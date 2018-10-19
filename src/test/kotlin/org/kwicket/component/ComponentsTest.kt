package org.kwicket.component

import org.apache.wicket.behavior.AttributeAppender
import org.apache.wicket.markup.html.basic.Label
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.kwicket.model.model

@TestInstance(PER_CLASS)
class ComponentsTest : AbstractWicketTest() {

    companion object {
        private const val panelId = "panel"
        private const val labelId = "label"
        private const val labelPath = "$panelId:$labelId"
        private const val labelText = "test"
        private const val componentsTestMarkup = """<span wicket:id="$labelId">[LABEL]</span>"""
    }

    private abstract class ComponentsTestPanel : TestPanel(id = panelId, markup = componentsTestMarkup)

    @Test
    fun `component config defaults`() {
        val panel = object : ComponentsTestPanel() {
            init {
                q(Label(labelId, labelText.model()).config())
            }
        }
        tester.render(panel) {
            assertContains(labelId)
            assertEnabled(labelPath)
            assertVisible(labelPath)
            assertLabel(labelPath, labelText)
            assertFalse(tester.getTagByWicketId(labelId).hasAttribute("id"))
        }
    }

    @Test
    fun `component config markupId`() {
        val myMarkupId = "testTag"
        val panel = object : ComponentsTestPanel() {
            init {
                q(
                    Label(labelId, labelText.model())
                        .config(markupId = myMarkupId)
                )
            }
        }
        tester.render(panel) {
            assertNotNull(findTag("id", myMarkupId))
        }
    }

    @Test
    fun `component config markupId and outputMarkupId=true`() {
        val myMarkupId = "testTag"
        val panel = object : ComponentsTestPanel() {
            init {
                q(
                    Label(labelId, labelText.model())
                        .config(markupId = myMarkupId, outputMarkupId = true)
                )
            }
        }
        tester.render(panel) {
            assertNotNull(findTag("id", myMarkupId))
        }
    }

    @Test
    fun `component config outputMarkupId=false`() {
        val panel = object : ComponentsTestPanel() {
            init {
                q(
                    Label(labelId, labelText.model())
                        .config(outputMarkupId = false)
                )
            }
        }
        tester.render(panel) {
            assertFalse(tester.getTagByWicketId(labelId).hasAttribute("id"))
        }
    }

    @Test
    fun `component config outputMarkupId`() {
        val panel = object : ComponentsTestPanel() {
            val label = q(
                Label(labelId, labelText.model())
                    .config(outputMarkupId = true)
            )
        }
        tester.render(panel) {
            assertNotNull(findTag("id", panel.label.markupId))
        }
    }

    @Test
    fun `component config outputMarkupPlaceholderTag=true`() {
        val panel = object : ComponentsTestPanel() {
            val label = q(
                Label(labelId, labelText.model())
                    .config(outputMarkupPlaceholderTag = true)
            )
        }
        tester.render(panel) {
            assertNotNull(findTag("id", panel.label.markupId))
            assertVisible(labelPath)
        }
    }

    @Test
    fun `component config outputMarkupPlaceholderTag=true visible=false`() {
        val panel = object : ComponentsTestPanel() {
            val label = q(
                Label(labelId, labelText.model())
                    .config(visible = false, outputMarkupPlaceholderTag = true)
            )
        }
        tester.render(panel) {
            assertNotNull(findTag("id", panel.label.markupId))
            assertInvisible(labelPath)
        }
    }

    @Test
    fun `component config visible=false`() {
        val c = Label(labelId).config(visible = false)
        assertFalse(c.isVisible)
        val panel = object : ComponentsTestPanel() {
            val label = q(
                Label(labelId, labelText.model())
                    .config(visible = false)
            )
        }
        tester.render(panel) {
            assertFalse(panel.label.isVisible)
            assertInvisible(labelPath)
        }
    }

    @Test
    fun `component config visible=true`() {
        val panel = object : ComponentsTestPanel() {
            val label = q(
                Label(labelId, labelText.model())
                    .config(visible = true)
            )
        }
        tester.render(panel) {
            assertLabel(labelPath, labelText)
            assertVisible(labelPath)
            assertTrue(panel.label.isVisible)
        }
    }

