package com.sameer.dailypulse.articles.data

import sameer.dailypulse.db.DailyPulseDatabase


class ArticlesDataSource(
    private val database: DailyPulseDatabase
) {

    fun getAllArticles(): List<ArticleRaw> =
        database.dailyPulseDatabaseQueries.selectAllArticles(::mapToArticleRaw).executeAsList()

    fun insertArticles(articles: List<ArticleRaw>) {
        database.dailyPulseDatabaseQueries.transaction {
            articles.forEach { articleRaw ->
                insertArticles(articleRaw)
            }
        }
    }

    fun clearArticles()= database.dailyPulseDatabaseQueries.removeAllArticles()

    private fun insertArticles(articleRaw: ArticleRaw) {
        database.dailyPulseDatabaseQueries.insertArticle(
            articleRaw.title,
            articleRaw.desc,
            articleRaw.date,
            articleRaw.imageUrl
        )
    }

    private fun mapToArticleRaw(
        title: String,
        desc: String?,
        date: String,
        imageUrl: String?
    ): ArticleRaw =
        ArticleRaw(
            title, desc, date, imageUrl
        )
}