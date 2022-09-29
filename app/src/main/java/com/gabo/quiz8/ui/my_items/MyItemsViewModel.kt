package com.gabo.quiz8.ui.my_items

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabo.quiz8.domain.models.PurchaseItemModel
import com.gabo.quiz8.domain.usecases.BuyItemUseCase
import com.gabo.quiz8.domain.usecases.DeleteItemUseCase
import com.gabo.quiz8.domain.usecases.GetBoughtItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyItemsViewModel @Inject constructor(
    private val getBoughtItemsUseCase: GetBoughtItemsUseCase,
//    private val deleteItemUseCase: DeleteItemUseCase,
    private val buyItemUseCase: BuyItemUseCase
) :
    ViewModel() {
    private val _data: MutableStateFlow<List<PurchaseItemModel>> = MutableStateFlow(emptyList())
    val data = _data.asStateFlow()

    init {
        getBoughtItems()
    }

    private fun getBoughtItems() {
        viewModelScope.launch {
            getBoughtItemsUseCase(Unit).collect{
                _data.value = it
            }
        }
    }
    fun buyItem(bought: Boolean, title: String) {
        viewModelScope.launch {
            buyItemUseCase(Pair(bought,title))
        }
    }

//    fun deleteItem(cover: String) {
//        viewModelScope.launch {
//            deleteItemUseCase(cover)
//        }
//    }
}