    @Test
    fun `component config renderBodyOnly=true`() {
        val panel = object : ComponentsTestPanel() {
            val label = q(
                Label(labelId, labelText.model())
                    .config(renderBodyOnly = true)
            )
        }
        tester.render(panel) {
            assertLabel(labelPath, labelText)
            assertTrue(panel.label.renderBodyOnly)
            assertNull(tester.getTagByWicketId(labelId))
        }
    }

    @Test
    fun `component config escapeModelStrings=true`() {
        val labelText = "<1"
        val panel = object : ComponentsTestPanel() {
            val label = q(
                Label(labelId, labelText.model())
                    .config(escapeModelStrings = true)
            )
        }
        tester.render(panel) {
            assertLabel(labelPath, "&lt;1")
            assertTrue(panel.label.escapeModelStrings)
        }
    }

    @Test
    fun `component config escapeModelStrings=false`() {
        val labelText = "<1"
        val panel = object : ComponentsTestPanel() {
            val label = q(
                Label(labelId, labelText.model())
                    .config(escapeModelStrings = false)
            )
        }
        tester.render(panel) {
            assertLabel(labelPath, labelText)
            assertFalse(panel.label.escapeModelStrings)
        }
    }

    @Test
    fun `component config enabled=true`() {
        val panel = object : ComponentsTestPanel() {
            val label = q(
                Label(labelId, labelText.model())
                    .config(enabled = true)
            )
        }
        tester.render(panel) {
            assertEnabled(labelPath)
            assertLabel(labelPath, labelText)
        }
    }

    @Test
    fun `component config enabled=false`() {
        val panel = object : ComponentsTestPanel() {
            val label = q(
                Label(labelId, labelText.model())
                    .config(enabled = false)
            )
        }
        tester.render(panel) {
            assertDisabled(labelPath)
            assertLabel(labelPath, labelText)
        }
    }

    @Test
    fun `component config behavior != null`() {
        val panel = object : ComponentsTestPanel() {
            val label = q(
                Label(labelId, labelText.model())
                    .config(behavior = AttributeAppender("data-kwicket-test", "testing"))
            )
        }
        tester.render(panel) {
            assertLabel(labelPath, labelText)
            assertEquals(1, panel.label.behaviors.size)
            assertNotNull(tester.findTag("data-kwicket-test", "testing"))
        }
    }

    @Test
    fun `component config 1 behaviors`() {
        val panel = object : ComponentsTestPanel() {
            val label = q(
                Label(labelId, labelText.model())
                    .config(behaviors = listOf(AttributeAppender("data-kwicket-test", "testing")))
            )
        }
        tester.render(panel) {
            assertLabel(labelPath, labelText)
            assertEquals(1, panel.label.behaviors.size)
            assertNotNull(tester.findTag("data-kwicket-test", "testing"))
        }
    }

    @Test
    fun `component config 2 behaviors`() {
        val panel = object : ComponentsTestPanel() {
            val label = q(
                Label(labelId, labelText.model())
                    .config(
                        behaviors = listOf(
                            AttributeAppender("data-kwicket-test", "testing"),
                            AttributeAppender("data-kwicket-test2", "testing2")
                        )
                    )
            )
        }
        tester.render(panel) {
            assertLabel(labelPath, labelText)
            assertEquals(2, panel.label.behaviors.size)
            assertNotNull(tester.findTag("data-kwicket-test", "testing"))
            assertNotNull(tester.findTag("data-kwicket-test2", "testing2"))
        }
    }

    @Test
    fun `component config behavior with behaviors`() {
        val panel = object : ComponentsTestPanel() {
            val label = q(
                Label(labelId, labelText.model())
                    .config(
                        behavior = AttributeAppender("data-kwicket-test", "behavior1", " "),
                        behaviors = listOf(AttributeAppender("data-kwicket-test", "behavior2", " "))
                    )
            )
        }
        tester.render(panel) {
            assertLabel(labelPath, labelText)
            assertEquals(2, panel.label.behaviors.size)
            assertNotNull(tester.findTag("data-kwicket-test", "behavior1 behavior2"))
        }
    }

}