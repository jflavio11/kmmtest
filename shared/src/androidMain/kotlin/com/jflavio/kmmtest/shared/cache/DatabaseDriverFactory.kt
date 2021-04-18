package com.jflavio.kmmtest.shared.cache

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

/**
 * DatabaseDriverFactory
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since  14/04/2021
 */
actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(KmmDatabase.Schema, context, "app.db")
    }
}