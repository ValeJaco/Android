package com.example.dummybase.ui.seances

import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.View
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.*
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.example.dummybase.R
import com.example.dummybase.api.ApiResult
import com.example.dummybase.data.model.Seance
import com.example.dummybase.data.repository.SeancesRepository
import com.example.dummybase.utils.Event
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject
import android.app.PendingIntent

import android.content.Intent
import androidx.work.Data
import com.example.dummybase.receiver.SeancesReminderReceiver
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.dummybase.receiver.SeancesReminderWorker
import java.util.concurrent.TimeUnit


@HiltViewModel
class SeancesListViewModel  @Inject constructor(private val seancesRepository: SeancesRepository) : ViewModel() {


    lateinit var notificationManager : NotificationManager;
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "crossfit.seances.notifications"
    private val description = "Séances à venir"

    val isLoading: ObservableBoolean = ObservableBoolean(true)
    lateinit var baseView: View;

    private var _seancesListLiveData: MutableLiveData<Event<Unit>> = MutableLiveData()
    val seancesListLiveData: LiveData<List<Seance>?> = _seancesListLiveData.switchMap {
        seancesRepository.observeSeances().map { result ->
            when (result.status) {
                ApiResult.Status.SUCCESS -> {
                    isLoading.set(false)
                }
                ApiResult.Status.ERROR -> {
                    isLoading.set(false)
                    println("Error fetching seances : ${result.message}")
                }
                ApiResult.Status.LOADING -> {
                    isLoading.set(true)
                }
            }
            result.data
        }
    }

    fun loadSeances() {
        _seancesListLiveData.postValue(Event(Unit))
    }

    fun showSnackBar( seance: Seance ) {
        Snackbar.make(baseView, "Inscription à la séance de " + seance.dateSeanceToStringForSeanceCard() + " OK", 2500).show()
    }

    fun sendNotification( seance: Seance ) {

        notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_DEFAULT)
        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.GREEN
        notificationChannel.enableVibration(false)
        notificationManager.createNotificationChannel(notificationChannel)

        builder = Notification.Builder(baseView.context, channelId)
            .setSmallIcon(R.drawable.haltero_icone_active)
            .setLargeIcon(BitmapFactory.decodeResource( Resources.getSystem() , R.drawable.ic_subscribe))
            .setContentText( seance.dateSeanceToStringForSeanceCard() )
            .setContentTitle( seance.name )
            .setWhen( seance.dateSeance.epochSecond )

        notificationManager.notify( seance.id , builder.build() )
    }

    fun planNotification( seance: Seance ) {

        val intent = Intent(baseView.context, SeancesReminderReceiver::class.java)
        intent.putExtra( "SEANCE_NAME" , seance.name )
        intent.putExtra( "SEANCE_DATE" , seance.dateSeanceToStringForSeanceCard() )
        intent.putExtra( "SEANCE_ID" , seance.id )
        val pendingIntent = PendingIntent.getBroadcast(baseView.context, seance.id , intent, 0)
        println( seance.dateSeance.epochSecond )
        var alarmManager = baseView.context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)
        alarmManager.set(AlarmManager.RTC_WAKEUP, seance.dateSeance.toEpochMilli() , pendingIntent)
        showSnackBar( seance )
    }

}