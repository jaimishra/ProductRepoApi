package com.example.demo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ItemResponse::class],
                     version = 1)
@TypeConverters(Converters::class)
abstract class FavoriteDataBaseItem : RoomDatabase() {
    abstract fun getItemDao() : FavItemDao

    companion object {
        @Volatile private var INSTANCE: FavoriteDataBaseItem? = null
        private val Lock = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(Lock) {
            INSTANCE ?: buildDatabase(context).also {
                INSTANCE = it
            }
        }

        private  fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            FavoriteDataBaseItem::class.java,
            "items.db"
        ).build()

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
