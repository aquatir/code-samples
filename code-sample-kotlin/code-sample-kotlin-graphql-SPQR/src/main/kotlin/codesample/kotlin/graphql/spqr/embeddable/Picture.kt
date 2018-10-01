package codesample.kotlin.graphql.spqr.embeddable

import javax.persistence.Embeddable

@Embeddable
class Picture (
        val pictureDescription: String = "",
        val pictureUrl: String = ""
)