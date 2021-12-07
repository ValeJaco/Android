package com.example.dummybase.data.repository

import com.example.dummybase.api.ApiClient
import com.example.dummybase.data.model.Seance
import com.example.dummybase.data.persistence.SeancesDao
import com.example.dummybase.utils.resultLiveData
import javax.inject.Inject

class SeancesRepository @Inject constructor(
    private val apiClient: ApiClient,
    private val seancesDao: SeancesDao
) : Repository {

    fun observeSeances() = resultLiveData(
        databaseQuery = { seancesDao.getSeances() },
        networkCall = { apiClient.fetchSeances() },
        saveCallResult = { seancesDao.insertSeances( it ) }
    )

    fun getSeanceById( seanceId: Int) = resultLiveData <Seance, Seance>(
        databaseQuery = { seancesDao.getSeanceById(seanceId) }
    )
}
