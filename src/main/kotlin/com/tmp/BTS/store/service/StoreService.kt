package com.tmp.BTS.store.service

import com.tmp.BTS.store.dto.HistoryDto
import com.tmp.BTS.store.dto.HistoryListDto
import com.tmp.BTS.store.model.Store
import com.tmp.BTS.store.repository.HistoryRepository
import com.tmp.BTS.store.repository.HistoryRepositorySupport
import com.tmp.BTS.store.repository.StoreRepository
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

    @Transactional
    fun addStoreByBeacon(historyDto: HistoryDto):Boolean{
        try{
            //val user:User = userRepository.getById(historyDto.userId)
            val store:Store = storeRepository.getById(id = historyDto.uuid)

            val history = store.addHistory(historyDto.temperature, LocalDateTime.now())
            historyRepository.save(history)

            return true

        } catch (ex:Exception) {
            logger.warn(ex.message, kv("storeId", historyDto.uuid), kv("eventCode", LogEvent.StoreServiceProcess.code))
            return false
        }
    }


    fun fetchHistoryList():List<HistoryListDto> {
        val histories = historyRepositorySupport.fetchHistoryList()

        return histories
    }
}
