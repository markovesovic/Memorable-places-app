package rs.raf.projekat2.marko_vesovic_rn2417.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Database(
    entities = [],
    version = 1,
    exportSchema = false
)
@TypeConverters()
abstract class Database: RoomDatabase() {

}