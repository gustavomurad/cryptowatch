package io.pncc.cryptowatch

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import io.pncc.cryptowatch.database.AppDatabase
import io.pncc.cryptowatch.database.Holdings
import io.pncc.cryptowatch.utilities.InjectorUtils
import io.pncc.cryptowatch.utilities.runOnIoThread
import io.pncc.cryptowatch.viewmodels.MarketListViewModel
import java.lang.Double


class HoldingsAddFragment: AppCompatActivity(){
    private lateinit var mHoldingsAddCryptocurrency: AutoCompleteTextView
    private lateinit var mHoldingsAddBack: ImageView
    private lateinit var mHoldingAddSave: Button
    private lateinit var mHoldingsAddBuyDate: TextView
    private lateinit var mHoldingsAddBuyPrice: TextView
    private lateinit var mHoldingsAddAmountBought: TextView
    private lateinit var mMarketViewModel: MarketListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.holdings_add)

        val factory = InjectorUtils.provideMarketViewModelFactory()
        val context = this.applicationContext

        mHoldingsAddBack = findViewById(R.id.holdings_add_back)
        mHoldingAddSave = findViewById(R.id.holdings_add_save)
        mHoldingsAddCryptocurrency = findViewById(R.id.holdings_add_cryptocurrency)
        mHoldingsAddBuyDate = findViewById(R.id.holdings_add_buyDate)
        mHoldingsAddBuyPrice = findViewById(R.id.holdings_add_buyPrice)
        mHoldingsAddAmountBought = findViewById(R.id.holdings_add_amountBought)
        mMarketViewModel = ViewModelProviders.of(this, factory).get(MarketListViewModel::class.java)

        mHoldingsAddBack.setOnClickListener {
            finish()
        }

        mHoldingAddSave.setOnClickListener {
            val amount = java.lang.Double.parseDouble(mHoldingsAddAmountBought.text.toString())
            val price = Double.parseDouble(mHoldingsAddBuyPrice.text.toString())
            val db: AppDatabase = AppDatabase.getInstance(context)
            runOnIoThread {
                db.holdingsDao().insert(Holdings(2, amount, price, mHoldingsAddBuyDate.text.toString()))
            }
            finish()
        }
    }
}