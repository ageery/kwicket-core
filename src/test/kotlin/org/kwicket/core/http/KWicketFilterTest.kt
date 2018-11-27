package org.kwicket.core.http

import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.protocol.http.WebApplication
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS

@TestInstance(PER_CLASS)
class KWicketFilterTest {

    private class HomePage : WebPage()
    private class TestApp : KWebApplication(homePage = HomePage::class)
    private class TestFilter(webApp: WebApplication, filterPath: String = "/") :
        KWicketFilter(webApp = webApp, filterPath = filterPath) {
        val webApp: WebApplication
            get() = application
    }

    @Test
    fun `test filter connected to web app`() {
        val filter = TestFilter(webApp = TestApp())
        assertEquals(TestApp::class, filter.webApp::class)
    }

    @Test
    fun `test default filter path is blank`() {
        val filter = KWicketFilter(webApp = TestApp())
        assertEquals("", filter.filterPath)
    }

    @Test
    fun `test slash filter path maps to blank`() {
        val filter = KWicketFilter(webApp = TestApp(), filterPath = "/")
        assertEquals("", filter.filterPath)
    }

    @Test
    fun `test non-slash filter path maps correctly`() {
        val filter = KWicketFilter(webApp = TestApp(), filterPath = "/test")
        assertEquals("test/", filter.filterPath)
    }

}