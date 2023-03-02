package com.tavantdemo.productscatalogue.ui.favproducts

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cheezycode.notesample.repository.ProductListRepository
import com.tavantdemo.productscatalogue.model.ProductsResponseItem
import com.tavantdemo.productscatalogue.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteNotificationsViewModel @Inject constructor(private val productListRepository : ProductListRepository): ViewModel() {

    val userResponseLiveData: LiveData<NetworkResult<List<ProductsResponseItem>>>
        get() = productListRepository.userResponseLiveData

    fun getProductList(productsResponse : Int){
        viewModelScope.launch {
            productListRepository.getProductList(10)
        }
    }


}