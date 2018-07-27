package io.pncc.cryptowatch.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
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
