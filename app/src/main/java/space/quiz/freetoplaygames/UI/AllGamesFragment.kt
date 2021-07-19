package space.quiz.freetoplaygames.UI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import space.quiz.freetoplaygames.Models.Game
import space.quiz.freetoplaygames.R
import space.quiz.freetoplaygames.Repository.Repository
import space.quiz.freetoplaygames.UI.adapters.GameOnClickListener
import space.quiz.freetoplaygames.UI.adapters.GamesAdapter
import space.quiz.freetoplaygames.ViewModels.AllGamesViewModel
import space.quiz.freetoplaygames.ViewModels.AllGamesViewModelFactory
import space.quiz.freetoplaygames.databinding.FragmentAllGamesBinding


class AllGamesFragment : Fragment() {

    private lateinit var mBinding: FragmentAllGamesBinding
    private lateinit var viewModel: AllGamesViewModel
    private lateinit var gamesList: List<Game>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentAllGamesBinding.inflate(layoutInflater)
        initFunc()
        return mBinding.root
    }

    private fun initFunc(){
        val viewModelFactory = AllGamesViewModelFactory(Repository())
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(AllGamesViewModel::class.java)
        viewModel.getBrowserGames()
        viewModel.getPcGames()
        viewModel.pcGamesResponse.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful){
                Log.d("Response", response.body().toString())
                gamesList = response.body()!!
                createRV(gamesList, mBinding.allGamesFragmentPcRv)
            }
            else
                Log.d("Response", response.errorBody().toString())
        })

        viewModel.browserGamesResponse.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful){
                Log.d("Response", response.body().toString())
                gamesList = response.body()!!
                createRV(gamesList, mBinding.allGamesFragmentBrowserRv)
            }
            else
                Log.d("Response", response.errorBody().toString())
        })
    }

    private fun createRV(list: List<Game>, rv: RecyclerView){
        val adapter = GamesAdapter(list, object : GameOnClickListener {
            override fun onClicked(game: Game) {
                openFragment(game)
            }
        }, requireContext())
        rv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv.adapter = adapter
    }

    private fun openFragment(game: Game){
        val fragment = GameInfoFragment()
        val args = Bundle().apply {
            putInt("GAME_ID", game.id)
        }
        fragment.arguments = args
        parentFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.main_fragment_container, fragment)
            .commit()
    }
}