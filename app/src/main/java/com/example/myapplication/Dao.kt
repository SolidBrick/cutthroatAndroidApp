package com.example.myapplication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Dao {
    @Query("SELECT * FROM user")
    fun getAll(): List<Player>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Player>

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): Player

    @Insert
    fun insertAll(vararg users: Player)

    @Delete
    fun delete(user: Player)
}