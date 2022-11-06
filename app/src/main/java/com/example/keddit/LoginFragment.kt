package com.example.keddit

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.keddit.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)
        binding.loginToolbar.title = "로그인"

        binding.loginJoinbtn.setOnClickListener {
            val act = activity as MainActivity
            act.fragmentController("join", add = true, animate = true)
        }

        binding.loginLoginbtn.setOnClickListener {
            val boardMainIntent = Intent(requireContext(), BoardMainActivity::class.java)
            startActivity(boardMainIntent)
            activity?.finish()
        }

        return binding.root
    }
}