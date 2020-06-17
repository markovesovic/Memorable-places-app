package rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.fragment.FirstFragment
import rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.fragment.SecondFragment

class MainActivityPagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        private const val ITEM_COUNT = 2
        const val FIRST_FRAGMENT = 0
        const val SECOND_FRAGMENT = 1
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            FIRST_FRAGMENT -> FirstFragment()
            else -> SecondFragment()
        }
    }

    override fun getCount(): Int {
        return ITEM_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            FIRST_FRAGMENT -> "Home"
            else -> "Saved"
        }
    }
}