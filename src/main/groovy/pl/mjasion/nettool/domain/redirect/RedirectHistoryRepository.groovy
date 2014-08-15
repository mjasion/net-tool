package pl.mjasion.nettool.domain.redirect

import org.springframework.data.repository.PagingAndSortingRepository

interface RedirectHistoryRepository extends PagingAndSortingRepository<RedirectHistory, String>{
}
