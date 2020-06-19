package rs.raf.projekat2.marko_vesovic_rn2417.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.marko_vesovic_rn2417.data.model.MemorablePlace

interface MemorablePlaceRepository {

    fun getAll(): Observable<List<MemorablePlace>>
    fun insert(memorablePlace: MemorablePlace): Completable

}