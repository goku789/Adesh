package com.example.adesh.data.repo

import com.example.adesh.data.local.WebsiteDao
import com.example.adesh.data.model.WebSiteModel

class MainRepo(private val dao: WebsiteDao) : BaseRepo() {

    suspend fun insertWebSiteData(webSiteModel: WebSiteModel): Long {
        return dao.insertWebSite(webSiteModel)
    }
    suspend fun updateLastVisited(date:String,id:Int): Int {
        return dao.updateLastVisited(date,id)
    }
    fun getAllWebsites() = dao.getAllData()
}