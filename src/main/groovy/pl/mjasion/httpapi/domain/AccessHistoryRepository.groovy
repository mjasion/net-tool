package pl.mjasion.httpapi.domain

import org.springframework.data.repository.PagingAndSortingRepository

interface AccessHistoryRepository extends PagingAndSortingRepository<AccessHistory, String> {

}
