package ndk.banee.booklibrary

import androidx.room.Dao
import androidx.room.Query

@Dao
interface AuthorDao : BaseDao<AuthorModel> {
    @Query(value = "SELECT * FROM authors")
    fun getAllAuthors(): List<AuthorModel>

    @Query(value = "SELECT * FROM authors WHERE author_id = :authorId")
    fun getAuthorWithId(authorId: Long): AuthorModel?

    @Query(value = "SELECT author_id FROM authors")
    fun getAllIds(): List<Long>
}
