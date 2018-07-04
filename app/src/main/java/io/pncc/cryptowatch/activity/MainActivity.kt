package io.pncc.cryptowatch.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import io.pncc.cryptowatch.R
import io.pncc.cryptowatch.adapter.CryptocurrencyPageAdapter
import io.pncc.cryptowatch.model.Holdings
import io.pncc.cryptowatch.viewmodel.HoldingsViewModel
import java.sql.Date


class MainActivity : AppCompatActivity() {
    private lateinit var mTabLayout: TabLayout
    private lateinit var mViewPager: ViewPager
    private lateinit var mPagerAdapter: CryptocurrencyPageAdapter
    private lateinit var mHoldingsViewModel: HoldingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewPager = findViewById(R.id.viewpager)
        mTabLayout = findViewById(R.id.tabLayout)

        mHoldingsViewModel = ViewModelProviders.of(this).get(HoldingsViewModel::class.java)
        mHoldingsViewModel.insert(Holdings(1, "PNCC Coin", "BTC", 2500.01, 22.5, 0.5, 1500.00, Date(1L)))

        mHoldingsViewModel.getAllHoldings().observe(this, object : Observer<List<Holdings>> {
            override fun onChanged(words: List<Holdings>?) {
                // Update the cached copy of the words in the adapter.
                //adapter.setWords(words)

            }
        })

        mPagerAdapter = CryptocurrencyPageAdapter(supportFragmentManager, mTabLayout.tabCount)

        mViewPager.adapter = mPagerAdapter

        mViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(mTabLayout))
        mTabLayout.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(mViewPager))



       // val db: CryptocurrencyRoomDatabase = Room.databaseBuilder(applicationContext, CryptocurrencyRoomDatabase::class.java, "database-name").build()

        //db.holdingsDao().insert()
    }
}


//https://github.com/googlesamples/android-sunflower/blob/master/app/src/main/java/com/google/samples/apps/sunflower/data/PlantRepository.kt

//https://codelabs.developers.google.com/codelabs/android-room-with-a-view/#11