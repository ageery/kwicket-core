package org.kwicket.http

import org.apache.wicket.RuntimeConfigurationType
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.util.tester.WicketTester
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class KApplicationTest {

    private class HomePage : WebPage()

    @Test
    fun `test default runtime config type`() {
        val tester = WicketTester(object : KWebApplication(homePage = HomePage::class) { })
        assertEquals(RuntimeConfigurationType.DEVELOPMENT, tester.application.configurationType)
    }

    @Test
    fun `test home page`() {
        val tester = WicketTester(object : KWebApplication(homePage = HomePage::class) { })
        assertEquals(HomePage::class.java, tester.application.homePage)
    }

    @Test
    fun `test development runtime config type`() {
        val tester = WicketTester(object : KWebApplication(homePage = HomePage::class, configType = RuntimeConfigurationType.DEVELOPMENT) { })
        assertEquals(RuntimeConfigurationType.DEVELOPMENT, tester.application.configurationType)
    }

    @Test
    fun `test deployment runtime config type`() {
        val tester = WicketTester(object : KWebApplication(homePage = HomePage::class, configType = RuntimeConfigurationType.DEPLOYMENT) { })
        assertEquals(RuntimeConfigurationType.DEPLOYMENT, tester.application.configurationType)
    }


}