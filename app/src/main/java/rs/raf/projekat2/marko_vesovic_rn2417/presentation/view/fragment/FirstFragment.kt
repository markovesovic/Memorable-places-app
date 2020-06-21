package rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_first.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.marko_vesovic_rn2417.R
import rs.raf.projekat2.marko_vesovic_rn2417.data.model.MemorablePlace
import rs.raf.projekat2.marko_vesovic_rn2417.presentation.contract.MemorablePlaceContract
import rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.state.MemorablePlaceState
import rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.viewpager.FirstFragmentPagerAdapter
import rs.raf.projekat2.marko_vesovic_rn2417.presentation.viewmodel.MemorablePlaceViewModel
import timber.log.Timber
import java.util.*


class FirstFragment: Fragment(R.layout.fragment_first) {

    private val memorablePlaceViewModel: MemorablePlaceContract.ViewModel by sharedViewModel<MemorablePlaceViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    private fun init() {
        initMap()
        initListeners()
        initObservers()
    }

    private fun initMap() {
        mapContainer.offscreenPageLimit = 1
        mapContainer.adapter = FirstFragmentPagerAdapter(childFragmentManager)
    }

    private fun initListeners() {
        cancel.setOnClickListener {
            title.setText("")
            content.setText("")
        }
        save.setOnClickListener {
            val title = title.text.toString()
            val content = content.text.toString()
            val date = Date()
            val mapView = (mapContainer.adapter as FirstFragmentPagerAdapter).getCurrentFragment() as MapsFragment
            val latLng = mapView.latLng
            val memorablePlace = MemorablePlace(0, title, content, latLng, date)

            if(title == "") {
                Toast.makeText(this.context, "Morate uneti naslov!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(content == "") {
                Toast.makeText(this.context, "Morate uneti opis!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            memorablePlaceViewModel.insertMemorablePlace(memorablePlace)
            Timber.e("Pozvan upis u bazu, title $title")
        }
    }

    private fun initObservers() {
        memorablePlaceViewModel.memorablePlaceState.observe(this.viewLifecycleOwner, androidx.lifecycle.Observer {
            renderState(it)
        })
    }

    private fun renderState(state: MemorablePlaceState) {
        when (state) {
            is MemorablePlaceState.SuccessMessage -> Toast.makeText(this.context, state.message, Toast.LENGTH_LONG).show()
            is MemorablePlaceState.Error -> Toast.makeText(this.context, state.message, Toast.LENGTH_SHORT).show()
        }
    }
}