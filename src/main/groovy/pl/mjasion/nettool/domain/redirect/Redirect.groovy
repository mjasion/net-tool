package pl.mjasion.nettool.domain.redirect

import org.springframework.data.mongodb.core.mapping.Document

@Document
class Redirect {
    String redirectUrl
    String target
    Date created
}
