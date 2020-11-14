package com.tmp.BTS.store.repository

import com.tmp.BTS.store.model.Place
import org.springframework.data.repository.CrudRepository

interface PlaceRepository: CrudRepository<Place, Long> {
}