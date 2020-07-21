package ndk.banee.booklibrary

import androidx.room.*

@Dao
interface BaseDao<in T> {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(t: T): Long

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun update(type: T): Int

    @Delete
    fun delete(type: T): Int
}
