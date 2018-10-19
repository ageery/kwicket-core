package org.kwicket.component

import org.apache.wicket.MarkupContainer
import org.apache.wicket.Page
import org.apache.wicket.markup.IMarkupResourceStreamProvider
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.model.IModel
import org.apache.wicket.protocol.http.WebApplication
import org.apache.wicket.util.resource.IResourceStream
import org.apache.wicket.util.resource.StringResourceStream
import org.apache.wicket.util.tester.TagTester
import org.apache.wicket.util.tester.WicketTester
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.kwicket.http.KWebApplication

@TestInstance(PER_CLASS)
abstract class AbstractWicketTest {

    protected fun WicketTester.findTag(attribute: String, value: String): TagTester? =
        TagTester.createTagByAttribute(output, attribute, value)

    protected val tester = WicketTester(TestApp())

}

internal abstract class TestPanel(id: String, model: IModel<*>? = null, val markup: String) : Panel(id, model),
    IMarkupResourceStreamProvider {

    override fun getMarkupResourceStream(container: MarkupContainer, containerClass: Class<*>): IResourceStream =
        StringResourceStream("<wicket:panel>$markup</wicket:panel>")
}

internal class HomePage : WebPage()

internal class TestApp : KWebApplication(homePage = HomePage::class)