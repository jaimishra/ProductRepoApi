package com.tavantdemo.productscatalogue.api
import com.tavantdemo.productscatalogue.model.ProductsResponseItem
import retrofit2.Response
import retrofit2.http.*

interface IProductApi {
   //https://fakestoreapi.com/products?limit=10
    @GET("products")
    suspend fun getProductList(@Query("limit") itemsSize: Int): Response<List<ProductsResponseItem>>

}