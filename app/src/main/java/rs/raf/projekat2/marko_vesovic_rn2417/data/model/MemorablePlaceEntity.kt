package rs.raf.projekat2.marko_vesovic_rn2417.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memorablePlace")
data class MemorablePlaceEntity (
    @PrimaryKey(autoGenerate = true) val id: Long
)