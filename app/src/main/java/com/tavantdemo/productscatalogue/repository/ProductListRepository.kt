package com.cheezycode.notesample.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tavantdemo.productscatalogue.api.IProductApi
import com.tavantdemo.productscatalogue.model.ProductsResponseItem
import com.tavantdemo.productscatalogue.utils.NetworkResult
import retrofit2.Response
import javax.inject.Inject

class ProductListRepository @Inject constructor(private val iProductApi:IProductApi) {

    private val _userResponseLiveData = MutableLiveData<NetworkResult<List<ProductsResponseItem>>>()
    val userResponseLiveData: LiveData<NetworkResult<List<ProductsResponseItem>>>
        get() = _userResponseLiveData

    suspend fun getProductList(itemPerPage: Int) {
        _userResponseLiveData.postValue(NetworkResult.Loading())
        val response = iProductApi.getProductList(itemPerPage)
        handleResponse(response)
    }


    private fun handleResponse(response: Response<List<ProductsResponseItem>>) {
        if (response.isSuccessful && response.body()!= null) {
            _userResponseLiveData.postValue((NetworkResult.Success(response.body()!!)))
        }
        else if (response.errorBody() != null) {
            _userResponseLiveData.postValue(NetworkResult.Error("message"))
        }
        else{
            _userResponseLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }
}