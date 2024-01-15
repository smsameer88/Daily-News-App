package com.sameer.dailypulse.di

import app.cash.sqldelight.db.SqlDriver
import com.sameer.dailypulse.db.DatabaseDriverFactory
import org.koin.dsl.module
import sameer.dailypulse.db.DailyPulseDatabase

val databaseModule = module {

    single<SqlDriver> { DatabaseDriverFactory().createDriver() }

    single<DailyPulseDatabase> { DailyPulseDatabase(get()) }
}