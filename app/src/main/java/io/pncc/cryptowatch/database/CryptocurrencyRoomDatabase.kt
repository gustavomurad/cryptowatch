package io.pncc.cryptowatch.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import io.pncc.cryptowatch.constants.Constants
import io.pncc.cryptowatch.dao.HoldingsDao
import io.pncc.cryptowatch.model.Holdings


@Database(version = 1, entities = [Holdings::class])
abstract class CryptocurrencyRoomDatabase : RoomDatabase() {
    abstract fun holdingsDao(): HoldingsDao

    companion object {
        @Volatile
        private var instance: CryptocurrencyRoomDatabase? = null

        fun getInstance(context: Context): CryptocurrencyRoomDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): CryptocurrencyRoomDatabase {
            return Room.databaseBuilder(context, CryptocurrencyRoomDatabase::class.java, Constants.DATABASE_NAME).build()
        }
    }
}

