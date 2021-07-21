package space.quiz.freetoplaygames

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import space.quiz.freetoplaygames.UI.AllGamesFragment
import space.quiz.freetoplaygames.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mToolbar: androidx.appcompat.widget.Toolbar
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        navController = Navigation.findNavController(this, R.id.nav_host)

//        if (savedInstanceState == null)
//            addAllGameFragment()
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunc()
    }

    private fun initFunc(){
        setSupportActionBar(mToolbar)
//        mToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
//        mToolbar.setNavigationOnClickListener {
//            onBackPressed()
//        }
    }

    private fun initFields(){
        mToolbar = mBinding.mainToolbar
    }
//
//    private fun addAllGameFragment(){
//        supportFragmentManager
//            .beginTransaction()
//            .add(R.id.main_fragment_container, AllGamesFragment())
//            .commit()
//    }
}