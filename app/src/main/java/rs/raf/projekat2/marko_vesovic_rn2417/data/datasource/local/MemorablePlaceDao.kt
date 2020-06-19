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

    @Query("SELECT * FROM memorablePlace")
    abstract fun getAll(): Observable<List<MemorablePlaceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(memorablePlaceEntity: MemorablePlaceEntity): Completable

}