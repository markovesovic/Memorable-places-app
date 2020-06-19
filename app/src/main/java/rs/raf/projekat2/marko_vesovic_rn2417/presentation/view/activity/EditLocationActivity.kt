package rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_edit_location.*
import rs.raf.projekat2.marko_vesovic_rn2417.R
import rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.viewpager.FirstFragmentPagerAdapter

class EditLocationActivity: AppCompatActivity(R.layout.activity_edit_location) {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        init()
    }

    private fun init() {
        initMap()
    }

    private fun initMap() {
        mapContainer.offscreenPageLimit = 1
        mapContainer.adapter = FirstFragmentPagerAdapter(supportFragmentManager)
    }

}