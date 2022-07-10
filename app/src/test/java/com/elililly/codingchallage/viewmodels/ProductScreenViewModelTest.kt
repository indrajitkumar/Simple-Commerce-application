package com.elililly.codingchallage.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.gson.JsonParser
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.reflect.Whitebox
import java.io.File
import java.io.FileNotFoundException
import java.io.FileReader

@PrepareForTest(ProductScreenViewModel::class, AndroidViewModel::class)
@RunWith(PowerMockRunner::class)
class ProductScreenViewModelTest{
    lateinit var productScreenViewModel: ProductScreenViewModel
    @Mock
    lateinit var application: Application
    lateinit var spyProductScreenViewModel: ProductScreenViewModel
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        productScreenViewModel = ProductScreenViewModel(application)
        spyProductScreenViewModel = Mockito.spy(productScreenViewModel)

    }

    @Test
    fun `verify json read`(){
        Assert.assertNotNull(productScreenViewModel)
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