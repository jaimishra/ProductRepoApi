package com.tavantdemo.productscatalogue.adapter
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tavantdemo.productscatalogue.databinding.ItemProductShellBinding
import com.tavantdemo.productscatalogue.model.ProductsResponseItem


class CommonProductListAdapter(context: Context) : RecyclerView.Adapter<CommonProductListAdapter.ProductViewHolder>() {

    var productList : ArrayList<ProductsResponseItem>? =null
    var onProductClickListener: OnProductClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductShellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val   productsResponseItem = productList?.get(position)
        holder.rowItemProductShellBinding.tvProductTittle.text= productsResponseItem?.title
        Glide
            .with(holder.itemView.context)
            .load(productsResponseItem?.image)
            .centerInside()
            .into(holder.rowItemProductShellBinding.imageView);
        holder.rowItemProductShellBinding.tvProduct.text = productsResponseItem?.rating?.rate.toString()
        holder.rowItemProductShellBinding.tvProductCount.text = "("+ productsResponseItem?.rating?.count.toString()+ ")"
        holder.rowItemProductShellBinding.tvPrice.text = "$"+productsResponseItem?.price.toString()
        holder.rowItemProductShellBinding.overallRating.rating =
            (productsResponseItem?.rating?.rate?.toFloat() ?: 0) as Float
        holder.rowItemProductShellBinding.cvLayout.setOnClickListener{
            onProductClickListener?.onShellClick(productList?.get(position))
        }
        holder.rowItemProductShellBinding.imFav.setOnClickListener{
            onProductClickListener?.onFavClick(productList?.get(position))
        }

    }

    fun setItemsData(itemsData: ArrayList<ProductsResponseItem>) {
        productList?.clear()
        productList = itemsData
        notifyDataSetChanged()
    }

    fun setClickListener(categoryClickListener:OnProductClickListener){
        onProductClickListener = categoryClickListener
    }



    class ProductViewHolder( itemView : ItemProductShellBinding) :
        RecyclerView.ViewHolder(itemView.getRoot()) {

        val rowItemProductShellBinding: ItemProductShellBinding

        init {
            this.rowItemProductShellBinding = itemView
        }
    }

    interface OnProductClickListener{
        fun onShellClick(name: ProductsResponseItem?)
        fun onFavClick(get: ProductsResponseItem?)
    }

}