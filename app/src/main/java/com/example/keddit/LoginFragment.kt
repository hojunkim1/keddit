package com.example.keddit

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.keddit.databinding.FragmentLoginBinding
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.thread

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

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

            val loginId = binding.loginId.text.toString()
            val loginPw = binding.loginPw.text.toString()
            val chk = binding.loginAutologin.isChecked

            val loginAutoLogin = if (chk) {
                1
            } else {
                0
            }

            if (loginId.isEmpty()) {
                val dialogBuilder = AlertDialog.Builder(requireContext())
                dialogBuilder.setTitle("아이디 입력 오류")
                dialogBuilder.setMessage("아이디를 입력해 주세요")
                dialogBuilder.setPositiveButton("확인") { _: DialogInterface, _: Int ->
                    binding.loginId.requestFocus()
                }
                dialogBuilder.show()
                return@setOnClickListener
            }

            if (loginPw.isEmpty()) {
                val dialogBuilder = AlertDialog.Builder(requireContext())
                dialogBuilder.setTitle("비밀번호 입력 오류")
                dialogBuilder.setMessage("비밀번호를 입력해 주세요")
                dialogBuilder.setPositiveButton("확인") { _: DialogInterface, _: Int ->
                    binding.loginPw.requestFocus()
                }
                dialogBuilder.show()
                return@setOnClickListener
            }

            thread {
                val client = OkHttpClient()

                val site = "http://172.31.45.57/keddit-server/login_user.jsp"

                val builder = FormBody.Builder()
                builder.add("user_id", loginId)
                builder.add("user_pw", loginPw)
                val formBody = builder.build()

                val req = Request.Builder().url(site).post(formBody).build()
                val res = client.newCall(req).execute()

                if (res.isSuccessful) {
                    val resultText = res.body?.string()!!.trim()

                    if (resultText == "FAIL") {
                        activity?.runOnUiThread {
                            val dialogBuilder = AlertDialog.Builder(requireContext())
                            dialogBuilder.setTitle("로그인 실패")
                            dialogBuilder.setMessage("아이디나 비밀번호가 잘못되었습니다")
                            dialogBuilder.setPositiveButton("확인") { _: DialogInterface, _: Int ->
                                binding.loginId.setText("")
                                binding.loginPw.setText("")
                                binding.loginAutologin.isChecked = false
                                binding.loginId.requestFocus()
                            }
                            dialogBuilder.show()
                        }
                    } else {
                        activity?.runOnUiThread {
                            val dialogBuilder = AlertDialog.Builder(requireContext())
                            dialogBuilder.setTitle("로그인 성공")
                            dialogBuilder.setMessage("로그인에 성공하였습니다")
                            dialogBuilder.setPositiveButton("확인") { _: DialogInterface, _: Int ->
                                val pref = activity?.getSharedPreferences(
                                    "login_data",
                                    Context.MODE_PRIVATE
                                )
                                val editor = pref?.edit()

                                editor?.putInt("login_user_idx", Integer.parseInt(resultText))
                                editor?.putInt("login_auto_login", loginAutoLogin)
                                editor?.apply()


                                val boardMainIntent =
                                    Intent(requireContext(), BoardMainActivity::class.java)
                                startActivity(boardMainIntent)
                                activity?.finish()
                            }
                            dialogBuilder.show()
                        }
                    }
                } else {
                    activity?.runOnUiThread {
                        val dialogBuilder = AlertDialog.Builder(requireContext())
                        dialogBuilder.setTitle("로그인 오류")
                        dialogBuilder.setMessage("로그인 오류가 발생하였습니다")
                        dialogBuilder.setPositiveButton("확인", null)
                        dialogBuilder.show()
                    }
                }
            }

        }

        return binding.root
    }
}