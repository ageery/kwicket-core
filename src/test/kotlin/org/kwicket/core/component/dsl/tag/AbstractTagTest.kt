//package org.kwicket.core.component.dsl.tag
//
//import kotlinx.html.br
//import kotlinx.html.span
//import org.junit.jupiter.api.Test
//import org.kwicket.core.component.AbstractWicketTest
//import org.kwicket.core.component.KChoiceRenderer
//import org.kwicket.core.component.factory.labelFactory
//import org.kwicket.core.component.render
//import org.kwicket.core.component.wrapper.KLabel
//import org.kwicket.core.model.listModel
//import org.kwicket.core.model.model
//import org.kwicket.core.model.obj
//import org.kwicket.core.model.plus
//import org.kwicket.core.model.res
//import java.io.Serializable
//
//class AbstractTagTest : AbstractWicketTest() {
//
//    @Test
//    fun f() {
//        class Person(var name: String? = null, var id: String = "0", var parent: Person? = null) : Serializable
//        val panel = /*wicket().*/ panel {
//            val formModel = Person().model()
//            val parents = listOf(Person(id = "1", name = "Andrew"), Person(id = "2", name = "Bill"))
//            val childModel = Person("test").model()
//            form(model = formModel) {
//                textField(model = formModel + Person::name, isRequired = true) {
//                    isVisible = true
//                }
//                label(model = formModel + Person::name)
//                label(model = "hi".res())
//                checkBox(model = true.model(), label = "Check Me!".model())
//                textField(model = formModel + Person::id)
//                dropDownChoice(model = formModel + Person::parent, choices = parents.listModel(),
//                    choiceRenderer = KChoiceRenderer(
//                        toDisplayObj = { it.name ?: "No name" },
//                        toId = { person, _ -> person.id },
//                        toObj = { id, choices -> choices.obj.first { parent -> parent.id == id } }),
//                    label = "andrew".model())
//                dropDownChoice(model = childModel, choices = parents.listModel(),
//                    choiceRenderer = KChoiceRenderer(
//                        toDisplayObj = { it.name ?: "No name" },
//                        toId = { person, _ -> person.id },
//                        toObj = { id, choices -> choices.obj.first { parent -> parent.id == id } }),
//                    label = "andrew".model())
//
//            }
//            label(1.model(), visible = false) {
//                isVisible = true
//                //isEnabled = true
//            }
//            span {
//                +"hi"
//            }
//            br {
//                span { +"help!" }
//            }
//            form(model = "andrew".model()) {
//                onConfig = { println("here!") }
//                label(model = "blah".model())
//                button(model = "Click me!".model()) {
//                    onSubmit = {
//
//                    }
//                }
//                ajaxButton(model = "Ajax submit".model()) {
//                    onSubmit = { target ->
//                        println(target)
//                    }
//                }
//            }
//            label(model = "test".model())
//            val x = labelFactory(id = "xyz", model = "static".model(), outputMarkupPlaceholderTag = true) {
//                isVisible = false
//            }
//            span(comp = x)
//            val y = KLabel(id = "dummy", model = "Geery".model())
//            comp(tagName = "label", comp = y) {
//                span { +"hi" }
//            }
//            //label(compareBy() = x)
//            webMarkupContainer(tagName = "div") {
//                onConfig = {
//                    println("here!!!")
//                }
//                label(id = "testing", model = "hello".model()) {
//                    label(model = "bye".model())
//                }
//                span {
//                    +"other"
//                }
//                label(tagName = "label", model = "andrew".model())
//            }
//        }.panel("panel")
//        tester.render(panel) {
//            assertContains("test")
//            println(lastResponseAsString)
//        }
//    }
//
//}