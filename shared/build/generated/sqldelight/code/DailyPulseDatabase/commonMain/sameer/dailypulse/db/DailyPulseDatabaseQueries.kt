package sameer.dailypulse.db

import app.cash.sqldelight.Query
import app.cash.sqldelight.TransacterImpl
import app.cash.sqldelight.db.SqlDriver
import kotlin.Any
import kotlin.String

public class DailyPulseDatabaseQueries(
  driver: SqlDriver,
) : TransacterImpl(driver) {
  public fun <T : Any> selectAllArticles(mapper: (
    title: String,
    desc: String?,
    date: String,
    imageUrl: String?,
  ) -> T): Query<T> = Query(2_132_412_944, arrayOf("Article"), driver, "DailyPulseDatabase.sq",
      "selectAllArticles", """
  |SELECT Article.*
  |FROM Article
  """.trimMargin()) { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1),
      cursor.getString(2)!!,
      cursor.getString(3)
    )
  }

  public fun selectAllArticles(): Query<Article> = selectAllArticles { title, desc, date,
      imageUrl ->
    Article(
      title,
      desc,
      date,
      imageUrl
    )
  }

  public fun insertArticle(
    title: String,
    desc: String?,
    date: String,
    imageUrl: String?,
  ) {
    driver.execute(1_120_773_291, """
        |INSERT INTO Article(title, desc, date, imageUrl)
        |VALUES(?,?,?,?)
        """.trimMargin(), 4) {
          bindString(0, title)
          bindString(1, desc)
          bindString(2, date)
          bindString(3, imageUrl)
        }
    notifyQueries(1_120_773_291) { emit ->
      emit("Article")
    }
  }

  public fun removeAllArticles() {
    driver.execute(-1_689_894_520, """DELETE FROM Article""", 0)
    notifyQueries(-1_689_894_520) { emit ->
      emit("Article")
    }
  }
}
