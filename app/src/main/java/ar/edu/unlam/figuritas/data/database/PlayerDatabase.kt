package ar.edu.unlam.figuritas.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ar.edu.unlam.figuritas.data.database.dao.PlayerDao
import ar.edu.unlam.figuritas.model.entities.PlayerAlbumEntity
import ar.edu.unlam.figuritas.model.entities.PlayerEntity

@Database(
    version = 6,
    entities = [PlayerEntity::class,
                PlayerAlbumEntity::class]
)
abstract class PlayerDatabase : RoomDatabase() {

    abstract fun playerDao(): PlayerDao

}