package io.pncc.cryptowatch

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.pncc.cryptowatch.adapters.HoldingsAdapter
import io.pncc.cryptowatch.database.AppDatabase
import io.pncc.cryptowatch.database.Holdings
import io.pncc.cryptowatch.utilities.InjectorUtils
import io.pncc.cryptowatch.utilities.runOnIoThread
import io.pncc.cryptowatch.viewmodels.HoldingsListViewModel

class HoldingsFragment : Fragment() {
    private lateinit var mHoldingsViewModel: HoldingsListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.tab_holdings_fragment, container, false)
        val context = context ?: return view
        val factory = InjectorUtils.provideHoldingsListViewModelFactory(context)

        mHoldingsViewModel = ViewModelProviders.of(this, factory).get(HoldingsListViewModel::class.java)

        val adapter = HoldingsAdapter(context)
        view.findViewById<RecyclerView>(R.id.recyclerViewHoldings).adapter = adapter
        view.findViewById<RecyclerView>(R.id.recyclerViewHoldings).layoutManager = LinearLayoutManager(context)
        subscribeUi(adapter)

        view.findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener {
            val db: AppDatabase = AppDatabase.getInstance(context)
            runOnIoThread {
                db.holdingsDao().insert(Holdings(2, 2500.01, 22.5, "13/04/1976"))
                //db.holdingsDao().insert(Holdings(1, "PNCC Coin"))
            }

        }

        return view
    }

    private fun subscribeUi(adapter: HoldingsAdapter) {
        mHoldingsViewModel.getHoldings().observe(viewLifecycleOwner, Observer { holdings ->
            if (holdings != null) adapter.setHoldings(holdings)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMarketCapData()
    }

    private fun getMarketCapData() {
        val db: AppDatabase = AppDatabase.getInstance(context!!)
        runOnIoThread {
            db.holdingsDao().insert(Holdings(1, 2500.01, 22.5, "13/04/1976"))
            //db.holdingsDao().insert(Holdings(1, "PNCC Coin"))
        }


    }


}