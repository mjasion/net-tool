package pl.mjasion.nettool.domain.redirect

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Redirect {
    @Id
    String accessUrl

    String redirectUrl

    Date created
}
