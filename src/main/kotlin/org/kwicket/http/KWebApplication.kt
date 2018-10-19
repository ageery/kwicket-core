package org.kwicket.http

import org.apache.wicket.Page
import org.apache.wicket.RuntimeConfigurationType
import org.apache.wicket.protocol.http.WebApplication
import kotlin.reflect.KClass

/**
 * [WebApplication] with named parameters.
 *
 * @param homePage class of the home page
 * @param configType optional optional Wicket configuration type (defaults to development)
 */
abstract class KWebApplication(private val homePage: KClass<out Page>, configType: RuntimeConfigurationType? = null) :
    WebApplication() {

    init {
        configType?.let { this.configurationType = it }
    }

    /**
     * Returns the class of the home page for the application.
     *
     * @return the class of the home for the application
     */
    override fun getHomePage(): Class<out Page> = homePage.java

}
