package com.example.adesh.ui.main.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.adesh.R
import com.example.adesh.data.local.WebsiteDatabase
import com.example.adesh.data.model.WebSiteModel
import com.example.adesh.data.repo.MainRepo
import com.example.adesh.databinding.ActivityMainBinding
import com.example.adesh.ui.base.ViewModelFactory
import com.example.adesh.ui.main.adapter.WebSiteAdapter
import com.example.adesh.ui.main.viewModel.MainViewModel
import com.example.adesh.utils.Const.pk
import com.example.adesh.utils.Const.website_last_visited
import com.example.adesh.utils.Const.website_name
import com.example.adesh.utils.Const.website_url
import com.example.adesh.utils.Utils.getCurrentData
import com.example.adesh.utils.Utils.showDatePickerDialog
import com.example.adesh.utils.Utils.showToast
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    var websiteList: List<WebSiteModel>? = null

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        init()
        setObserver()
        setListener()


    }

    private fun setObserver() {
        viewModel.insertRes.observe(this) {
            it?.let {
                if (it > 0) {
                    showToast(this, "Website inserted successfully")
                } else
                    showToast(this, "Website not inserted")
            }
        }

        viewModel.updateRes.observe(this) {
            it?.let {
                if (it > 0) {
                    //           showToast(this, "last visited date updated successfully")
                } else {//         showToast(this, "last visited date not updated  ")
                }
            }
        }
        viewModel.allWebsites.observe(this) {
            it?.let {
                websiteList = it
                val adapter = WebSiteAdapter(it, itemClick = {
                    openWebsite(it)
                }, onCardClick = {
                    startActivity(it)
                })
                binding.rvWebsiteAdapter.adapter = adapter
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setListener() {
        binding.btSortByName.setOnClickListener {
            websiteList?.let { it ->
                val sorterData = it.sortedBy { website ->
                    website.websiteName
                }
                val adapter = WebSiteAdapter(sorterData, itemClick = {
                    openWebsite(it)
                }, onCardClick = {
                    startActivity(it)
                })
                binding.rvWebsiteAdapter.adapter = adapter
            }
        }
        binding.btSortByDate.setOnClickListener {
            websiteList?.let { websites ->
                val dates = arrayListOf<String>()
                websites.forEach {
                    dates.add(it.lastVisited)
                }
                val dateTimeFormatter: DateTimeFormatter =
                    DateTimeFormatter.ofPattern("dd-MM-yyyy")

                val result = dates.sortedByDescending {
                    LocalDate.parse(it, dateTimeFormatter)
                }.reversed()
                val sortedWebsite = arrayListOf<WebSiteModel>()
                for (date in result) {
                    for (item in websites) {
                        if (item.lastVisited == date)
                            sortedWebsite.add(item)
                    }
                }
                val adapter = WebSiteAdapter(sortedWebsite, itemClick = {
                    openWebsite(it)
                }, onCardClick = {
                    startActivity(it)
                })
                binding.rvWebsiteAdapter.adapter = adapter

            }
        }
        binding.etLastVisited.setOnClickListener {
            showDatePickerDialog(this, binding.etLastVisited)
        }
        binding.btAddData.setOnClickListener {
            viewModel.insertWebSite(
                WebSiteModel(
                    null,
                    binding.etWebSiteName.text.toString(),
                    binding.etWebSiteUrl.text.toString(),
                    getCurrentData(),
                    binding.etLastVisited.text.toString()
                )
            )


        }
    }

    private fun init() {
        val database = WebsiteDatabase.getInstance(this).getDao()
        val repo = MainRepo(database)
        viewModel=
            ViewModelProvider(this, factory = ViewModelFactory(repo)
            )[MainViewModel::class.java]
    }

    private fun openWebsite(model: WebSiteModel) {
        val webView = WebView(this)
        webView.loadUrl(model.websiteUrl)
        val dialog: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this)
        dialog.setView(webView)
        webView.webViewClient = object : WebViewClient() {
            override fun onReceivedError(
                view: WebView,
                errorCode: Int,
                description: String,
                failingUrl: String
            ) {
                webView.loadUrl(model.websiteUrl)
            }

        }
        model.id?.let { pk ->
            viewModel.updateLastStatus(getCurrentData(), pk)
        }
        dialog.setPositiveButton("Okay", null)
        dialog.show()
    }

    private fun startActivity(model: WebSiteModel) {
        val bundle = Bundle()
        bundle.putString(pk, model.id.toString())
        bundle.putString(website_name, model.websiteName.toString())
        bundle.putString(website_url, model.websiteUrl.toString())
        bundle.putString(website_last_visited, model.lastVisited.toString())
        val intent = Intent(this, Edit::class.java).putExtras(bundle)
        startActivity(intent)
        finish()
    }

    override fun onRestart() {
        super.onRestart()
    }
}