package rs.raf.projekat2.marko_vesovic_rn2417.data.datasource.local

import androidx.room.*
import com.google.android.gms.maps.model.LatLng
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



    @Query("DELETE FROM memorablePlace WHERE id = :id")
    abstract fun delete(id: Long): Completable

    @Query("DELETE FROM memorablePlace")
    abstract fun deleteAll(): Completable



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(memorablePlaceEntity: MemorablePlaceEntity): Completable

    @Query("UPDATE memorablePlace SET title = :title, content = :content, location = :latLng WHERE id = :id")
    abstract fun update(id: Long, title: String, content: String, latLng: LatLng): Completable

//    @Query("SELECT * FROM memorablePlace WHERE (title LIKE '%' || :filter || '%' OR content LIKE '%' || :filter || '%') ORDER BY dateAdded ASC")
    @Query("SELECT * FROM memorablePlace WHERE title LIKE :filter || '%' ORDER BY dateAdded ASC")
    abstract fun getByFilterAscending(filter: String): Observable<List<MemorablePlaceEntity>>

//    @Query("SELECT * FROM memorablePlace WHERE (title LIKE '%' || :filter || '%' OR content LIKE '%' || :filter || '%') ORDER BY dateAdded DESC")
    @Query("SELECT * FROM memorablePlace WHERE title LIKE :filter || '%' ORDER BY dateAdded DESC")
    abstract fun getByFilterDescending(filter: String): Observable<List<MemorablePlaceEntity>>
}