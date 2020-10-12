package com.solar.retrofit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.solar.recyclerview.adapter.DataBindingAdapter
import com.solar.retrofit.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val viewModel: CoroutineViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.list.observe(this, Observer {
            user_list_view.adapter = DataBindingAdapter<UserView>(viewModel).apply {
                submitList(it)
            }
        })
    }
}