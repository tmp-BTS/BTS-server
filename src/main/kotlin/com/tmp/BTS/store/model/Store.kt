package com.tmp.BTS.store.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "store")
data class Store (
        @Column
        val uuid : String,

        @Column
        val title : String,

        @Column
        val location : String
) : BaseEntity() {
    fun newStore(uuid : String, title : String, location: String): Store {
        val store = Store(uuid, title, location)
        return store
    }

    fun addHistory(temperature:String, time:LocalDateTime) : History {
        val history = History(this, temperature, time)

        return history
    }
}


