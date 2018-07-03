package io.pncc.cryptowatch.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "holdings")
data class Holdings(

        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = "id")
        var id: Int,

        @Ignore
        var name: String,

        @Ignore
        var symbol: String,

        @Ignore
        var price: Double,

        @Ignore
        var percentChange24h: Double)