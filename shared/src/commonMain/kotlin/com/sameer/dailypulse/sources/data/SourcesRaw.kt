package com.sameer.dailypulse.sources.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourcesRaw   (
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val title: String,
    @SerialName("description")
    val desc: String,
    @SerialName("url")
    val imageUrl: String,
    @SerialName("category")
    val category: String,
    @SerialName("language")
    val language: String,
    @SerialName("country")
    val country: String
)
