package uz.gita.newdictionaryapp.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import uz.gita.newdictionaryapp.R

class AboutDialog(context: Context) : AlertDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_about)
        findViewById<AppCompatButton>(R.id.btn_ok).setOnClickListener { dismiss() }
        setCancelable(false)
    }

}