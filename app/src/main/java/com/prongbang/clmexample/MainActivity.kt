package com.prongbang.clmexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.prongbang.clm.CenterDownLayoutManager
import com.prongbang.clm.CenterUpLayoutManager
import com.prongbang.clm.CenterZoomLayoutManager
import com.prongbang.clm.extension.pagerSnapper
import com.prongbang.clmexample.databinding.ActivityMainBinding
import com.prongbang.clmexample.list.MainAdapter

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mainAdapter by lazy { MainAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
        initLoad()
    }

    private fun initLoad() {
        val cards = arrayListOf<Card>()
        for (i in 1..5) {
            cards.add(Card(i))
        }

        mainAdapter.submitList(cards)
    }

    private fun initView() {
        binding.apply {

            // Looper scroll
            mainAdapter.addOnLooperScrollListener(recyclerViewUp)

            recyclerViewUp.apply {
                adapter = mainAdapter
                layoutManager =
                    CenterUpLayoutManager(context, RecyclerView.HORIZONTAL, false, pixelSpace = 50f)
                pagerSnapper()
            }

            recyclerViewZoom.apply {
                adapter = mainAdapter
                layoutManager =
                    CenterZoomLayoutManager(context, RecyclerView.HORIZONTAL, false)
                pagerSnapper()
            }

            recyclerViewDown.apply {
                adapter = mainAdapter
                layoutManager =
                    CenterDownLayoutManager(
                        context,
                        RecyclerView.HORIZONTAL,
                        false,
                        pixelSpace = 50f
                    )
                pagerSnapper()
            }
        }
    }

}