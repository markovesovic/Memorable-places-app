package rs.raf.projekat2.marko_vesovic_rn2417.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.marko_vesovic_rn2417.data.model.MemorablePlace

interface MemorablePlaceRepository {
    fun getAllAscending(): Observable<List<MemorablePlace>>
    fun getAllDescending(): Observable<List<MemorablePlace>>
    fun insert(memorablePlace: MemorablePlace): Completable
    fun update(memorablePlace: MemorablePlace): Completable
    fun delete(memorablePlace: MemorablePlace): Completable
    fun deleteAll(): Completable
    fun getAllByFilterAscending(filter: String): Observable<List<MemorablePlace>>
    fun getAllByFilterDescending(filter: String): Observable<List<MemorablePlace>>
}