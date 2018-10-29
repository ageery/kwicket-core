package org.kwicket.component.tag

import kotlinx.html.br
import kotlinx.html.div
import kotlinx.html.i
import kotlinx.html.span
import org.junit.jupiter.api.Test
import org.kwicket.component.AbstractWicketTest
import org.kwicket.component.render
import org.kwicket.model.model

class AbstractTagTest : AbstractWicketTest() {

    @Test
    fun f() {
        val panel = wicket().panel {
            span {
                +"hi"
            }
            br {
                span { +"help!" }
            }
            label(model = "test".model())
            webMarkupContainer(tagName = "div") {
                label(id = "testing", model = "hello".model()) {
                    label(model = "bye".model())
                }
                span {
                    +"other"
                }
                label(tagName = "label", model = "andrew".model())
            }
        }.build("panel")
        tester.render(panel) {
            assertContains("test")
            println(lastResponseAsString)
        }
    }

}