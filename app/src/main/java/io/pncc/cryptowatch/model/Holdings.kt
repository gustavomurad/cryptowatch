package io.pncc.cryptowatch.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import java.sql.Date

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
        var percentChange: Double,

        @ColumnInfo(name = "cryptoAmount")
        var cryptoAmount: Double,

        @ColumnInfo(name = "payedAmount")
        var payedAmount: Double,

        @ColumnInfo(name = "buyDate")
        var buyDate: Date
)