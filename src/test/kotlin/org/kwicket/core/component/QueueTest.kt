package org.kwicket.core.component

import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.markup.html.basic.Label
import org.junit.jupiter.api.Test
import org.kwicket.core.model.model


class QueueTest : AbstractWicketTest() {

    companion object {
        private const val panelId = "panel"
        private const val labelId = "label"
        private const val containerId = "container2"
        private const val labelPath = "panel:$labelId"
        private const val labelText = "test"

        private val componentsTestMarkup = """<span wicket:id="$labelId">[LABEL]</span>""".trimMargin()
        private val nestedTestMarkup = """
            |<div wicket:id="$containerId">
            |   <span wicket:id="$labelId">[LABEL]</span>
            |</div>
            """.trimMargin()
    }

    @Test
    fun `q queues direct component`() {
        val panel = TestPanel(id = panelId, markup = componentsTestMarkup) {
            q(Label(labelId, labelText.model()))
        }
        tester.render(panel) {
            assertLabel(labelPath, labelText)
        }
    }

    @Test
    fun `q queues nested component`() {
        val panel = TestPanel(id = panelId, markup = nestedTestMarkup) {
            q(WebMarkupContainer(containerId))
            q(Label(labelId, labelText.model()))
        }
        tester.render(panel) {
            assertContains(labelId)
        }
    }
}