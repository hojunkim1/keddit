package com.example.keddit

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.example.keddit.databinding.FragmentBoardReadBinding

class BoardReadFragment : Fragment() {
    private lateinit var binding: FragmentBoardReadBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBoardReadBinding.inflate(inflater)
        binding.boardReadToolbar.title = "게시글 읽기"

        val navIcon = AppCompatResources.getDrawable(
            requireContext(),
            androidx.appcompat.R.drawable.abc_ic_ab_back_material
        )
        binding.boardReadToolbar.navigationIcon = navIcon

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            binding.boardReadToolbar.navigationIcon?.colorFilter =
                BlendModeColorFilter(Color.parseColor("#FFFFFF"), BlendMode.SRC_ATOP)
        } else {
            binding.boardReadToolbar.navigationIcon?.setColorFilter(
                Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP
            )
        }

        binding.boardReadToolbar.setNavigationOnClickListener {
            val act = activity as BoardMainActivity
            act.fragmentRemoveBackStack("board_read")
        }

        binding.boardReadToolbar.inflateMenu(R.menu.board_read_menu)
        binding.boardReadToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.board_read_menu_modify -> {
                    val act = activity as BoardMainActivity
                    act.fragmentController("board_modify", add = true, animate = true)
                    true
                }
                R.id.board_read_menu_delete -> {
                    val act = activity as BoardMainActivity
                    act.fragmentRemoveBackStack("board_read")
                    true
                }
                else -> false
            }
        }

        return binding.root
    }
}