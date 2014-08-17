package pl.mjasion.nettool.domain.redirect

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id

class RedirectHistory {
    @Id
    @JsonIgnore
    String id

    String accessUrl

    String redirectUrl

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "CET")
    Date accessDate
}
