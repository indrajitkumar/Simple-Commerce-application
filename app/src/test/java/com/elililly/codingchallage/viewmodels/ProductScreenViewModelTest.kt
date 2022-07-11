package com.elililly.codingchallage.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.elililly.codingchallage.models.Products
import com.elililly.codingchallage.models.Stores
import com.elililly.codingchallage.utils.getJsonDataFromAsset
import com.google.gson.JsonParser
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.reflect.internal.WhiteboxImpl
import java.io.File
import java.io.FileNotFoundException
import java.io.FileReader

@PrepareForTest(ProductScreenViewModel::class)
@RunWith(MockitoJUnitRunner::class)
class ProductScreenViewModelTest {
    private lateinit var productScreenViewModel: ProductScreenViewModel

    @Mock
    private lateinit var mockContext: Application

    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var products: Products

    @Mock
    private lateinit var stores: Stores

    @Mock
    private lateinit var productsMutableLiveData: MutableLiveData<Products>

    @Mock
    private lateinit var storesMutableLiveData: MutableLiveData<Stores>


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Mockito.`when`(mockContext.applicationContext).thenReturn(context)
        getJsonDataFromAsset(context,"")
        productScreenViewModel = ProductScreenViewModel(mockContext)
    }

    @Test
    fun `verify object initiated`() {
        Assert.assertNotNull(productScreenViewModel)
    }

    @Test
    fun `verify setProduct response`() {
        WhiteboxImpl.invokeMethod<Any>(productScreenViewModel, "setProducts", products)
        productScreenViewModel.getProductResponse()
        Mockito.verify(productsMutableLiveData).postValue(products)
    }

    @Test
    fun `verify setStore response`() {
        WhiteboxImpl.invokeMethod<Any>(productScreenViewModel, "setStores",stores )
        productScreenViewModel.getProductResponse()
        Mockito.verify(storesMutableLiveData).postValue(stores)
    }

    private fun readProductsResponse(): String? {
        val path = "src/test/rs/Products.json"
        val file = File(path)
        return try {
            val jsonObject = JsonParser.parseReader(FileReader(file)).asJsonObject
            jsonObject.toString()
        } catch (e: FileNotFoundException) {
            null
        }
    }
}