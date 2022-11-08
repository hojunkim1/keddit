package com.example.keddit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.keddit.databinding.FragmentBoardWriteBinding

class BoardWriteFragment : Fragment() {
    private lateinit var binding: FragmentBoardWriteBinding
    private val spinnerData = arrayOf("게시판1", "게시판2", "게시판3", "게시판4")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBoardWriteBinding.inflate(inflater)
        binding.boardWriteToolbar.title = "게시글 작성"

        binding.boardWriteToolbar.inflateMenu(R.menu.board_write_menu)
        binding.boardWriteToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.board_write_menu_camera -> {
                    true
                }
                R.id.board_write_menu_gallary -> {
                    true
                }
                R.id.board_write_menu_upload -> {
                    val act = activity as BoardMainActivity
                    act.fragmentRemoveBackStack("board_write")
                    act.fragmentController("board_read", add = true, animate = true)
                    true
                }
                else -> false
            }
        }

        val spinnerAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinnerData)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.boardWriteType.adapter = spinnerAdapter

        return binding.root
    }
}