package org.kwicket.component.factory

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.model.IModel
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.kwicket.component.TestPanel
import org.kwicket.component.q
import org.kwicket.component.render
import org.kwicket.model.model
import org.kwicket.model.plus
import java.io.Serializable

class FormFactoryTest : AbstractFactoryTest<Form<String>, String>() {

    override val tagName: String = "form"
    override val model: IModel<String> = "test label".model()

    override fun factoryCreate(
        id: String,
        model: IModel<String>?,
        markupId: String?,
        outputMarkupId: Boolean?,
        outputMarkupPlaceholderTag: Boolean?,
        visible: Boolean?,
        visibilityAllowed: Boolean?,
        enabled: Boolean?,
        escapeModelStrings: Boolean?,
        renderBodyOnly: Boolean?,
        behavior: Behavior?,
        behaviors: List<Behavior>?,
        onConfig: (Form<String>.() -> Unit)?,
        postInit: (Form<String>.() -> Unit)?
    ) = formFactory(
        id = id,
        model = model,
        markupId = markupId,
        outputMarkupId = outputMarkupId,
        outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
        visible = visible,
        enabled = enabled,
        visibilityAllowed = visibilityAllowed,
        escapeModelStrings = escapeModelStrings,
        renderBodyOnly = renderBodyOnly,
        behavior = behavior,
        behaviors = behaviors,
        onConfig = onConfig,
        postInit = postInit
    )

    val formMarkup = """
        <form wicket:id="form">
            <input type="text" wicket:id="field"/>
        </form>
    """.trimIndent()

    private class Person(var name: String? = null) : Serializable

    @Test
    fun `test onSubmit`() {
        val model = Person().model()
        var submitCalled = false
        val panel = TestPanel(id = panelWicketId, markup = formMarkup) {
            q(formFactory(id = "form", model = model, onSubmit = { submitCalled = true}))
            q(textFieldFactory(id = "field", model = model + Person::name))
        }
        tester.render(panel) {
            val formTester = newFormTester("panel:form")
            formTester.setValue("field", "Name")
            formTester.submit()
            assertTrue(submitCalled)
        }
    }

    @Test
    fun `test onError`() {
        val model = Person().model()
        var errorCalled = false
        val panel = TestPanel(id = panelWicketId, markup = formMarkup) {
            q(formFactory(id = "form", model = model, onError = { errorCalled = true }))
            q(textFieldFactory(id = "field", model = model + Person::name, isRequired = true))
        }
        tester.render(panel) {
            val formTester = newFormTester("panel:form")
            formTester.submit()
            assertTrue(errorCalled)
        }
    }

}
