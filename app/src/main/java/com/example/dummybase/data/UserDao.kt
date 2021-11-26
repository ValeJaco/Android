package com.example.dummybase.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dummybase.data.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserList(userList: List<User>)

    @Query("SELECT * FROM User")
    fun getUserList(): LiveData<List<User>>

}