package com.example.adesh.ui.main.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adesh.data.model.WebSiteModel
import com.example.adesh.data.repo.EditRepo
import kotlinx.coroutines.launch

class EditViewModel constructor(private val editRepo: EditRepo) : ViewModel() {

    private var _updateRes = MutableLiveData<Int>()
    val updateRes: LiveData<Int> = _updateRes

    fun updateDetails(webSiteModel: WebSiteModel) = viewModelScope.launch {
        _updateRes.value = editRepo.updateWebsiteData(webSiteModel)
    }
}