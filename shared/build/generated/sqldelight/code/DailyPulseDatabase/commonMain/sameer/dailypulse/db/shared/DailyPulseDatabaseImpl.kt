package sameer.dailypulse.db.shared

import app.cash.sqldelight.TransacterImpl
import app.cash.sqldelight.db.AfterVersion
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import kotlin.Long
import kotlin.Unit
import kotlin.reflect.KClass
import sameer.dailypulse.db.DailyPulseDatabase
import sameer.dailypulse.db.DailyPulseDatabaseQueries

internal val KClass<DailyPulseDatabase>.schema: SqlSchema<QueryResult.Value<Unit>>
  get() = DailyPulseDatabaseImpl.Schema

internal fun KClass<DailyPulseDatabase>.newInstance(driver: SqlDriver): DailyPulseDatabase =
    DailyPulseDatabaseImpl(driver)

private class DailyPulseDatabaseImpl(
  driver: SqlDriver,
) : TransacterImpl(driver), DailyPulseDatabase {
  override val dailyPulseDatabaseQueries: DailyPulseDatabaseQueries =
      DailyPulseDatabaseQueries(driver)

  public object Schema : SqlSchema<QueryResult.Value<Unit>> {
    override val version: Long
      get() = 1

    override fun create(driver: SqlDriver): QueryResult.Value<Unit> {
      driver.execute(null, """
          |CREATE TABLE Article (
          |   title TEXT NOT NULL,
          |   desc TEXT,
          |   date TEXT NOT NULL,
          |   imageUrl TEXT
          |)
          """.trimMargin(), 0)
      return QueryResult.Unit
    }

    override fun migrate(
      driver: SqlDriver,
      oldVersion: Long,
      newVersion: Long,
      vararg callbacks: AfterVersion,
    ): QueryResult.Value<Unit> = QueryResult.Unit
  }
}
