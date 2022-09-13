package uz.gita.newdictionaryapp.presenter

import android.database.Cursor
import androidx.lifecycle.LiveData
import uz.gita.newdictionaryapp.data.room.WordData

interface WordViewModel {

    val cursorLiveData: LiveData<Cursor>
    val showWordInfoLiveData: LiveData<WordData>
    val favouritesLiveData: LiveData<Cursor>
    val updateItemLiveData: LiveData<Int>

    fun showInfo(wordData: WordData)
    fun changeFavorite(wordData: WordData)
    fun filter(query: String)
    fun showFavourites()
}