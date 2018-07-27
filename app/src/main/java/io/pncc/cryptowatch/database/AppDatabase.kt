package io.pncc.cryptowatch.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import io.pncc.cryptowatch.constants.Constants


@Database(entities = [Holdings::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun holdingsDao(): HoldingsDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, Constants.DATABASE_NAME).build()
        }
    }
}

