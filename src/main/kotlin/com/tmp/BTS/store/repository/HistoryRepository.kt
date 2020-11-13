package com.tmp.BTS.store.repository

import com.tmp.BTS.store.model.History
import com.tmp.BTS.store.model.Store
import org.springframework.data.repository.CrudRepository
import org.springframework.transaction.annotation.Transactional

interface HistoryRepository : CrudRepository<History, Long> {
    @Transactional
    fun deleteByTime(time:String)

    fun findByStore(store: Store) : History?
}