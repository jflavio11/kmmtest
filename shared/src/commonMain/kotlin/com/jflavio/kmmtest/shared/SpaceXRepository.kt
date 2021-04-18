package com.jflavio.kmmtest.shared

import com.jflavio.kmmtest.shared.cache.Database
import com.jflavio.kmmtest.shared.cache.DatabaseDriverFactory
import com.jflavio.kmmtest.shared.network.SpaceXApi
import com.jflavio.kmmtest.RocketLaunch

/**
 * SpaceXSDK
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since  15/04/2021
 */
class SpaceXRepository(private val databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databaseDriverFactory)
    private val api = SpaceXApi()

    /**
     * Get all the rocket launches from cache or from the SpaceX API.
     *
     * To handle exceptions produced by the Ktor client, in Swift, we
     * need to mark our function with @Throws annotation. All Kotlin
     * exceptions are unchecked, while Swift has only checked errors.
     * Thus, to make Swift code aware of expected exceptions, Kotlin
     * functions should be marked with an @Throws annotation specifying
     * a list of potential exception classes.
     */
    @Throws(Exception::class)
    suspend fun getLaunches(forceReload: Boolean): List<RocketLaunch> {
        val cachedLaunches = database.getAllLaunches()
        return if (cachedLaunches.isNotEmpty() && !forceReload) {
            cachedLaunches
        } else {
            api.getAllLaunches().also {
                database.clearDatabase()
                database.createLaunches(it)
            }
        }
    }

}