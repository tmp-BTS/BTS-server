package com.tmp.BTS.store.service

import com.tmp.BTS.store.dto.HistoryDto
import com.tmp.BTS.store.dto.HistoryListDto
import com.tmp.BTS.store.model.History
import com.tmp.BTS.store.model.Place
import com.tmp.BTS.store.model.Store
import com.tmp.BTS.store.repository.HistoryRepository
import com.tmp.BTS.store.repository.HistoryRepositorySupport
import com.tmp.BTS.store.repository.PlaceRepository
import com.tmp.BTS.store.repository.StoreRepository
import com.tmp.BTS.user.UserRepository
import com.tmp.BTS.user.model.User
import com.tmp.BTS.util.LogEvent
import net.logstash.logback.argument.StructuredArguments.kv
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class StoreService {

    private val logger = LoggerFactory.getLogger(this.javaClass.name)

    @Autowired
    lateinit var historyRepository: HistoryRepository

    @Autowired
    lateinit var storeRepository: StoreRepository

    @Autowired
    lateinit var historyRepositorySupport: HistoryRepositorySupport

    @Autowired
    lateinit var placeRepository: PlaceRepository

    @Autowired
    lateinit var historyDto:HistoryDto

    @Autowired
    lateinit var userRepository : UserRepository

    @Transactional
    fun addStoreByBeacon(uuid:String, temperature:String):Boolean{
        try{
            val user: User = userRepository.getById(historyDto.user)

            val store:Store = storeRepository.getById(id = uuid)

            val history = store.addHistory(temperature, LocalDateTime.now())
            historyRepository.save(history)

            return true

        } catch (ex:Exception) {
            logger.warn(ex.message, kv("storeId", uuid), kv("eventCode", LogEvent.StoreServiceProcess.code))
            return false
        }
    }

    @Transactional
    fun addPlaceByUser(user : User, title: String, location: String): Boolean {
        try {
            val user:User = userRepository.getById(user)
            val place = Place(user, title, location, LocalDateTime.now())
            placeRepository.save(place)

            return true
        } catch(ex:Exception) {
            logger.warn(ex.message, kv("title", title), kv("eventCode", LogEvent.StoreServiceProcess.code))
            return false
        }
    }

    fun withdrawal(store:Store): Boolean {
        try{
            val history: History? = historyRepository.findByStore(store)

            if(history == null) throw Exception("history is null")

            if(history.time.plusWeeks(4) == LocalDateTime.now() ) {
                historyRepository.deleteHistoryById(store.id!!)
            }
            return true
        } catch (ex:Exception) {
            logger.warn(ex.message, kv("store", store.id), kv("eventCode", LogEvent.StoreServiceProcess.code))
            return false
        }

    }


    fun fetchHistoryList():List<HistoryListDto> {
        val histories = historyRepositorySupport.fetchHistoryList()

        return histories
    }
}
