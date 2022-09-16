package com.gabo.quiz8.ui.purchase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabo.quiz8.common.ResponseHandler
import com.gabo.quiz8.domain.models.PurchaseItemModel
import com.gabo.quiz8.domain.models.toRoomDto
import com.gabo.quiz8.domain.usecases.GetPurchaseItemsFromRoomUseCase
import com.gabo.quiz8.domain.usecases.GetPurchaseItemsUseCase
import com.gabo.quiz8.domain.usecases.SavePurchaseItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PurchaseViewModel @Inject constructor(
    private val getPurchaseItemsUseCase: GetPurchaseItemsUseCase,
    private val getPurchaseItemsFromRoomUseCase: GetPurchaseItemsFromRoomUseCase,
    private val savePurchaseItemsUseCase: SavePurchaseItemsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(ViewState())
    val state = _state.asStateFlow()

    init {
        getItems()
    }

    fun getItems() {
        _state.value = _state.value.copy(loading = true)
        resetViewState()
        viewModelScope.launch {
            val list = getPurchaseItemsFromRoomUseCase(Unit)
            _state.value = _state.value.copy(
                data = list
            )
        }
        viewModelScope.launch {
            getPurchaseItemsUseCase(Unit).collect {
                when (it) {
                    is ResponseHandler.Success -> {
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

    private fun resetViewState() {
        _state.value = ViewState()
    }

    data class ViewState(
        val loading: Boolean = false,
        val data: List<PurchaseItemModel> = emptyList(),
        val error: String = ""
    )
}