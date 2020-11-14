package com.tmp.BTS.store.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import com.tmp.BTS.store.model.History
import com.tmp.BTS.store.model.Store
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import javax.annotation.Resource

interface HistoryRepository : CrudRepository<History, Long> {
    @Transactional
    fun deleteByTime(time:String)

    fun findByStore(store: Store) : History?
}

@Repository
class HistoryRepositorySupport(
        @Resource(name = "jpaQueryFactory")
        val query : JPAQueryFactory
) : QuerydslRepositorySupport(History::class.java) {
   /* fun fetchHistoryList() : List<HistoryListDto>{
        val qHistory = QHistory.history
        val qStore = QStore.store

        val historyList : JPQLQuery<HistoryListDto> = query.from(qHistory)
                //.innerJoin(qHistory.store, qStore)
                .select(Projections.constructor(HistoryListDto::class.java,
                qStore.title, qStore.location, qHistory.temperature, qHistory.time))
                .groupBy(qStore.title, qStore.location, qHistory.temperature, qHistory.time).orderBy(qHistory.time)

        return historyList.fetch()
    }*/

}