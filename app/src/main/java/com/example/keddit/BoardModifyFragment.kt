package com.example.keddit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.keddit.databinding.FragmentBoardModifyBinding

class BoardModifyFragment : Fragment() {
    private lateinit var binding: FragmentBoardModifyBinding
    private val spinnerData = arrayOf("게시판1", "게시판2", "게시판3", "게시판4")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBoardModifyBinding.inflate(inflater)
        binding.boardModifyToolbar.title = "글 수정"

        binding.boardModifyToolbar.inflateMenu(R.menu.board_modify_menu)
        binding.boardModifyToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.board_modify_menu_camera -> {
                    true
                }
                R.id.board_modify_menu_gallery -> {
                    true
                }
                R.id.board_modify_menu_upload -> {
                    val act = activity as BoardMainActivity
                    act.fragmentRemoveBackStack("board_modify")
                    true
                }
                else -> false
            }
        }

        val spinnerAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinnerData)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.boardModifyType.adapter = spinnerAdapter

        return binding.root
    }
}