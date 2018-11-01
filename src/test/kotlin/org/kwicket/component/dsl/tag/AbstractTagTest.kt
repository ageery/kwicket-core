package org.kwicket.component.dsl.tag

import kotlinx.html.br
import kotlinx.html.span
import org.junit.jupiter.api.Test
import org.kwicket.component.AbstractWicketTest
import org.kwicket.component.factory.labelFactory
import org.kwicket.component.render
import org.kwicket.component.wrapper.KLabel
import org.kwicket.model.model
import org.kwicket.model.plus
import org.kwicket.model.res
import java.io.Serializable

class AbstractTagTest : AbstractWicketTest() {

    @Test
    fun f() {
        class Person(var name: String? = null) : Serializable
        val panel = /*wicket().*/ panel {
            val formModel = Person().model()
            form(model = formModel) {
                textField(model = formModel + Person::name, isRequired = true) {
                    isVisible = true
                }
                label(model = formModel + Person::name)
                label(model = "hi".res())
                checkBox(model = true.model(), label = "Check Me!".model())
            }
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
                button(model = "Click me!".model()) {
                    onSubmit = {

                    }
                }
                ajaxButton(model = "Ajax submit".model()) {
                    onSubmit = { target ->
                        println(target)
                    }
                }
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