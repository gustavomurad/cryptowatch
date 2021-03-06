package io.pncc.cryptowatch

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.facebook.stetho.Stetho
import io.pncc.cryptowatch.adapters.CryptocurrencyPageAdapter

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

        mPagerAdapter = CryptocurrencyPageAdapter(supportFragmentManager, mTabLayout.tabCount)

        mViewPager.adapter = mPagerAdapter

        mViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(mTabLayout))
        mTabLayout.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(mViewPager))
    }
}