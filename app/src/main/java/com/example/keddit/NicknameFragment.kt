package com.example.keddit

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.keddit.databinding.FragmentNicknameBinding

class NicknameFragment : Fragment() {
    private lateinit var binding: FragmentNicknameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNicknameBinding.inflate(inflater)
        binding.nicknameToolbar.title = "닉네임 입력"

        binding.nicknameJoinbtn.setOnClickListener {
            val mainIntent = Intent(requireContext(), MainActivity::class.java)
            startActivity(mainIntent)
            activity?.finish()
        }

        return binding.root
    }
}