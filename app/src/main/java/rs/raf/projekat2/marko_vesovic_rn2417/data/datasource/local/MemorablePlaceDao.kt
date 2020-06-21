package rs.raf.projekat2.marko_vesovic_rn2417.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.marko_vesovic_rn2417.data.model.MemorablePlaceEntity
import java.util.*

@Dao
abstract class MemorablePlaceDao {

    @Query("SELECT * FROM memorablePlace ORDER BY dateAdded ASC")
    abstract fun getAllAscending(): Observable<List<MemorablePlaceEntity>>

    @Query("SELECT * FROM memorablePlace ORDER BY dateAdded DESC")
    abstract fun getAllDescending(): Observable<List<MemorablePlaceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(memorablePlaceEntity: MemorablePlaceEntity): Completable

    @Query("SELECT * FROM memorablePlace WHERE title LIKE '%' || :filter || '%' OR content LIKE '%' || :filter || '%' ORDER BY dateAdded ASC")
    abstract fun getByFilterAscending(filter: String): Observable<List<MemorablePlaceEntity>>

    @Query("SELECT * FROM memorablePlace WHERE title LIKE '%' || :filter || '%' OR content LIKE '%' || :filter || '%' ORDER BY dateAdded DESC")
    abstract fun getByFilterDescending(filter: String): Observable<List<MemorablePlaceEntity>>
}