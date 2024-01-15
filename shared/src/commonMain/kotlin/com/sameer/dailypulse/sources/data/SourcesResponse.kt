package com.sameer.dailypulse.sources.data

import com.sameer.dailypulse.sources.data.SourcesRaw
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourcesResponse(
    @SerialName("status")
    val status:String,

    @SerialName("sources")
    val sources:List<SourcesRaw>
)
