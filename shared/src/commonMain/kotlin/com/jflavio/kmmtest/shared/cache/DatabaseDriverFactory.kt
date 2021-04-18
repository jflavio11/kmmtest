package com.jflavio.kmmtest.shared.cache

import com.squareup.sqldelight.db.SqlDriver

/**
 * DatabaseDriverFactory
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since  14/04/2021
 */
expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}