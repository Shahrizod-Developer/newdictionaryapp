package uz.gita.newdictionaryapp.Repository

import android.database.Cursor
import uz.gita.newdictionaryapp.data.room.WordData

interface WordRepository {

    fun getWordCursor(): Cursor
    fun getFilteredWordsCursor(query: String): Cursor
    fun update(wordData: WordData)
    fun getFavourites(): Cursor
}