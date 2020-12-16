package com.tmp.BTS.store.model

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable


@Embeddable
data class HistoryKey (
        @Column(name = "user_id")
        private val userId: Long? = 0,

        @Column(name = "store_id")
        private val storeId: Long? = 0
) : Serializable