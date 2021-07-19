package space.quiz.freetoplaygames.UI

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import space.quiz.freetoplaygames.Models.Game
import space.quiz.freetoplaygames.R
import space.quiz.freetoplaygames.Repository.Repository
import space.quiz.freetoplaygames.UI.adapters.GameOnClickListener
import space.quiz.freetoplaygames.UI.adapters.GamesAdapter
import space.quiz.freetoplaygames.ViewModels.CategoryGameViewModel
import space.quiz.freetoplaygames.ViewModels.CategoryGameViewModelFactory
import space.quiz.freetoplaygames.databinding.FragmentCategoryGamesBinding

class CategoryGamesFragment : Fragment() {

    private lateinit var mBinding: FragmentCategoryGamesBinding
    private lateinit var viewModel: CategoryGameViewModel
    private lateinit var gamesList: List<Game>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentCategoryGamesBinding.inflate(layoutInflater)
        createViewModel()

//        if (arguments?.getString("CATEGORY") != null)
//        loadCategory(arguments?.getString("CATEGORY")!!)

        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        loadCategory(arguments?.getString("CATEGORY")!!)
    }

    private fun createViewModel(){
        val repository = Repository()
        val categoryGameViewModelFactory = CategoryGameViewModelFactory(repository)
        viewModel = ViewModelProvider(this, categoryGameViewModelFactory)
                .get(CategoryGameViewModel::class.java)
    }

    private fun loadCategory(category: String){
        viewModel.getCategory(category)
        viewModel.categoryResponse.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful){
                Log.d("Response", response.body().toString())
                gamesList = response.body()!!
                createRv(gamesList, mBinding.categoryRv)
            }
        })
    }

    private fun createRv(list: List<Game>, rv: RecyclerView){
        val adapter = GamesAdapter(list, object : GameOnClickListener{
            override fun onClicked(game: Game) {
            }
        }, requireContext())
        rv.layoutManager = GridLayoutManager(activity, 2, LinearLayoutManager.VERTICAL, false)
        rv.setHasFixedSize(true)
        rv.adapter = adapter
//        rv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
//        rv.adapter = adapter
    }

}