package io.pncc.cryptowatch.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import io.pncc.cryptowatch.HoldingsFragment
import io.pncc.cryptowatch.MarketFragment

class CryptocurrencyPageAdapter(fm: FragmentManager?, private val tabCount: Int) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> HoldingsFragment()
            1 -> MarketFragment()
            else -> throw IllegalArgumentException("Unknown position")
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}
