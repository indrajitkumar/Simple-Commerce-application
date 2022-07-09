package com.elililly.codingchallage.screens.orderSummaryScreen

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.elililly.codingchallage.*
import com.elililly.codingchallage.databinding.OrdersummaryFragmentBinding
import com.elililly.codingchallage.models.Address
import com.elililly.codingchallage.models.OrderSubmit
import com.elililly.codingchallage.models.Product
import com.elililly.codingchallage.screens.BaseFragment
import com.elililly.codingchallage.utils.Constants
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class OrderSummaryScreenFragment : BaseFragment(), DialogListner {
    private lateinit var orderListAdaptor: OrderListAdaptor
    lateinit var productsToBeOrder: Map<Product, Int>
    lateinit var binding: OrdersummaryFragmentBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private var mContext: Context? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext)
        editor = sharedPreferences.edit()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = OrdersummaryFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        val arguments = arguments
        if (arguments != null && arguments.containsKey(Constants.ORDER_SUMMARY_KEY)) {
            productsToBeOrder =
                arguments.getSerializable(Constants.ORDER_SUMMARY_KEY) as Map<Product, Int>
            Log.d("inder", "Ordered List = " + productsToBeOrder)
            orderListAdaptor = OrderListAdaptor(productsToBeOrder)
            val layoutManager = LinearLayoutManager(mContext)
            binding.selectedProducts.layoutManager = layoutManager
            binding.selectedProducts.adapter = orderListAdaptor
        }


        binding.orderSummaryBtn.setOnClickListener(View.OnClickListener {
            val successDialog = SuccessDialog(mContext!!)
            successDialog.setDialogListner(this)
            CoroutineScope(Dispatchers.IO).launch {
                delay(TimeUnit.SECONDS.toMillis(3))

                val orderDoneRequest = getJsonDataFromAsset(mContext!!, "OrderDone.json")
                val orderSubmit = orderDoneRequest?.fromJson<OrderSubmit>()
//                orderSubmit?.data?.attributes?.address = binding.address
                orderSubmit?.data?.attributes?.orderId = (10000..100000000).random().toString()
                withContext(Dispatchers.Main) {
                    Log.i("TAG", "this will be called after 3 seconds")

                    successDialog.show(
                        "Congratulation",
                        "Your Order has been placed successfully, Enjoy",
                        orderSubmit?.data?.attributes?.orderId!!
                    )
                    binding.progressBarContainer.visibility = View.GONE
                    editor.putString(Constants.ORDER_SUCCESS, orderSubmit.json())
                    editor.apply()
                }
            }
            binding.progressBarContainer.visibility = View.VISIBLE
        })
        return binding.root
    }

//    private fun saveSuccessResponseLocally(): String {
//        var map = mutableMapOf<String, String>()
//        var mapData = mutableMapOf<String, String>()
//        mapData.put("order_id", (0..1000000).random().toString())
//        mapData.put("products", productsToBeOrder.json())
//        map.put("status", "success")
//        map.put("data", mapData.json().toString())
//
//        val jsonObject = map.json()
//        val decoder: Base64.Decoder = Base64.getDecoder()
//        val decoded = String(decoder.decode(jsonObject.toByteArray(StandardCharsets.UTF_8)))
//        Log.i("TAG", "Success : " + decoded)
//
//        editor.putString(Constants.ORDER_SUCCESS, jsonObject)
//        editor.apply()
//        return  jsonObject
//    }

    override fun onDialogDismiss() {
        val fm: FragmentManager = activity!!.supportFragmentManager
        fm.popBackStack()
    }
}