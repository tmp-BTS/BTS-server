package com.tmp.BTS.store.dto

import com.tmp.BTS.store.model.Store
import java.time.LocalTime

data class StoreDto(val uuid:String) {

    fun toEntity():Store = Store(uuid)
}