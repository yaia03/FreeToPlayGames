package space.quiz.freetoplaygames.UI

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import space.quiz.freetoplaygames.R
import space.quiz.freetoplaygames.UI.adapters.ScreenPageAdapter
import space.quiz.freetoplaygames.databinding.FragmentCategoryGamesBinding
import space.quiz.freetoplaygames.databinding.FragmentScreenBinding

class ScreenFragment : Fragment() {

    private lateinit var mBinding: FragmentScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentScreenBinding.inflate(layoutInflater)
        var list = arguments?.getStringArrayList("SCREENS")!!
        var position = arguments?.getInt("POSITION")!!
        createViewPager(list, position)

        return mBinding.root
    }

    private fun createViewPager(list: List<String>, position: Int){
        mBinding.screenVp.adapter = ScreenPageAdapter(list, requireContext())
        mBinding.screenVp.setCurrentItem(position,false)


        TabLayoutMediator(mBinding.screenTab, mBinding.screenVp) {tab, position ->
        }.attach()
    }
}