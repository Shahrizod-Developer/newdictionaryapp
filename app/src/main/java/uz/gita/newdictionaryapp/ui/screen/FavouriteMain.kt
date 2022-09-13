package uz.gita.newdictionaryapp.ui.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.gita.newdictionaryapp.R
import uz.gita.newdictionaryapp.presenter.WordViewModel
import uz.gita.newdictionaryapp.presenter.impl.WordViewModelImpl
import uz.gita.newdictionaryapp.ui.adapter.WordAdapter
import uz.gita.newdictionaryapp.ui.dialog.WordInfoDialog
import java.util.*

class FavouriteMain : AppCompatActivity() {
    private lateinit var listWords: RecyclerView

    private lateinit var backButton: ImageView

    private val adapter: WordAdapter by lazy { WordAdapter() }

    private val viewModel: WordViewModel by viewModels<WordViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.favorites)
        listWords = findViewById(R.id.list_favorite)

        backButton = findViewById(R.id.btnBack)
        backButton.setOnClickListener {
            onBackPressed()
        }

        listWords.layoutManager = LinearLayoutManager(this)
        adapter.setItemClickListener { viewModel.showInfo(it) }
        adapter.setFavouriteClickListener { data ->
            viewModel.changeFavorite(data)
            viewModel.showFavourites()
        }
        listWords.adapter = adapter
        viewModel.showFavourites()



        viewModel.showWordInfoLiveData.observe(this) {
            val dialog = WordInfoDialog()
            dialog.setFavouriteClickListener {
                viewModel.changeFavorite(it)
                viewModel.showFavourites()

            }
            val bundle = Bundle()
            bundle.putSerializable("data", it)
            dialog.arguments = bundle
            dialog.show(supportFragmentManager, "Info")
        }

        viewModel.favouritesLiveData.observe(this) {
            adapter.submitCursor(it)
        }
        viewModel.updateItemLiveData.observe(this) {
            adapter.notifyItemChanged(it)
        }

        viewModel.favouritesLiveData.observe(this) {
            adapter.submitCursor(it)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}