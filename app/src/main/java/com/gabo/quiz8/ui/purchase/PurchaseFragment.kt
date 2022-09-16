package com.gabo.quiz8.ui.purchase

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.gabo.quiz8.PurchaseAdapter
import com.gabo.quiz8.base.BaseFragment
import com.gabo.quiz8.common.extension.launchStarted
import com.gabo.quiz8.databinding.FragmentPurchaseBinding
import com.gabo.quiz8.domain.models.PurchaseItemModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PurchaseFragment : BaseFragment<FragmentPurchaseBinding>(FragmentPurchaseBinding::inflate) {
    private val viewModel: PurchaseViewModel by viewModels()
    private lateinit var purchaseAdapter: PurchaseAdapter
    override fun setupView() {
        setupObservers()
        setupAdapters()
        setupListeners()
    }

    private fun setupObservers() {
        viewLifecycleOwner.launchStarted {
            viewModel.state.collect {
                if (it.error.isNotEmpty()){
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }
                if (it.data != emptyList<PurchaseItemModel>()){
                    purchaseAdapter.submitList(it.data)
                    binding.tvNotFound.visibility = View.GONE
                }else{
                    binding.tvNotFound.visibility = View.VISIBLE
                }
                binding.root.isRefreshing = it.loading
            }
        }
    }

    private fun setupAdapters() {
        purchaseAdapter = PurchaseAdapter { }
        binding.rvPurchaseItems.adapter = purchaseAdapter
        binding.rvPurchaseItems.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
    }
    private fun setupListeners(){
        binding.root.setOnRefreshListener {
            viewModel.getItems()
        }
    }
}