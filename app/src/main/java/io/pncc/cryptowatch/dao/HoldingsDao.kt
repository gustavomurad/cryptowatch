package io.pncc.cryptowatch.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import io.pncc.cryptowatch.model.Holdings

@Dao
interface HoldingsDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(holdings: Holdings)

    @Update
    fun update(holdings: Holdings)

    @Delete
    fun delete(holdings: Holdings)

    @Query("SELECT * FROM holdings ORDER BY id ASC")
    fun getHoldings(): LiveData<List<Holdings>>


}