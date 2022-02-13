package com.example.adesh.ui.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.example.adesh.data.local.WebsiteDatabase
import com.example.adesh.data.model.WebSiteModel
import com.example.adesh.data.repo.EditRepo
import com.example.adesh.databinding.ActivityEditBinding
import com.example.adesh.ui.base.ViewModelFactory
import com.example.adesh.ui.main.viewModel.EditViewModel
import com.example.adesh.utils.Const.pk
import com.example.adesh.utils.Const.website_last_visited
import com.example.adesh.utils.Const.website_name
import com.example.adesh.utils.Const.website_url
import com.example.adesh.utils.Utils

class Edit : AppCompatActivity() {
    private lateinit var viewModel: EditViewModel
    private lateinit var binding: ActivityEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        init()
        setObserver()
        setListener()
    }

    private fun setListener() {
        binding.etLastVisited.setOnClickListener {
            Utils.showDatePickerDialog(this, binding.etLastVisited)
        }
        viewModel.updateRes.observe(this) {
            it?.let {
                if (it > 0) {
                    Utils.showToast(this, "Website data updated successfully")
                    val intent = Intent()
                    setResult(75, intent)
                } else
                    Utils.showToast(this, "Website data not updated")
            }
        }
    }

    private fun setObserver() {
        val bundle: Bundle? = intent.extras

        binding.btUpdate.setOnClickListener {
            viewModel.updateDetails(
                WebSiteModel(
                    bundle?.getString(pk)?.toInt(),
                    binding.etWebSiteName.text.toString(),
                    binding.etWebSiteUrl.text.toString(),
                    Utils.getCurrentData(),
                    binding.etLastVisited.text.toString()
                )
            )
        }
    }

    private fun init() {
        val bundle: Bundle? = intent.extras
        val database = WebsiteDatabase.getInstance(this).getDao()
        val repo = EditRepo(database)
        viewModel =
            ViewModelProvider(
                this,
                factory = ViewModelFactory(repo)
            )[EditViewModel::class.java]

        binding.etLastVisited.setText(bundle?.getString(website_last_visited) ?: "")
        binding.etWebSiteName.setText(bundle?.getString(website_name) ?: "")
        binding.etWebSiteUrl.setText(bundle?.getString(website_url) ?: "")
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}