package com.gabo.quiz8.ui.my_items

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log.d
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.gabo.quiz8.PurchaseAdapter
import com.gabo.quiz8.base.BaseFragment
import com.gabo.quiz8.common.extension.launchStarted
import com.gabo.quiz8.databinding.FragmentMyItemsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyItemsFragment : BaseFragment<FragmentMyItemsBinding>(FragmentMyItemsBinding::inflate) {
    val viewModel: MyItemsViewModel by viewModels()
    private lateinit var adapter: PurchaseAdapter
    override fun setupView() {
        setupAdapters()
        setupObservers()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupAdapters() {
        adapter = PurchaseAdapter { model, imageView ->
            if (model.bought) {
                viewModel.buyItem(false, model.title)
            }
        }
        binding.rvBought.adapter = adapter
        binding.rvBought.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun setupObservers(){
        viewLifecycleOwner.launchStarted {
            viewModel.data.collect{
                d("MY List", it.toString())
                adapter.submitList(it)
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

}