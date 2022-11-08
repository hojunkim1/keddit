package com.example.keddit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.keddit.databinding.ActivityBoardMainBinding

class BoardMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBoardMainBinding
    private lateinit var currentFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoardMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragmentController("board_main", add = false, animate = false)
    }

    fun fragmentController(name: String, add: Boolean, animate: Boolean) {
        when (name) {
            "board_main" -> {
                currentFragment = BoardMainFragment()
            }
            "board_read" -> {
                currentFragment = BoardReadFragment()
            }
            "board_write" -> {
                currentFragment = BoardWriteFragment()
            }
            "board_modify" -> {
                currentFragment = BoardModifyFragment()
            }
        }

        val trans = supportFragmentManager.beginTransaction()
        trans.replace(R.id.board_main_container, currentFragment)

        if (add) {
            trans.addToBackStack(name)
        }

        if (animate) {
            trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        }

        trans.commit()
    }

    fun fragmentRemoveBackStack(name: String) {
        supportFragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}