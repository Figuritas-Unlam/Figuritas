package ar.edu.unlam.figuritas.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ar.edu.unlam.figuritas.data.database.dao.PlayerDao
import ar.edu.unlam.figuritas.data.database.dao.RepeatedDao
import ar.edu.unlam.figuritas.model.entities.PlayerEntity
import ar.edu.unlam.figuritas.model.entities.RepeatedEntity

/*@Database(
    version = 1,
    entities = [PlayerEntity::class, RepeatedEntity::class]
)
abstract class PlayerDatabase : RoomDatabase() {

    abstract fun playerDao(): PlayerDao

    abstract fun repeatedDao(): RepeatedDao
}*/