package rs.raf.projekat2.marko_vesovic_rn2417.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.marko_vesovic_rn2417.data.datasource.local.MemorablePlaceDao
import rs.raf.projekat2.marko_vesovic_rn2417.data.model.MemorablePlace
import rs.raf.projekat2.marko_vesovic_rn2417.data.model.MemorablePlaceEntity

class MemorablePlaceRepositoryImplementation(
    private val localDataSource: MemorablePlaceDao): MemorablePlaceRepository {

    override fun getAllAscending(): Observable<List<MemorablePlace>> {
        return localDataSource.getAllAscending()
            .map {
                it.map {
                    MemorablePlace(
                        it.id,
                        it.title,
                        it.content,
                        it.location,
                        it.dateAdded
                    )
                }
            }
    }

    override fun getAllDescending(): Observable<List<MemorablePlace>> {
        return localDataSource.getAllDescending()
                .map {
                    it.map {
                        MemorablePlace(
                            it.id,
                            it.title,
                            it.content,
                            it.location,
                            it.dateAdded
                        )
                    }
                }
        }

    override fun insert(memorablePlace: MemorablePlace): Completable {
        val entity = MemorablePlaceEntity(
            memorablePlace.id,
            memorablePlace.title,
            memorablePlace.content,
            memorablePlace.location,
            memorablePlace.dateAdded
        )
        return localDataSource.insert(entity)
    }

    override fun update(memorablePlace: MemorablePlace): Completable {
        return localDataSource.update(
            memorablePlace.id,
            memorablePlace.title,
            memorablePlace.content,
            memorablePlace.location
        )
    }

    override fun getAllByFilterAscending(filter: String): Observable<List<MemorablePlace>> {
        return localDataSource.getByFilterAscending(filter)
            .map {
                it.map {
                    MemorablePlace(
                        it.id,
                        it.title,
                        it.content,
                        it.location,
                        it.dateAdded
                    )
                }
            }
    }
    override fun getAllByFilterDescending(filter: String): Observable<List<MemorablePlace>> {
        return localDataSource.getByFilterDescending(filter)
                .map {
                    it.map {
                        MemorablePlace(
                            it.id,
                            it.title,
                            it.content,
                            it.location,
                            it.dateAdded
                        )
                    }
                }
        }

    override fun delete(memorablePlace: MemorablePlace): Completable {
        return localDataSource.delete(memorablePlace.id)
    }

    override fun deleteAll(): Completable {
        return localDataSource.deleteAll()
    }
}