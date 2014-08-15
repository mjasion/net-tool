package pl.mjasion.nettool.domain.accesshistory

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class AccessHistory {
    @Id
    @JsonIgnore
    String id;

    String ip

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "CET")
    Date accessDate

    Site site
}