package rs.raf.projekat2.marko_vesovic_rn2417.data.db.converters

import androidx.room.TypeConverter
import com.google.android.gms.maps.model.LatLng
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.koin.core.KoinComponent
import org.koin.core.get
import java.lang.reflect.Type

class LatLngConverter: KoinComponent {

    private val jsonAdapter: JsonAdapter<LatLng>

    init {
        val type: Type = Types.newParameterizedType(LatLng::class.java)
        val moshi: Moshi = get()
        jsonAdapter = moshi.adapter(type)
    }

    @TypeConverter
    fun fromLatLng(latLng: LatLng?): String? {
        return latLng.let {
            jsonAdapter.toJson(latLng)
        }
    }

    @TypeConverter
    fun toLatLng(json: String?): LatLng? {
        return json?.let {
            jsonAdapter.fromJson(json)
        }
    }

}