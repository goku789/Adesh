package com.example.adesh.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.adesh.data.model.WebSiteModel

@Dao
interface WebsiteDao {
    @Insert
    suspend fun insertWebSite(webSiteModel: WebSiteModel): Long

    @Query("select * from Website")
    fun getAllData(): LiveData<List<WebSiteModel>>

    @Query("update Website set lastVisited=:lastVisitDate where Id=:Id")
    suspend fun updateLastVisited(lastVisitDate: String, Id: Int): Int


    @Query("update Website set lastVisited=:lastVisitDate ,websiteName=:webSiteName,websiteUrl=:websiteUrl  where Id=:Id")
    suspend fun updateWebSiteData(
        lastVisitDate: String,
        webSiteName: String,
        websiteUrl: String,
        Id: Int
    ): Int
}