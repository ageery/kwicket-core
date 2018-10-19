package org.kwicket.component

import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.util.tester.WicketTester
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.kwicket.model.model

private const val panelId = "panel"
private const val labelId = "label"
private const val labelPath = "panel:$labelId"
private const val labelText = "test"

private val componentsTestMarkup = """<span wicket:id="$labelId">[LABEL]</span>""".trimMargin()

private val nestedTestMarkup = """
            |<div wicket:id="container">
            |   <span wicket:id="$labelId">[LABEL]</span>
            |</div>
            """.trimMargin()

private abstract class QueueTestPanel(markup: String) : TestPanel(id = panelId, markup = markup)

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class QueueTest {

    private val tester = WicketTester(TestApp())

    @Test
    fun `q queues direct component`() {
        val panel = object : QueueTestPanel(markup = componentsTestMarkup) {
            init {
                q(Label(labelId, labelText.model()))
            }
        }
        tester.render(panel) {
            assertLabel(labelPath, labelText)
        }
    }

    @Test
    fun `q queues nested component`() {
        val panel = object : QueueTestPanel(markup = nestedTestMarkup) {
            init {
                q(WebMarkupContainer("container"))
                q(Label(labelId, labelText.model()))
            }
        }
        tester.render(panel) {
            assertContains(labelId)
        }
    }
}