//package org.kwicket.component.factory
//
//import org.apache.wicket.behavior.Behavior
//import org.apache.wicket.markup.html.form.Button
//import org.apache.wicket.model.IModel
//import org.junit.jupiter.api.Assertions
//import org.junit.jupiter.api.Assertions.assertFalse
//import org.junit.jupiter.api.Assertions.assertTrue
//import org.junit.jupiter.api.Test
//import org.kwicket.component.Person
//import org.kwicket.component.TestPanel
//import org.kwicket.component.q
//import org.kwicket.component.render
//import org.kwicket.model.model
//import org.kwicket.model.obj
//import org.kwicket.model.plus
//
//class ButtonFactoryTest : AbstractFactoryTest<Button, String>() {
//
//    override val tagName: String = "button"
//    override val model: IModel<String> = "test label".model()
//
//    companion object {
//        val formMarkup = """
//            <form wicket:id="form">
//                <input type="text" wicket:id="field"/>
//                <button wicket:id="button"></button>
//            </form>
//        """.trimIndent()
//
//        fun makePanel(
//            id: String,
//            model: IModel<Person>,
//            defaultFormProcessing: Boolean? = null,
//            onSubmit: (Button.() -> Unit)? = null,
//            onError: (Button.() -> Unit)? = null
//        ) =
//            TestPanel(id = id, markup = formMarkup) {
//                q(formFactory(id = "form", model = model))
//                q(textFieldFactory(id = "field", model = model + Person::name, isRequired = true))
//                q(buttonFactory(id = "button", onSubmit = onSubmit, onError = onError, defaultFormProcessing = defaultFormProcessing))
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
//        onConfig: (Button.() -> Unit)?,
//        postInit: (Button.() -> Unit)?
//    ) = buttonFactory(
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
//            formTester.submit("button")
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
//            formTester.submit("button")
//            assertFalse(onSubmitCalled)
//            assertTrue(onErrorCalled)
//        }
//    }
//
//    @Test
//    fun `test valid input defaultFormProcessing=false`() {
//        var onSubmitCalled = false
//        var onErrorCalled = false
//        val model = Person().model()
//        val panel = AjaxButtonFactoryTest.makePanel(
//            id = panelWicketId,
//            model = model,
//            defaultFormProcessing = false,
//            onSubmit = { onSubmitCalled = true },
//            onError = { onErrorCalled = true })
//        tester.render(panel) {
//            val formTester = newFormTester("panel:form")
//            formTester.setValue("field", "test")
//            formTester.submit("button")
//            assertTrue(onSubmitCalled)
//            assertFalse(onErrorCalled)
//            Assertions.assertNull(model.obj.name)
//        }
//    }
//
//    @Test
//    fun `test invalid input defaultFormProcessing=false`() {
//        var onSubmitCalled = false
//        var onErrorCalled = false
//        val model = Person().model()
//        val panel = makePanel(
//            id = panelWicketId,
//            model = model,
//            defaultFormProcessing = false,
//            onSubmit = { onSubmitCalled = true },
//            onError = { onErrorCalled = true })
//        tester.render(panel) {
//            val formTester = newFormTester("panel:form")
//            formTester.setValue("field", null)
//            formTester.submit("button")
//            assertTrue(onSubmitCalled)
//            assertFalse(onErrorCalled)
//            Assertions.assertNull(model.obj.name)
//        }
//    }
//
//    @Test
//    fun `test valid input defaultFormProcessing=true`() {
//        var onSubmitCalled = false
//        var onErrorCalled = false
//        val model = Person().model()
//        val panel = makePanel(
//            id = panelWicketId,
//            model = model,
//            defaultFormProcessing = true,
//            onSubmit = { onSubmitCalled = true },
//            onError = { onErrorCalled = true })
//        val name = "test"
//        tester.render(panel) {
//            val formTester = newFormTester("panel:form")
//            formTester.setValue("field", name)
//            formTester.submit("button")
//            assertTrue(onSubmitCalled)
//            assertFalse(onErrorCalled)
//            Assertions.assertEquals(name, model.obj.name)
//        }
//    }
//
//    @Test
//    fun `test invalid input defaultFormProcessing=true`() {
//        var onSubmitCalled = false
//        var onErrorCalled = false
//        val model = Person().model()
//        val panel = makePanel(
//            id = panelWicketId,
//            model = model,
//            defaultFormProcessing = true,
//            onSubmit = { onSubmitCalled = true },
//            onError = { onErrorCalled = true })
//        tester.render(panel) {
//            val formTester = newFormTester("panel:form")
//            formTester.setValue("field", null)
//            formTester.submit("button")
//            assertFalse(onSubmitCalled)
//            assertTrue(onErrorCalled)
//            Assertions.assertNull(model.obj.name)
//        }
//    }
//
//}
