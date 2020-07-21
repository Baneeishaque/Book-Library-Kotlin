package ndk.banee.booklibrary

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "authors")
data class AuthorModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "author_id")
    val authorId: Long = 0,
    @ColumnInfo(name = "author_name")
    var authorName: String
) {
    override fun toString(): String {

        return "AuthorModel(authorId=$authorId, authorName='$authorName')"
    }
}
