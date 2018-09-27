package codesample.kotlin.graphql.embeddable

import javax.persistence.Embeddable

@Embeddable
class Picture (
        val pictureDescription: String = "",
        val pictureUrl: String = ""
)