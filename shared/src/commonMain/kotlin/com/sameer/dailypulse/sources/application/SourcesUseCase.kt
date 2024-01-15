package com.sameer.dailypulse.sources.application

import com.sameer.dailypulse.sources.data.SourcesRaw
import com.sameer.dailypulse.sources.data.SourcesRepository

class SourcesUseCase(
    private val repository: SourcesRepository
) {

    suspend fun getSources(): List<Source> {
        val fetchedSources = repository.getSources()
        return mapToSources(fetchedSources)
    }

    private fun mapToSources(fetchedSources: List<SourcesRaw>): List<Source> {
        return fetchedSources.map { sourceRaw ->
            Source(
                id = sourceRaw.id,
                title = sourceRaw.title,
                desc = sourceRaw.desc,
                imageUrl = sourceRaw.imageUrl,
                origin = combineLanguageCountry(
                    sourceRaw.language, sourceRaw.country
                )
            )
        }
    }

    private fun combineLanguageCountry(
        language: String,
        country: String
    ): String {
        return "$country - $language"
    }
}