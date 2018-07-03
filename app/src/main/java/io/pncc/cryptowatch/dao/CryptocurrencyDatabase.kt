package io.pncc.cryptowatch.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import io.pncc.cryptowatch.model.Holdings

@Database(version = 1, entities = [Holdings::class])
abstract class CryptocurrencyDatabase: RoomDatabase(){

    abstract fun holdingsDao():HoldingsDao
}
