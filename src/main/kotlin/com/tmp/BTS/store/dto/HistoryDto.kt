package com.tmp.BTS.store.dto

import com.tmp.BTS.store.model.History
import com.tmp.BTS.store.model.Store
import com.tmp.BTS.user.model.User
import java.time.LocalDateTime


data class HistoryDto(val uuid : String, val user : User, val store: Store, val temperature:String) {

    fun toEntity():History = History(user, store, temperature, LocalDateTime.now())

}