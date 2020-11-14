package com.tmp.BTS.store.dto

import com.tmp.BTS.store.model.History
import java.time.LocalDateTime


data class HistoryDto(val uuid : String, val temperature:String) {

    //fun toEntity():History = History(userId, storeId, temperature, LocalDateTime.now())

}