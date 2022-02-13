package com.example.adesh.data.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Website")
data class WebSiteModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    val id: Int?,
    @ColumnInfo(name = "websiteName")
    val websiteName: String,

    @ColumnInfo(name = "websiteUrl")
    val websiteUrl: String,

    @ColumnInfo(name = "createdOn")
    val createdOn: String,

    @ColumnInfo(name = "lastVisited")
    val lastVisited: String,
)