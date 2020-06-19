package rs.raf.projekat2.marko_vesovic_rn2417.data.model

import com.google.android.gms.maps.model.LatLng
import java.util.*

data class MemorablePlace(
    val id: Long,
    val title: String,
    val content: String,
    val location: LatLng,
    val dateAdded: Date
)