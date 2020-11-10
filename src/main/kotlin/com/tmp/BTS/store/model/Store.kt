package com.tmp.BTS.store.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import java.time.LocalTime
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
    fun newStore(uuid : String, title : String, location: String) {
        val store = store(uuid, )
        return store
    }
}


