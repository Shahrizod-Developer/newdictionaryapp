package uz.gita.newdictionaryapp

import android.app.Application
import uz.gita.newdictionaryapp.data.room.AppDatabase


class DictionaryApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        AppDatabase.init(this)


    }
}