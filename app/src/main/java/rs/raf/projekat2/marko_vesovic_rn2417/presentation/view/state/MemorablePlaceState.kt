package rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.state

import rs.raf.projekat2.marko_vesovic_rn2417.data.model.MemorablePlace

sealed class MemorablePlaceState {
    object Loading: MemorablePlaceState()
    object DataFetched: MemorablePlaceState()
    data class Success(val memorablePlaces: List<MemorablePlace>): MemorablePlaceState()
    data class SuccessMessage(val message: String): MemorablePlaceState()
    data class Error(val message: String): MemorablePlaceState()
}