package pl.mjasion.nettool.domain.watchers

import org.springframework.data.mongodb.repository.MongoRepository

interface WatchedPageRepository extends MongoRepository<WatchedPage, String> {

}
