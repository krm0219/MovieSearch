package com.lotte.task.moviesearch.main

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.lotte.task.moviesearch.R
import com.lotte.task.moviesearch.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //   setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        // 키보드에서 검색 버튼 눌렀을 때, 검색이 가능하도록
        edit_main_keyword.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    viewModel.clickSearch()
                    return true
                }

                return false
            }
        })


        // 이벤트 발생시, 키보드 숨기기
        viewModel.hideKeyboard.observe(this, Observer {

            it.getContentIfNotHandled()?.let {

                hideKeyboard()
            }
        })

        // Toast Event
        viewModel.showEmptyToast.observe(this, Observer {

            it.getContentIfNotHandled()?.let {

                Toast.makeText(this, R.string.msg_empty_keyword, Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.showErrorToast.observe(this, Observer {

            it.getContentIfNotHandled()?.let { it1 ->

                if (it1 == "empty") {

                    Toast.makeText(this, R.string.msg_result_null, Toast.LENGTH_SHORT).show()
                    recycler_main_list.visibility = View.GONE
                } else if (it1 == "network") {

                    Toast.makeText(this, R.string.msg_error, Toast.LENGTH_SHORT).show()
                }
            }
        })


        setRecyclerView()
    }

    private fun setRecyclerView() {

        val adapter = MainAdapter()

        binding.recyclerMainList.adapter = adapter
        binding.recyclerMainList.setHasFixedSize(true)

        viewModel.movies.observe(this, Observer {

            recycler_main_list.visibility = View.VISIBLE
            adapter.setData(it)
            recycler_main_list.scrollToPosition(0)
        })
    }

    private fun hideKeyboard() {

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(edit_main_keyword.windowToken, 0)
    }
}