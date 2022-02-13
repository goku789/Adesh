package com.example.adesh.data.local


import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.adesh.data.model.WebSiteModel


@Database(entities = [WebSiteModel::class], version = 1, exportSchema = false)
abstract class WebsiteDatabase : RoomDatabase() {
    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        TODO("Not yet implemented")
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("Not yet implemented")
    }

    override fun clearAllTables() {
        TODO("Not yet implemented")
    }

    abstract fun getDao(): WebsiteDao

    companion object {
        var instance: WebsiteDatabase? = null
        fun getInstance(context: Context): WebsiteDatabase {
            return if (instance != null)
                return instance!!
            else
                Room.databaseBuilder(context, WebsiteDatabase::class.java, "WebsiteDB").build()
        }
    }
}