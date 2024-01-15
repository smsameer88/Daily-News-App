package com.sameer.dailypulse.articles.data

import com.sameer.dailypulse.articles.data.ArticleRaw
import com.sameer.dailypulse.articles.data.ArticlesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticlesService(private val httpClient: HttpClient) {

    private val country = "us"
    private val category = "business"
    private val apiKey = "1271dae3c97a4375883aba79a2bf8bee"

    suspend fun fetchArticles(): List<ArticleRaw> {

        val response: ArticlesResponse = httpClient.get(
            urlString = "https://newsapi.org/v2/top-headlines?" +
                    "country=${country}" +
                    "&category=${category}" +
                    "&apiKey=${apiKey}"
        ).body()

        return response.articles
    }
}