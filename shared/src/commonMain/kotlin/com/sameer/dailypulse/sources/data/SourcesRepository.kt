package com.sameer.dailypulse.sources.data

class SourcesRepository(
    private val service: SourcesService
) {

    suspend fun getSources(): List<SourcesRaw> {
        return service.fetchSources()
    }

}