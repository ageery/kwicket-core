package org.kwicket.component.tag

import kotlinx.html.br
import kotlinx.html.onSubmit
import kotlinx.html.span
import org.junit.jupiter.api.Test
import org.kwicket.component.AbstractWicketTest
import org.kwicket.component.render
import org.kwicket.component.wrapper.KLabel
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
            form(model = "andrew".model()) {
                onConfig = { println("here!") }
                label(model = "blah".model())
            }
            label(model = "test".model())
            val x = KLabel(id = "xyz", model = "static".model())
            span(comp = x)
            //label(compareBy() = x)
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