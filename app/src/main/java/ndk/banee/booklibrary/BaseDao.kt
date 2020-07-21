package ndk.banee.booklibrary

import androidx.room.*

@Dao
interface BaseDao<in T> {

    @Insert(onConflict = OnConflictStrategy.ROLLBACK)
    fun insert(t: T): Long

    @Delete
    fun delete(type: T)

    @Update(onConflict = OnConflictStrategy.ROLLBACK)
    fun update(type: T)
}
