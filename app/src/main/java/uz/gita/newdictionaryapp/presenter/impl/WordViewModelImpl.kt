package uz.gita.newdictionaryapp.presenter.impl

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.newdictionaryapp.Repository.WordRepository
import uz.gita.newdictionaryapp.Repository.impl.WordRepositoryImpl
import uz.gita.newdictionaryapp.data.room.WordData
import uz.gita.newdictionaryapp.presenter.WordViewModel

class WordViewModelImpl : WordViewModel, ViewModel() {

    private val wordRepository: WordRepository = WordRepositoryImpl()
    override val cursorLiveData = MutableLiveData<Cursor>()
    override val showWordInfoLiveData = MutableLiveData<WordData>()
    override val favouritesLiveData = MutableLiveData<Cursor>()
    override val updateItemLiveData = MutableLiveData<Int>()


    private var query: String = ""

    override fun showInfo(wordData: WordData){
        showWordInfoLiveData.value = wordData
    }

    override fun changeFavorite(wordData: WordData) {
        wordRepository.update(wordData)
        if (query.isEmpty()){
            cursorLiveData.value = wordRepository.getWordCursor()
        } else {
            cursorLiveData.value = wordRepository.getFilteredWordsCursor(query)
        }
    }

    override fun filter(query: String){
        this.query = query
        cursorLiveData.value = wordRepository.getFilteredWordsCursor(query)
    }

    override fun showFavourites() {
        favouritesLiveData.value = wordRepository.getFavourites()
    }

    init{
        cursorLiveData.value = wordRepository.getWordCursor()
    }
}