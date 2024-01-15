package com.sameer.dailypulse.sources.data

import com.sameer.dailypulse.sources.data.SourcesRaw
import com.sameer.dailypulse.sources.data.SourcesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class SourcesService(
    private val httpClient: HttpClient
) {
    private val apiKey = "1271dae3c97a4375883aba79a2bf8bee"

    suspend fun fetchSources(): List<SourcesRaw> {

        val response: SourcesResponse = httpClient.get(
            urlString = "https://newsapi.org/v2/top-headlines/sources?" +
                    "apiKey=${apiKey}"
        ).body()

        return response.sources
    }

}