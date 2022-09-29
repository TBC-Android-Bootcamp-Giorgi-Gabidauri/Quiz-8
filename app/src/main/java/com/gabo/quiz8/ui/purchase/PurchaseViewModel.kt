package com.gabo.quiz8.ui.purchase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabo.quiz8.common.ResponseHandler
import com.gabo.quiz8.domain.models.PurchaseItemModel
import com.gabo.quiz8.domain.models.toRoomDto
import com.gabo.quiz8.domain.usecases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PurchaseViewModel @Inject constructor(
    private val getPurchaseItemsUseCase: GetPurchaseItemsUseCase,
    private val getPurchaseItemsFromRoomUseCase: GetPurchaseItemsFromRoomUseCase,
    private val savePurchaseItemsUseCase: SavePurchaseItemsUseCase,
    private val buyItemUseCase: BuyItemUseCase,
//    private val deleteItemUseCase: DeleteItemUseCase,
//    private val itemExistUseCase: ItemExistUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(ViewState())
    val state = _state.asStateFlow()

    init {
        getItemsFromRoom()
    }

    fun buyItem(bought: Boolean, title: String) {
        viewModelScope.launch {
            buyItemUseCase(Pair(bought, title))
        }
    }

//    fun deleteItem(cover: String) {
//        viewModelScope.launch {
//            deleteItemUseCase(cover)
//        }
//    }

//    suspend fun exist(cover: String): Boolean {
//        return itemExistUseCase(cover)
//    }

    fun getItems() {
        if (_state.value.data.isEmpty()) {
            getItemsFromServerAndReplaceDatabase()
        } else {
            viewModelScope.launch {
                getItemsFromRoom()
            }.invokeOnCompletion { getItemsFromServerAndReplaceDatabase() }
        }
    }

    private fun getItemsFromRoom() {
        _state.value = _state.value.copy(loading = true)
        viewModelScope.launch {
            getPurchaseItemsFromRoomUseCase(Unit).collect { list ->
                _state.value = _state.value.copy(
                    loading = false,
                    data = list
                )
            }
        }
    }

    private fun getItemsFromServerAndReplaceDatabase() {
        viewModelScope.launch {
            getPurchaseItemsUseCase(Unit).collect {
                when (it) {
                    is ResponseHandler.Success -> {
                        it.data?.map { recieved ->
                            _state.value.data.forEach{
                                if (recieved.title == it.title && it.bought){
                                    recieved.bought = true
                                }
                            }
                        }
                        _state.value = _state.value.copy(
                            loading = false,
                            data = it.data ?: emptyList()
                        )
                        savePurchaseItemsUseCase(_state.value.data.map { it.toRoomDto() })
                    }
                    is ResponseHandler.Error -> {
                        _state.value = _state.value.copy(
                            loading = false,
                            error = it.errorMsg ?: "something went wrong"
                        )
                    }
                }
            }
        }
    }

//    private fun resetViewState() {
//        _state.value = ViewState()
//    }

    data class ViewState(
        val loading: Boolean = false,
        val data: List<PurchaseItemModel> = emptyList(),
        val error: String = ""
    )
}