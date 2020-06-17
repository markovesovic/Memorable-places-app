package rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.fragment

import android.content.SharedPreferences
import android.location.*
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.android.ext.android.inject
import rs.raf.projekat2.marko_vesovic_rn2417.R

class MapsFragment : Fragment() {

    private lateinit var locationManager: LocationManager
    private val sharedPreferences: SharedPreferences by inject<SharedPreferences>()

    private val onMapReadyCallback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.setOnMarkerClickListener(onMarkerClickListener)



        val lon = sharedPreferences.getFloat("lon", 20.5F)
        val lat = sharedPreferences.getFloat("lat", 40.8F)
        val currentLocation = LatLng(lat.toDouble(), lon.toDouble())

        placeMarkerOnMap(lat, lon, googleMap)
//        googleMap.addMarker(MarkerOptions().position(currentLocation).title("Current location"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 12.0f))

    }

    private val onMarkerClickListener = GoogleMap.OnMarkerClickListener {
        false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(onMapReadyCallback)
    }

    private fun placeMarkerOnMap(lat: Float, lon: Float, map: GoogleMap) {
        val location = LatLng(lat.toDouble(), lon.toDouble())
        val markerOptions = MarkerOptions().position(location)

        val titleStr = getAddress(location)
        markerOptions.title(titleStr)

        map.addMarker(markerOptions)
    }

    private fun getAddress(latLng: LatLng): String {
        val geoCoder = Geocoder(this.activity)
        val addresses: List<Address>?

        addresses = geoCoder.getFromLocation(latLng.latitude, latLng.longitude, 1)

//        if (null != addresses && addresses.isNotEmpty()) {
//            address = addresses[0]
//            for (i in 0 until address.maxAddressLineIndex) {
//                addressText += if (i == 0) address.getAddressLine(i) else "\n" + address.getAddressLine(i)
//            }
//        }
//        Timber.e("Ovo je iz getAddress $addressText")
//        Timber.e("Ovo je iz getAddress2 $address")
        return addresses[0].getAddressLine(0)
    }

}