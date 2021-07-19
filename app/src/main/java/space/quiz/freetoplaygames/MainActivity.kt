package space.quiz.freetoplaygames

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.widget.Toolbar
import space.quiz.freetoplaygames.UI.AllGamesFragment
import space.quiz.freetoplaygames.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mToolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        if (savedInstanceState == null)
            addAllGameFragment()
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunc()
    }

    private fun initFunc(){
        setSupportActionBar(mToolbar)
    }

    private fun initFields(){
        mToolbar = mBinding.mainToolbar
    }

    private fun addAllGameFragment(){
        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_fragment_container, AllGamesFragment())
            .commit()
    }
}