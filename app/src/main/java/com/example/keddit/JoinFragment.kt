package com.example.keddit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.keddit.databinding.FragmentJoinBinding

class JoinFragment : Fragment() {
    private lateinit var binding: FragmentJoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJoinBinding.inflate(inflater)
        binding.joinToolbar.title = "회원가입"

        binding.joinNextbtn.setOnClickListener {
            val act = activity as MainActivity
            act.fragmentController("nickname", add = true, animate = true)
        }

        return binding.root
    }
}