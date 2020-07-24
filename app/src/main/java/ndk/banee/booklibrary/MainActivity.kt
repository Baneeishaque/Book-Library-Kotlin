package ndk.banee.booklibrary

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database: BookLibraryDatabase =
            Room.databaseBuilder(this, BookLibraryDatabase::class.java, applicationName).build()

        val authorId: Long
        val insertAuthorSingle = Single.just("Author Name")
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({ authorName ->

                authorId = database.authorDao().insert(AuthorModel(authorName = authorName))
                Log.d(applicationName, "Author Inserted, Author ID : $authorId")

            }, { throwable ->
                Log.d(applicationName, "Error : ${throwable.localizedMessage}")
            })

        // Author with Id
        val getAuthorSingle= Single.just(authorId)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({ localAuthorId ->
                    Log.d(
                        applicationName,
                        "Author With id $localAuthorId : ${database.authorDao()
                            .getAuthorWithId(localAuthorId)
                            .toString()}"
                    )

                    // Get all authors
                    Completable.complete()
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .subscribe({

                            val authorList: List<AuthorModel> =
                                database.authorDao().getAllAuthors()
                            val authorsIterator = authorList.listIterator()
                            Log.d(applicationName, "Current Authors...")
                            while (authorsIterator.hasNext()) {

                                Log.d(applicationName, authorsIterator.next().toString())
                            }

                        }, { throwable ->
                            Log.d(applicationName, "Error : ${throwable.localizedMessage}")
                        })

                }, { throwable ->
                    Log.d(applicationName, "Error : ${throwable.localizedMessage}")
                })
    }
}
