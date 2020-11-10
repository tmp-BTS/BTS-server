package com.tmp.BTS.store.repository

import com.tmp.BTS.store.model.Store
import org.springframework.data.repository.CrudRepository
import org.springframework.transaction.annotation.Transactional

interface StoreRepository : CrudRepository<Store, Long> {

    fun getById(id : Long) : Store

    @Transactional
    fun deleteStoreById(id : Long)
}