package pl.mjasion.httputils.domain.accesshistory

import org.springframework.data.repository.PagingAndSortingRepository

interface AccessHistoryRepository extends PagingAndSortingRepository<AccessHistory, String> {
}
