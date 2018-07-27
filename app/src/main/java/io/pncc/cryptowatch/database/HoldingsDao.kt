package io.pncc.cryptowatch.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface HoldingsDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(holdings: Holdings)

    @Update
    fun update(holdings: Holdings)

    @Delete
    fun delete(holdings: Holdings)

    @Query("SELECT * FROM holdings")
    fun getHoldings(): LiveData<List<Holdings>>


}