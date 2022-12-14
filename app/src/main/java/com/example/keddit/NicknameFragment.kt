package com.example.keddit

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.keddit.databinding.FragmentNicknameBinding
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.thread

class NicknameFragment : Fragment() {
    private lateinit var binding: FragmentNicknameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNicknameBinding.inflate(inflater)
        binding.nicknameToolbar.title = "닉네임 입력"

        binding.nicknameJoinbtn.setOnClickListener {
            val nickname = binding.nicknameNickname.text.toString()

            if (nickname.isEmpty()) {
                val dialogBuilder = AlertDialog.Builder(requireContext())
                dialogBuilder.setTitle("닉네임 입력 오류")
                dialogBuilder.setMessage("닉네임을 입력해주세요")
                dialogBuilder.setPositiveButton("확인") { _: DialogInterface, _: Int ->
                    binding.nicknameNickname.requestFocus()
                }
                dialogBuilder.show()
                return@setOnClickListener
            }

            val act = activity as MainActivity

            act.userNickname = nickname

            thread {
                val client = OkHttpClient()

                val site = "http://172.31.45.57/keddit-server/join_user.jsp"

                val builder = FormBody.Builder()
                builder.add("user_id", act.userId)
                builder.add("user_pw", act.userPw)
                builder.add("user_nickname", act.userNickname)
                val formBody = builder.build()

                val req = Request.Builder().url(site).post(formBody).build()
                val res = client.newCall(req).execute()

                if (res.isSuccessful) {
                    activity?.runOnUiThread {
                        val dialogBuilder = AlertDialog.Builder(requireContext())
                        dialogBuilder.setTitle("가입 완료")
                        dialogBuilder.setMessage("가입이 완료되었습니다")
                        dialogBuilder.setPositiveButton("확인") { _: DialogInterface, _: Int ->
                            val mainIntent = Intent(requireContext(), MainActivity::class.java)
                            startActivity(mainIntent)
                            activity?.finish()
                        }
                        dialogBuilder.show()
                    }
                } else {
                    activity?.runOnUiThread {
                        val dialogBuilder = AlertDialog.Builder(requireContext())
                        dialogBuilder.setTitle("가입 오류")
                        dialogBuilder.setMessage("가입 오류가 발생하였습니다")
                        dialogBuilder.setPositiveButton("확인", null)
                        dialogBuilder.show()
                    }
                }
            }

            val mainIntent = Intent(requireContext(), MainActivity::class.java)
            startActivity(mainIntent)
            activity?.finish()
        }

        return binding.root
    }
}