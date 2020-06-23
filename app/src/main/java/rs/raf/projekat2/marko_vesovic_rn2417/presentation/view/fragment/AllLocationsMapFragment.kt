package rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.fragment

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.android.ext.android.inject
import rs.raf.projekat2.marko_vesovic_rn2417.R
import rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.state.MemorablePlaceState
import rs.raf.projekat2.marko_vesovic_rn2417.presentation.viewmodel.MemorablePlaceViewModel

class AllLocationsMapFragment : Fragment() {

    private val memorablePlaceViewModel: MemorablePlaceViewModel by inject<MemorablePlaceViewModel>()

    private var latLngs = mutableListOf<LatLng>()

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        memorablePlaceViewModel.memorablePlaceState.observe(viewLifecycleOwner, Observer {
            when(it) {
                is MemorablePlaceState.Success -> {
                    val mps = it.memorablePlaces
                    latLngs = mutableListOf<LatLng>()
                    mps.map {
                        latLngs.add(it.location)
                        googleMap.addMarker(MarkerOptions().position(it.location)
                            .title(it.title)
                            .snippet(it.title + System.lineSeparator()
                                    + it.content + System.lineSeparator()
                                    + "Lat: ${it.location.latitude}, Lon: ${it.location.longitude}"))
                    }
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mps[0].location, 12.0f))
                }
            }
        })
        memorablePlaceViewModel.getAllMemorablePlacesAscending("")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_all_locations_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}