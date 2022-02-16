package com.vikas.zoodmall.mobile.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vikas.zoodmall.mobile.R
import com.vikas.zoodmall.mobile.adapter.Api2PagingAdapter
import com.vikas.zoodmall.mobile.adapter.ImageDataComparator
import com.vikas.zoodmall.mobile.adapter.ImageListAdapter
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DashboardActivity : AppCompatActivity() {

    private val dashboardViewModel: DashboardViewModel by viewModels()
    private val recyclerViewApi1: RecyclerView by lazy { findViewById(R.id.recyclerview_api1) }
    private val recyclerViewApi2: RecyclerView by lazy { findViewById(R.id.recyclerview_api2) }

    private val pagingAdapter = Api2PagingAdapter(ImageDataComparator)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        recyclerViewApi2.adapter = pagingAdapter
        recyclerViewApi2.layoutManager = GridLayoutManager(applicationContext, 2)

        dashboardViewModel.callApi1().observe(this, { resultStatus ->

            when(resultStatus) {
                is ResultStatus.Success -> {
                    handleApi1Success(resultStatus.data)
                }
                is ResultStatus.Failure -> {
                    resultStatus.exception.printStackTrace()
                    Toast.makeText(this, resultStatus.exception.message, Toast.LENGTH_LONG).show()
                }
            }
        })

        lifecycleScope.launch {
            dashboardViewModel.callApi2Pagination().collectLatest {
                pagingAdapter.submitData(it)
            }
        }

    }

    private fun handleApi1Success(data: List<ApiUIModel>) {
        val imageListAdapter = ImageListAdapter(
                dataSet = data
        ) {
            Toast.makeText(this, "${it.id} clicked", Toast.LENGTH_SHORT).show()
        }
        recyclerViewApi1.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewApi1.adapter = imageListAdapter

        // auto scroll mechanism
        val job = lifecycleScope.launch(Dispatchers.Main, start = CoroutineStart.LAZY) {
            do {
                var position = 0
                repeat(imageListAdapter.itemCount) {
                    delay(1000)
                    recyclerViewApi1.smoothScrollToPosition(position++)
                }
            } while (true)

        }
        job.start()
    }

}