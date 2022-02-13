package com.example.adesh.ui.main.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adesh.data.model.WebSiteModel
import com.example.adesh.data.repo.MainRepo
import kotlinx.coroutines.launch

class MainViewModel constructor(private val mainRepo: MainRepo) : ViewModel() {

    private var _insertRes = MutableLiveData<Long>()
    val insertRes: LiveData<Long> = _insertRes


    private var _updateStatusRes = MutableLiveData<Int>()
    val updateRes: LiveData<Int> = _updateStatusRes

    val allWebsites = mainRepo.getAllWebsites()
    fun insertWebSite(webSiteModel: WebSiteModel) = viewModelScope.launch {
        _insertRes.value = mainRepo.insertWebSiteData(webSiteModel)
    }

    fun updateLastStatus(date: String, Id: Int) = viewModelScope.launch {
        _updateStatusRes.value = mainRepo.updateLastVisited(date = date, id = Id)
    }
}