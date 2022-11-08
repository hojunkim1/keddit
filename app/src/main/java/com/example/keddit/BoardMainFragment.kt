package com.example.keddit

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.keddit.databinding.BoardMainRecyclerItemBinding
import com.example.keddit.databinding.FragmentBoardMainBinding

class BoardMainFragment : Fragment() {
    private lateinit var binding: FragmentBoardMainBinding
    private val boardListData = arrayOf("전체글", "게시판1", "게시판2", "게시판3", "게시판4")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBoardMainBinding.inflate(inflater)
        binding.boardMainToolbar.title = "게시판 이름"

        binding.boardMainToolbar.inflateMenu(R.menu.board_main_menu)
        binding.boardMainToolbar.setOnMenuItemClickListener() {
            when (it.itemId) {
                R.id.board_main_menu_board_list -> {
                    val boardListBuilder = AlertDialog.Builder(requireContext())
                    boardListBuilder.setTitle("게시판 목록")
                    boardListBuilder.setNegativeButton("취소", null)
                    boardListBuilder.setItems(boardListData, null)
                    boardListBuilder.show()
                    true
                }
                R.id.board_main_menu_write -> {
                    val act = activity as BoardMainActivity
                    act.fragmentController("board_write", add = true, animate = true)
                    true
                }
                else -> false
            }
        }

        val boardMainRecyclerAdapter = BoardMainRecyclerAdapter()
        binding.boardMainRecycler.adapter = boardMainRecyclerAdapter

        binding.boardMainRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.boardMainRecycler.addItemDecoration(DividerItemDecoration(requireContext(), 1))

        return binding.root
    }

    inner class BoardMainRecyclerAdapter :
        RecyclerView.Adapter<BoardMainRecyclerAdapter.ViewHolderClass>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val boardMainRecyclerItemBinding = BoardMainRecyclerItemBinding.inflate(layoutInflater)
            val holder = ViewHolderClass(boardMainRecyclerItemBinding)

            val layoutParams = RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            boardMainRecyclerItemBinding.root.layoutParams = layoutParams
            boardMainRecyclerItemBinding.root.setOnClickListener(holder)

            return holder
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        }

        override fun getItemCount(): Int {
            return 10
        }

        inner class ViewHolderClass(boardMainRecyclerItemBinding: BoardMainRecyclerItemBinding) :
            RecyclerView.ViewHolder(boardMainRecyclerItemBinding.root), View.OnClickListener {
            override fun onClick(p0: View?) {
                val act = activity as BoardMainActivity
                act.fragmentController("board_read", add = true, animate = true)
            }
        }
    }
}