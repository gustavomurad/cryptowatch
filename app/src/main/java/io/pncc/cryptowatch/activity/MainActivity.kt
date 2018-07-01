package io.pncc.cryptowatch.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import io.pncc.cryptowatch.R
import io.pncc.cryptowatch.adapter.CryptocurrencyPageAdapter


class MainActivity : AppCompatActivity() {
    private lateinit var mTabLayout: TabLayout
    private lateinit var mViewPager: ViewPager
    private lateinit var mPagerAdapter: CryptocurrencyPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewPager = findViewById(R.id.viewpager)
        mTabLayout = findViewById(R.id.tabLayout)

        mPagerAdapter = CryptocurrencyPageAdapter(supportFragmentManager, mTabLayout.tabCount)

        mViewPager.adapter = mPagerAdapter

        mViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(mTabLayout))
        mTabLayout.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(mViewPager))
    }
}