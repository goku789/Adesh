package com.example.adesh.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.adesh.data.repo.BaseRepo
import com.example.adesh.data.repo.EditRepo
import com.example.adesh.data.repo.MainRepo
import com.example.adesh.ui.main.viewModel.EditViewModel
import com.example.adesh.ui.main.viewModel.MainViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory constructor(private val repo: BaseRepo) :
ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java))
            MainViewModel(repo as MainRepo) as T
        else if (modelClass.isAssignableFrom(EditViewModel::class.java))
            EditViewModel(repo as EditRepo) as T
        else throw IllegalArgumentException("View Model Not Found")
    }


}
