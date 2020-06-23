package rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_edit_location.*
import rs.raf.projekat2.marko_vesovic_rn2417.R
import rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.viewpager.EditLocationPagerAdapter
import timber.log.Timber
import java.util.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class EditLocationActivity: AppCompatActivity(R.layout.activity_edit_location) {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        init()
    }

    private fun init() {
//        initMap()
        initListeners()
    }

    private fun initListeners() {
        var id: Long = 0
        var title: String = "Mare care title"
        var content: String = "Mare care content "
        var dateAdded: Long = 0
        var lon: Double = 0.0
        var lat: Double = 0.0

        intent?.let {
            title = it.getStringExtra("title")
            content = it.getStringExtra("content")
            id = it.getLongExtra("id", 0L)
            dateAdded = it.getLongExtra("dateAdded", 0L)
            lon = it.getDoubleExtra("lon", 0.0)
            lat = it.getDoubleExtra("lat", 0.0)
        }
        titleEditText.setText(title)
        contentEditText.setText(content)
        dateTextView.text = Date(dateAdded).toString()

        cancelEditActivity.setOnClickListener {
            Timber.e("Poziva se cancel funkcija")
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
        saveEditActivity.setOnClickListener {
            Timber.e("Poziva se save funkcija")
            val returnTitle = titleEditText.text.toString()
            val returnContent = contentEditText.text.toString()
            val returnIntent = Intent()
            returnIntent.putExtra("title", returnTitle)
            returnIntent.putExtra("content", returnContent)
            returnIntent.putExtra("id", id)
            returnIntent.putExtra("dateAdded", dateAdded)
            returnIntent.putExtra("lon", lon)
            returnIntent.putExtra("lat", lat)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }

    }

    private fun initMap() {
        mapContainer.offscreenPageLimit = 1
        mapContainer.adapter = EditLocationPagerAdapter(supportFragmentManager)
    }

}