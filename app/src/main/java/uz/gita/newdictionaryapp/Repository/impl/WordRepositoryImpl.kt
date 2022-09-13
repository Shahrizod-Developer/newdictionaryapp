package uz.gita.newdictionaryapp.Repository.impl

import android.database.Cursor
import uz.gita.newdictionaryapp.Repository.WordRepository
import uz.gita.newdictionaryapp.data.room.AppDatabase
import uz.gita.newdictionaryapp.data.room.WordData

class WordRepositoryImpl: WordRepository {
    private val wordDao = AppDatabase.instance.getWordDao()

    override fun getWordCursor(): Cursor = wordDao.getEngUzCursor()
    override fun getFilteredWordsCursor(query: String): Cursor = wordDao.getFilteredCursor(query)

    override fun update(wordData: WordData){
        if (wordData.isFavorite == 0){
            wordData.isFavorite = 1
        } else {
            wordData.isFavorite = 0
        }
        wordDao.update(wordData)
    }

    override fun getFavourites(): Cursor {
        return wordDao.getFavorite()
    }


}