package com.example.dummybase.receiver

import android.content.Context
import androidx.annotation.NonNull
import androidx.work.Worker
import androidx.work.WorkerParameters

class SeancesReminderWorker(@NonNull context: Context, @NonNull params: WorkerParameters) :
    Worker(context, params) {

    override
    fun doWork(): Result {
        println("lala")
        return Result.success()
    }
}