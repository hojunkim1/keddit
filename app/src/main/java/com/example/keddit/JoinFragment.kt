package com.example.keddit

import android.app.AlertDialog
import android.content.DialogInterface
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
            val joinId = binding.joinId.text.toString()
            val joinPw = binding.joinPw.text.toString()

            if (joinId.isEmpty()) {
                val dialogBuilder = AlertDialog.Builder(requireContext())
                dialogBuilder.setTitle("아이디 입력 오류")
                dialogBuilder.setMessage("아이디를 입력해 주세요")
                dialogBuilder.setPositiveButton("확인") { _: DialogInterface, _: Int ->
                    binding.joinId.requestFocus()
                }

                dialogBuilder.show()
                return@setOnClickListener
            }

            if (joinPw.isEmpty()) {
                val dialogBuilder = AlertDialog.Builder(requireContext())
                dialogBuilder.setTitle("비밀번호 입력 오류")
                dialogBuilder.setMessage("비밀번호를 입력해 주세요")
                dialogBuilder.setPositiveButton("확인") { _: DialogInterface, _: Int ->
                    binding.joinPw.requestFocus()
                }
                dialogBuilder.show()
                return@setOnClickListener
            }

            val act = activity as MainActivity

            act.userId = joinId
            act.userPw = joinPw

            act.fragmentController("nickname", add = true, animate = true)
        }

        return binding.root
    }
}