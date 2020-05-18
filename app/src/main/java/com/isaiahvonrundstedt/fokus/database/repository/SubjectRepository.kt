package com.isaiahvonrundstedt.fokus.database.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.isaiahvonrundstedt.fokus.database.AppDatabase
import com.isaiahvonrundstedt.fokus.features.subject.Subject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SubjectRepository(app: Application) {

    private var job = Job()
    private var database = AppDatabase.getInstance(app)
    private var subjects = database?.subject()

    fun fetch(): LiveData<List<Subject>>? = subjects?.fetch()

    fun search(query: String): LiveData<List<Subject>>? = subjects?.search(query)

    suspend fun insert(subject: Subject) {
        subjects?.insert(subject)
    }

    suspend fun remove(subject: Subject) {
        subjects?.remove(subject)
    }

    suspend fun update(subject: Subject) {
        subjects?.update(subject)
    }



}