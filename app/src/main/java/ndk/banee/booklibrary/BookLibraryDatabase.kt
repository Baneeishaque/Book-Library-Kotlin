package ndk.banee.booklibrary

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AuthorModel::class], version = 1)
abstract class BookLibraryDatabase : RoomDatabase() {

    abstract fun authorDao(): AuthorDao
}
