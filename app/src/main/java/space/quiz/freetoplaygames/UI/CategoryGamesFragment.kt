package space.quiz.freetoplaygames.UI

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import space.quiz.freetoplaygames.Models.Game
import space.quiz.freetoplaygames.R
import space.quiz.freetoplaygames.Repository.Repository
import space.quiz.freetoplaygames.UI.adapters.CategoryAdapter
import space.quiz.freetoplaygames.UI.adapters.GameOnClickListener
import space.quiz.freetoplaygames.ViewModels.CategoryGameViewModel
import space.quiz.freetoplaygames.ViewModels.CategoryGameViewModelFactory
import space.quiz.freetoplaygames.databinding.FragmentCategoryGamesBinding

class CategoryGamesFragment : Fragment() {

    private lateinit var mBinding: FragmentCategoryGamesBinding
    private lateinit var viewModel: CategoryGameViewModel
    private lateinit var gamesList: List<Game>
    private lateinit var adapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentCategoryGamesBinding.inflate(layoutInflater)
        createViewModel()
        setHasOptionsMenu(true)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        createSpinner()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        val menuItem = menu.findItem(R.id.search_icon)
        val searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText!!)
                return true
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }
    private fun createViewModel(){
        val repository = Repository()
        val categoryGameViewModelFactory = CategoryGameViewModelFactory(repository)
        viewModel = ViewModelProvider(this, categoryGameViewModelFactory)
                .get(CategoryGameViewModel::class.java)
    }

    private fun loadCategory(category: String?){
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
        adapter = CategoryAdapter(list, object : GameOnClickListener{
            override fun onClicked(game: Game) {
                openFragment(game)
            }
        }, requireContext())
        rv.layoutManager = LinearLayoutManager(activity)
        rv.adapter = adapter
    }

    private fun createSpinner(){
        var categories = resources.getStringArray(R.array.games_category)
        val arrayAdapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, categories)
        mBinding.categorySpinner.adapter = arrayAdapter
        mBinding.categorySpinner.setSelection(arrayAdapter.getPosition(arguments?.getString("CATEGORY")!!))
        mBinding.categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == 0)
                    loadCategory(null)
                else
                    loadCategory(categories[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                loadCategory(arguments?.getString("CATEGORY")!!)
            }
        }
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

    private fun filter(text: String){
        val filterList = arrayListOf<Game>()
        for (game in gamesList){
            if (game.title.toLowerCase().contains(text.toLowerCase())){
                filterList.add(game)
            }
        }
        adapter.filterList(filterList)
    }

}