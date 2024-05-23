package com.example.recycleview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recycleview.model.Data
import com.example.recycleview.model.ProductClass

class ProductViewModel : ViewModel() {
    private val _products = MutableLiveData<ProductClass>()
    val products : LiveData<ProductClass> get() = _products

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val repository = ProductRepo()

    fun fetchProducts(){
        repository.getProducts{productList, throwable ->
            if(productList != null){
                _products.postValue(productList)
            }
            else{
                _error.postValue(throwable?.message)
            }
        }
    }
}