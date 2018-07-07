package io.pncc.cryptowatch.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "holdings")
data class Holdings(
        @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id") var coinId: Int,
        var cryptoAmount: Double,
        var payedAmount: Double,
        var buyDate: String
){
    @Ignore var name: String = ""
    @Ignore var symbol: String = ""
    @Ignore var price: Double = 0.0
    @Ignore var percentChange: Double = 0.0

}