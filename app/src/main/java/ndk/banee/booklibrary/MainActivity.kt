package ndk.banee.booklibrary

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database: BookLibraryDatabase =
            Room.databaseBuilder(this, BookLibraryDatabase::class.java, applicationName).build()

        var id: Long
        // For inserting the author
        Observable.just("Author Name")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ authorName ->
                id = database.authorDao().insert(AuthorModel(authorName = authorName))
                Log.d(applicationName, "Author Inserted, Author ID : $id")
            }, { throwable ->
                Log.d(applicationName, "Error : ${throwable.printStackTrace()}")
            }, {})

//        // Author with Id
//        Observable.just(database.authorDao().getAuthorWithId(id))
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                Log.d(applicationName, "Author With id $id : ${it.toString()}")
//            }, {
//                Log.d(applicationName, "Error : ${it.printStackTrace()}")
//            })
//
//        // Get all authors
//        Observable.just(database.authorDao().getAllAuthors())
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                val authorsIterator = it.listIterator()
//                Log.d(applicationName, "Current Authors...")
//                while (authorsIterator.hasNext()) {
//
//                    Log.d(applicationName, authorsIterator.next().toString())
//                }
//            }, {
//                Log.d(applicationName, "Error : ${it.printStackTrace()}")
//            })
    }
}
