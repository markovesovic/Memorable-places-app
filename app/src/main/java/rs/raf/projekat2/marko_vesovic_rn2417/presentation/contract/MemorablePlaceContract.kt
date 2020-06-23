package rs.raf.projekat2.marko_vesovic_rn2417.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.projekat2.marko_vesovic_rn2417.data.model.MemorablePlace
import rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.state.MemorablePlaceState

interface MemorablePlaceContract {

    interface ViewModel {
        val memorablePlaceState: LiveData<MemorablePlaceState>
        fun getAllMemorablePlacesAscending(s: String)
        fun getAllMemorablePlacesDescending(s: String)
        fun insertMemorablePlace(memorablePlace: MemorablePlace)
        fun updateMemorablePlace(memorablePlace: MemorablePlace)
        fun deleteMemorablePlace(memorablePlace: MemorablePlace)
        fun deleteAll()
        fun getAllMemorablePlacesByFilterAscending(filter: String)
        fun getAllMemorablePlacesByFilterDescending(filter: String)
    }
}