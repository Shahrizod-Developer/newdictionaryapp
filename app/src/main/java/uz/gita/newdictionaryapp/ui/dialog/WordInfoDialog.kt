package uz.gita.newdictionaryapp.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import uz.gita.newdictionaryapp.R

import uz.gita.newdictionaryapp.data.room.WordData

class WordInfoDialog: BottomSheetDialogFragment() {
    private lateinit var txtWord: TextView
    private lateinit var txtTranscription: TextView
    private lateinit var txtTranslation: TextView
    private lateinit var txtType: TextView
    private lateinit var txtCountable: TextView
    private lateinit var btnStart: ImageButton

    private var favouriteClickListener: ((WordData) -> Unit)? = null

    fun setFavouriteClickListener(block: (WordData) -> Unit) {
        favouriteClickListener = block
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_word_info, container, false)
        txtWord = view.findViewById(R.id.txtWord)
        txtTranscription = view.findViewById(R.id.txtTranscription)
        txtTranslation = view.findViewById(R.id.txtTranslation)
        txtType = view.findViewById(R.id.txtType)
        txtCountable = view.findViewById(R.id.txtCountable)
        btnStart = view.findViewById(R.id.btnStar)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val data = arguments?.getSerializable("data") as WordData
        txtWord.text = data.english
        txtTranslation.text = data.uzbek
        txtType.text = data.type
        txtTranscription.text = data.transcript
        txtCountable.text = data.countable
        if (data.isFavorite == 0) {
            btnStart.setImageResource(R.drawable.start_outlined)
        } else {
            btnStart.setImageResource(R.drawable.star_filled)
        }
        btnStart.setOnClickListener {
            if (data.isFavorite == 1) {
                btnStart.setImageResource(R.drawable.start_outlined)
            } else {
                btnStart.setImageResource(R.drawable.star_filled)
            }
            favouriteClickListener?.invoke(data)
        }
    }

}