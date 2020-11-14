package com.tmp.BTS.store.dto

import com.tmp.BTS.store.model.Store

data class StoreDto(val uuid:String, val title:String, val location:String) {

    fun toEntity():Store = Store(uuid, title, location)
}