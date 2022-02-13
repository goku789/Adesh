package com.example.adesh.data.repo

import com.example.adesh.data.local.WebsiteDao
import com.example.adesh.data.model.WebSiteModel

class EditRepo(private val dao: WebsiteDao) : BaseRepo() {

    suspend fun updateWebsiteData(webSiteModel: WebSiteModel): Int {
        return dao.updateWebSiteData(
            lastVisitDate = webSiteModel.lastVisited,
            webSiteName = webSiteModel.websiteName,
            websiteUrl = webSiteModel.websiteUrl,
            Id = webSiteModel.id?:0
        )
    }

}
