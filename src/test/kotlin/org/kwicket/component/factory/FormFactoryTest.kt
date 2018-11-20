//package org.kwicket.component.factory
//
//import org.apache.wicket.behavior.Behavior
//import org.apache.wicket.markup.html.form.Form
//import org.apache.wicket.model.IModel
//import org.junit.jupiter.api.Assertions.assertFalse
//import org.junit.jupiter.api.Assertions.assertTrue
//import org.junit.jupiter.api.Test
//import org.kwicket.component.Person
//import org.kwicket.component.TestPanel
//import org.kwicket.component.q
//import org.kwicket.component.render
//import org.kwicket.model.model
//import org.kwicket.model.plus
//
//class FormFactoryTest : AbstractFactoryTest<Form<String>, String>() {
//
//    override val tagName: String = "form"
//    override val model: IModel<String> = "test label".model()
//
//    companion object {
//        val formMarkup = """
//            <form wicket:id="form">
//                <input type="text" wicket:id="field"/>
//            </form>
//        """.trimIndent()
//
//        fun makePanel(
//            id: String,
//            model: IModel<Person>,
//            onSubmit: (Form<Person>.() -> Unit)? = null,
//            onError: (Form<Person>.() -> Unit)? = null
//        ) =
//            TestPanel(id = id, markup = formMarkup) {
//                q(formFactory(id = "form", model = model, onSubmit = onSubmit, onError = onError))
//                q(textFieldFactory(id = "field", model = model + Person::name, isRequired = true))
//            }
//    }
//
//    override fun factoryCreate(
//        id: String,
//        model: IModel<String>?,
//        markupId: String?,
//        outputMarkupId: Boolean?,
//        outputMarkupPlaceholderTag: Boolean?,
//        visible: Boolean?,
//        visibilityAllowed: Boolean?,
//        enabled: Boolean?,
//        escapeModelStrings: Boolean?,
//        renderBodyOnly: Boolean?,
//        behavior: Behavior?,
//        behaviors: List<Behavior>?,
//        onConfig: (Form<String>.() -> Unit)?,
//        postInit: (Form<String>.() -> Unit)?
//    ) = formFactory(
//        id = id,
//        model = model,
//        markupId = markupId,
//        outputMarkupId = outputMarkupId,
//        outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
//        visible = visible,
//        enabled = enabled,
//        visibilityAllowed = visibilityAllowed,
//        escapeModelStrings = escapeModelStrings,
//        renderBodyOnly = renderBodyOnly,
//        behavior = behavior,
//        behaviors = behaviors,
//        onConfig = onConfig,
//        postInit = postInit
//    )
//
//    @Test
//    fun `test onSubmit`() {
//        var onSubmitCalled = false
//        var onErrorCalled = false
//        val panel = makePanel(
//            id = panelWicketId,
//            model = Person().model(),
//            onSubmit = { onSubmitCalled = true },
//            onError = { onErrorCalled = true })
//        tester.render(panel) {
//            val formTester = newFormTester("panel:form")
//            formTester.setValue("field", "Name")
//            formTester.submit()
//            assertTrue(onSubmitCalled)
//            assertFalse(onErrorCalled)
//        }
//    }
//
//    @Test
//    fun `test onError`() {
//        var onSubmitCalled = false
//        var onErrorCalled = false
//        val panel = makePanel(
//            id = panelWicketId,
//            model = Person().model(),
//            onSubmit = { onSubmitCalled = true },
//            onError = { onErrorCalled = true })
//        tester.render(panel) {
//            val formTester = newFormTester("panel:form")
//            formTester.setValue("field", null)
//            formTester.submit()
//            assertFalse(onSubmitCalled)
//            assertTrue(onErrorCalled)
//        }
//    }
//
//}
