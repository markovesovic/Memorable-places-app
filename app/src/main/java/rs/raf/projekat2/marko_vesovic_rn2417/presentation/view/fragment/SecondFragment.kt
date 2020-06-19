package rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_second.*
import rs.raf.projekat2.marko_vesovic_rn2417.R
import rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.viewpager.SecondFragmentPagerAdapter

class SecondFragment: Fragment(R.layout.fragment_second) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initTabs()
    }

    private fun initTabs() {
        viewPager.offscreenPageLimit = 2
        viewPager.adapter = SecondFragmentPagerAdapter(childFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

}