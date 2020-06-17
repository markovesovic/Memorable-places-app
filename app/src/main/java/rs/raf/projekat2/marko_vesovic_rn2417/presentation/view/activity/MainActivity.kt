package rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import rs.raf.projekat2.marko_vesovic_rn2417.R
import rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.viewpager.MainActivityPagerAdapter

class MainActivity: AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        initViewPager()
        initBottomNavigation()
    }

    private fun initViewPager() {
        viewPager.offscreenPageLimit = 2
        viewPager.adapter = MainActivityPagerAdapter(supportFragmentManager)
    }

    private fun initBottomNavigation() {
        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.navigation_item_1 -> {
                    viewPager.currentItem = MainActivityPagerAdapter.FIRST_FRAGMENT
                }
                R.id.navigation_item_2 -> {
                    viewPager.currentItem = MainActivityPagerAdapter.SECOND_FRAGMENT
                }
            }
            true
        }
    }
}