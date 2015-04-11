package pl.mjasion.nettool.domain.watchers

import com.fasterxml.jackson.annotation.JsonFormat
import groovy.transform.CompileStatic
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@CompileStatic
@Document
class WatchedPage {

    @Id
    String url

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "CET")
    Date created
}
