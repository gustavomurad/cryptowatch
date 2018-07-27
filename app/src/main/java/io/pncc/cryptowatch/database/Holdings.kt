package io.pncc.cryptowatch.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

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