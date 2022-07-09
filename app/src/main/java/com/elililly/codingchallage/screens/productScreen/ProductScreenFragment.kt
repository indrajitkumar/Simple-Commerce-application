package com.elililly.codingchallage.screens.productScreen

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.elililly.codingchallage.*
import com.elililly.codingchallage.databinding.ProductListFragmentBinding
import com.elililly.codingchallage.models.Products
import com.elililly.codingchallage.models.Stores
import com.elililly.codingchallage.screens.BaseFragment
import com.elililly.codingchallage.screens.orderSummaryScreen.OrderSummaryScreenFragment
import com.elililly.codingchallage.utils.Constants
import java.io.Serializable

class ProductScreenFragment : BaseFragment() {
    private lateinit var productListAdaptor: ProductListAdaptor
    lateinit var binding: ProductListFragmentBinding
    private var mContext: Context? = null
    lateinit var productList: Products
    lateinit var storeList: Stores
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val jsonFileString = getJsonDataFromAsset(this.context!!, "Products.json")
        Log.i("data", jsonFileString!!)
        productList = jsonFileString.fromJson<Products>()

        val jsonDataFromAsset = getJsonDataFromAsset(this.context!!, "Stores.json")
        storeList = jsonDataFromAsset!!.fromJson<Stores>()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = ProductListFragmentBinding.inflate(inflater, container, false)
        val resId: Int = mContext!!.resources.getIdentifier(
            storeList.stores[0].url,
            "drawable",
            mContext!!.getPackageName()
        )
        val image: Drawable = mContext!!.resources.getDrawable(resId, null)
        binding.storeBanner.setImageDrawable(image)
        productListAdaptor = ProductListAdaptor(productList.products)
        val layoutManager = LinearLayoutManager(mContext)
        binding.productsListView.layoutManager = layoutManager
        binding.productsListView.adapter = productListAdaptor

        binding.orderSummaryBtn.setOnClickListener(View.OnClickListener {
            val productsToBeOrder = productListAdaptor.getToBeOrderedProducts()

            val orderSummaryScreenFragment = OrderSummaryScreenFragment()
            val bundle = Bundle()
            bundle.putSerializable(Constants.ORDER_SUMMARY_KEY, productsToBeOrder as Serializable)
            orderSummaryScreenFragment.arguments = bundle
            replaceFragment(
                orderSummaryScreenFragment,
                OrderSummaryScreenFragment::class.java.getSimpleName(),
                true
            )
        })
        return binding.root
    }
}