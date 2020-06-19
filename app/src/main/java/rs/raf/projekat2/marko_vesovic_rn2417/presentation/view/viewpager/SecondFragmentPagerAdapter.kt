package rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.fragment.AllLocationsListFragment
import rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.fragment.AllLocationsMapFragment
import rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.fragment.MapsFragment

class SecondFragmentPagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        private const val ITEM_COUNT = 2
        const val LEFT_FRAGMENT = 0
        const val RIGHT_FRAGMENT = 1
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            LEFT_FRAGMENT -> AllLocationsMapFragment()
            else -> AllLocationsListFragment()
        }
    }

    override fun getCount(): Int {
        return ITEM_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            LEFT_FRAGMENT -> "Levi fragment"
            else -> "Desni fragment"
        }
    }

}