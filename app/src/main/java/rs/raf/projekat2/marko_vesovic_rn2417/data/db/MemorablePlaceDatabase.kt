package rs.raf.projekat2.marko_vesovic_rn2417.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.projekat2.marko_vesovic_rn2417.data.datasource.local.MemorablePlaceDao
import rs.raf.projekat2.marko_vesovic_rn2417.data.db.converters.DateConverter
import rs.raf.projekat2.marko_vesovic_rn2417.data.db.converters.LatLngConverter
import rs.raf.projekat2.marko_vesovic_rn2417.data.model.MemorablePlaceEntity

@Database(
    entities = [MemorablePlaceEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(LatLngConverter::class, DateConverter::class)
abstract class MemorablePlaceDatabase: RoomDatabase() {
    abstract fun getMemorablePlaceDao(): MemorablePlaceDao
}