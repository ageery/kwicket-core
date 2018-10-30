package org.kwicket.component.dsl.tag

import kotlinx.html.br
import kotlinx.html.span
import org.junit.jupiter.api.Test
import org.kwicket.component.AbstractWicketTest
import org.kwicket.component.factory.labelFactory
import org.kwicket.component.render
import org.kwicket.component.wrapper.KLabel
import org.kwicket.model.model

class AbstractTagTest : AbstractWicketTest() {

    @Test
    fun f() {
        val panel = /*wicket().*/ panel {
            label(1.model(), visible = false) {
                isVisible = true
                //isEnabled = true
            }
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
            val x = labelFactory(id = "xyz", model = "static".model(), outputMarkupPlaceholderTag = true) {
                isVisible = false
            }
            span(comp = x)
            val y = KLabel(id = "dummy", model = "Geery".model())
            comp(tagName = "label", comp = y) {
                span { +"hi" }
            }
            //label(compareBy() = x)
            webMarkupContainer(tagName = "div") {
                onConfig = {
                    println("here!!!")
                }
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