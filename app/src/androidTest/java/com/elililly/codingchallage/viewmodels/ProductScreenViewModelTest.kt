package com.elililly.codingchallage.viewmodels

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.gson.JsonParser
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File
import java.io.FileNotFoundException
import java.io.FileReader

@RunWith(AndroidJUnit4::class)
class ProductScreenViewModelTest {
    lateinit var productScreenViewModel: ProductScreenViewModel

//    @Mock
    lateinit var application: Application
    lateinit var spyProductScreenViewModel: ProductScreenViewModel

    @Before
    fun setUp() {
//        MockitoAnnotations.initMocks(this)
        val application: Application = ApplicationProvider.getApplicationContext<Application>()

        productScreenViewModel = ProductScreenViewModel(application)
//        spyProductScreenViewModel = Mockito.spy(productScreenViewModel)

    }

    @Test
    fun `verify`() {
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