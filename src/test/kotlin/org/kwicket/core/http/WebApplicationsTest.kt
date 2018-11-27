package org.kwicket.core.http

import org.apache.wicket.core.request.handler.RenderPageRequestHandler
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.mock.MockWebRequest
import org.apache.wicket.request.Url
import org.apache.wicket.util.tester.WicketTester
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WebApplicationsTest {

    private class HomePage : WebPage()

    @Test
    fun `test mounted page with KClass`() {
        val tester = WicketTester(object : KWebApplication(homePage = HomePage::class) {
            override fun init() {
                super.init()
                mountPage("home", HomePage::class)
            }
        })
        val handler = tester.application.rootRequestMapper.mapRequest(MockWebRequest(Url.parse("home")))
        Assertions.assertNotNull(handler)
        val requestHandler = handler as RenderPageRequestHandler
        Assertions.assertEquals(HomePage::class.java, requestHandler.pageProvider.pageClass)
    }

}