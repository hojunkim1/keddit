package com.example.keddit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.keddit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var currentFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragmentController("login", add = false, animate = false)
    }

    private fun fragmentController(name: String, add: Boolean, animate: Boolean) {

        when (name) {
            "login" -> {
                currentFragment = LoginFragment()
            }
        }

        val trans = supportFragmentManager.beginTransaction()
        trans.replace(R.id.main_container, currentFragment)

        if (add) {
            trans.addToBackStack(name)
        }

        if (animate) {
            trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        }

        trans.commit()
    }
}