package space.quiz.freetoplaygames.UI

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListAdapter
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ms.square.android.expandabletextview.ExpandableTextView
import space.quiz.freetoplaygames.Models.Game
import space.quiz.freetoplaygames.Models.Screenshot
import space.quiz.freetoplaygames.R
import space.quiz.freetoplaygames.Repository.Repository
import space.quiz.freetoplaygames.UI.adapters.GameOnClickListener
import space.quiz.freetoplaygames.UI.adapters.GamesAdapter
import space.quiz.freetoplaygames.UI.adapters.ScreenOnClickListener
import space.quiz.freetoplaygames.UI.adapters.ScreenshotAdapter
import space.quiz.freetoplaygames.ViewModels.GameInfoViewModel
import space.quiz.freetoplaygames.ViewModels.GameInfoViewModelFactory
import space.quiz.freetoplaygames.databinding.FragmentAllGamesBinding
import space.quiz.freetoplaygames.databinding.FragmentGameInfoBinding


class GameInfoFragment : Fragment() {

    private lateinit var viewModel: GameInfoViewModel
    private lateinit var textView: ExpandableTextView
    private lateinit var gameImage: ImageView
    private lateinit var download: ImageButton
    private lateinit var gameName: TextView
    private lateinit var gameDeveloper: TextView
    private lateinit var gamePublisher: TextView
    private lateinit var gameDate: TextView
    private lateinit var gameGenre: TextView
    private lateinit var screenRv: RecyclerView
    private lateinit var minOs: TextView
    private lateinit var minProc: TextView
    private lateinit var minMemory: TextView
    private lateinit var minGraph: TextView
    private lateinit var minStorage: TextView
    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_game_info, container, false)

        init()
        return root
    }

    override fun onStart() {
        super.onStart()
        getGame()
        gameGenre.setOnClickListener {
            openFragment(gameGenre.text.toString())
        }
    }

    private fun createViewModel(){
        val repository = Repository()
        val viewModelFactory = GameInfoViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(GameInfoViewModel::class.java)
    }

    private fun init(){
        createViewModel()
        textView = root.findViewById(R.id.expand_text_view) as ExpandableTextView
        gameImage = root.findViewById(R.id.fragment_game_info_image)
        gameDate = root.findViewById(R.id.fragment_game_info_release_date)
        gameName = root.findViewById(R.id.fragment_game_info_name)
        gameDeveloper = root.findViewById(R.id.fragment_game_info_developer)
        gamePublisher = root.findViewById(R.id.fragment_game_info_publisher)
        screenRv = root.findViewById(R.id.fragment_game_info_screen_rv)
        minGraph = root.findViewById(R.id.fragment_game_info_min_graphics)
        minMemory = root.findViewById(R.id.fragment_game_info_min_memory)
        minOs = root.findViewById(R.id.fragment_game_info_min_os)
        minProc = root.findViewById(R.id.fragment_game_info_min_processor)
        minStorage = root.findViewById(R.id.fragment_game_info_min_storage)
        gameGenre = root.findViewById(R.id.fragment_game_info_genre)
        download = root.findViewById(R.id.fragment_game_info_download)
    }

    private fun getGame(){
        val id = arguments?.getInt("GAME_ID")
        viewModel.getGame(id!!)
        viewModel.gameResponse.observe(viewLifecycleOwner, Observer { reponse ->
            if (reponse.isSuccessful){
                Log.d("Response", reponse.body().toString())
                createRv(reponse.body()?.screenshots!!, screenRv)
                outInfo(reponse.body()!!)
            }
            else
                Log.d("Response", reponse.errorBody().toString())
        })
    }

    private fun outInfo(game: Game){
        gamePublisher.text = game.publisher
        gameDeveloper.text = game.developer
        gameName.text = game.title
        gameDate.text = game.release_date
        textView.text = game.description
        val uri = Uri.parse(game.thumbnail)
        Glide.with(this)
            .load(uri)
            .into(gameImage)
        minStorage.text = game.minimum_system_requirements?.storage
        minProc.text = game.minimum_system_requirements?.processor
        minOs.text = game.minimum_system_requirements?.os
        minMemory.text = game.minimum_system_requirements?.memory
        minGraph.text = game.minimum_system_requirements?.graphics
        gameGenre.text = game.genre
        download.setOnClickListener(View.OnClickListener {
            openWebsite(game.game_url)
        })
    }

    private fun createRv(list: List<Screenshot>, rv: RecyclerView){
        val adapter = ScreenshotAdapter(list, object : ScreenOnClickListener {
            override fun onClicked(screen: Screenshot) {}
        }, requireContext())
        rv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv.adapter = adapter
    }

    private fun openFragment(category: String){
        val fragment = CategoryGamesFragment()
        val args = Bundle().apply {
            putString("CATEGORY", category.toLowerCase())
        }
        fragment.arguments = args
        parentFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.main_fragment_container, fragment)
            .commit()
    }

    private fun openWebsite(uri: String){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        startActivity(intent)
    }
}