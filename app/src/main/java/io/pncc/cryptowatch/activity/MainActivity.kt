package io.pncc.cryptowatch.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.facebook.stetho.Stetho
import io.pncc.cryptowatch.R
import io.pncc.cryptowatch.adapter.CryptocurrencyPageAdapter
import io.pncc.cryptowatch.database.AppDatabase
import io.pncc.cryptowatch.model.Holdings
import java.sql.Date


class MainActivity : AppCompatActivity() {
    private lateinit var mTabLayout: TabLayout
    private lateinit var mViewPager: ViewPager
    private lateinit var mPagerAdapter: CryptocurrencyPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Stetho.initializeWithDefaults(this)

        setContentView(R.layout.activity_main)
        mViewPager = findViewById(R.id.viewpager)
        mTabLayout = findViewById(R.id.tabLayout)

        val db: AppDatabase = AppDatabase.getInstance(this)
        db.holdingsDao().insert(Holdings(1, 2500.01, 22.5, "13/04/1976"))
        //db.holdingsDao().insert(Holdings(1, "PNCC Coin"))

        mPagerAdapter = CryptocurrencyPageAdapter(supportFragmentManager, mTabLayout.tabCount)

        mViewPager.adapter = mPagerAdapter

        mViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(mTabLayout))
        mTabLayout.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(mViewPager))



       // val db: AppDatabase = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name").build()

        //db.holdingsDao().insert()
    }
}


//https://github.com/googlesamples/android-sunflower/blob/master/app/src/main/java/com/google/samples/apps/sunflower/data/PlantRepository.kt

//https://codelabs.developers.google.com/codelabs/android-room-with-a-view/#11