package io.pncc.cryptowatch

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import io.pncc.cryptowatch.database.AppDatabase
import io.pncc.cryptowatch.database.Holdings
import io.pncc.cryptowatch.utilities.InjectorUtils
import io.pncc.cryptowatch.utilities.runOnIoThread
import io.pncc.cryptowatch.viewmodels.MarketListViewModel
import java.text.SimpleDateFormat
import java.util.*


class HoldingsAddFragment: AppCompatActivity(){
    private lateinit var mHoldingsAddCryptocurrency: AutoCompleteTextView
    private lateinit var mHoldingsAddBack: ImageView
    private lateinit var mHoldingAddSave: Button
    private lateinit var mHoldingsAddBuyDate: TextView
    private lateinit var mHoldingsAddBuyPrice: TextView
    private lateinit var mHoldingsAddAmountBought: TextView
    private lateinit var mMarketViewModel: MarketListViewModel
    private var myCalendar = Calendar.getInstance()

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


        val date = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel()
        }

        mHoldingsAddBuyDate.setOnClickListener {
            DatePickerDialog(this, date, myCalendar
            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }
        mHoldingsAddBuyDate.setOnFocusChangeListener {view: View?, b: Boolean ->
            if(b){
                DatePickerDialog(this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show()
            }

        }

        mHoldingsAddBack.setOnClickListener {
            finish()
        }

        mHoldingAddSave.setOnClickListener {
            val amount = mHoldingsAddAmountBought.text.toString().toDouble()
            val price = mHoldingsAddBuyPrice.text.toString().toDouble()
            val db: AppDatabase = AppDatabase.getInstance(context)
            runOnIoThread {
                db.holdingsDao().insert(Holdings(2, amount, price, mHoldingsAddBuyDate.text.toString()))
            }
            finish()
        }
    }

    private fun updateLabel() {
        val myFormat = "MM/dd/yyyy" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.US)

        mHoldingsAddBuyDate.text = sdf.format(myCalendar.time)
    }
}