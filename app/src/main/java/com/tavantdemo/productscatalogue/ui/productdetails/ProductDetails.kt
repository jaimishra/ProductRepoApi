package com.tavantdemo.productscatalogue.ui.productdetails
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.tavantdemo.productscatalogue.databinding.FragmentProductDetailsBinding
import com.tavantdemo.productscatalogue.model.ProductsResponseItem


class ProductDetails : Fragment(){
    private var _binding: FragmentProductDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val productsResponseItem =arguments?.getParcelable<ProductsResponseItem>("ProductItem")
        binding.tvProductTitle.text= productsResponseItem?.title
        Glide
            .with(requireActivity())
            .load(productsResponseItem?.image)
            .centerInside()
            .into(binding.imProduct);
        binding.tvDescription.text = productsResponseItem?.description
        binding.tvRatCount.text = "("+ productsResponseItem?.rating?.count.toString()+ ")"
        binding.tvPrice.text = "$"+productsResponseItem?.price.toString()
        binding.overallRating.rating =
            (productsResponseItem?.rating?.rate?.toFloat() ?: 0) as Float

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}