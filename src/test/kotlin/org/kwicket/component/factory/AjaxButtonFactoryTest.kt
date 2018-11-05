package org.kwicket.component.factory

import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.markup.html.form.AjaxButton
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.model.IModel
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.kwicket.component.Person
import org.kwicket.component.TestPanel
import org.kwicket.component.q
import org.kwicket.component.render
import org.kwicket.model.model
import org.kwicket.model.plus

class AjaxButtonFactoryTest : AbstractFactoryTest<AjaxButton, String>() {

    override val tagName: String = "button"
    private val formModel = Person().model()
    override val model: IModel<String> = "Click Me".model()
    override val pathToComponent: String = "form:$compWicketId"
    override val baseBehaviorCount: Int = 1
    override val isNoMarkupIdValid: Boolean = false

    override fun componentsTestMarkup(id: String) = """
        <form wicket:id="form">
            <button wicket:id="$id">[COMPONENT]</button>
        </form>
        """.trimIndent()

    override fun Panel.queueAdditional() {
        q(formFactory(id = "form", model = formModel))
    }

    companion object {
        val formMarkup = """
            <form wicket:id="form">
                <input type="text" wicket:id="field"/>
                <button wicket:id="button"></button>
            </form>
        """.trimIndent()

        fun makePanel(
            id: String,
            model: IModel<Person>,
            onSubmit: (AjaxButton.(AjaxRequestTarget) -> Unit)? = null,
            onError: (AjaxButton.(AjaxRequestTarget) -> Unit)? = null
        ) =
            TestPanel(id = id, markup = formMarkup) {
                q(formFactory(id = "form", model = model))
                q(textFieldFactory(id = "field", model = model + Person::name, isRequired = true))
                q(ajaxButtonFactory(id = "button", onSubmit = onSubmit, onError = onError))
            }
    }

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
        onConfig: (AjaxButton.() -> Unit)?,
        postInit: (AjaxButton.() -> Unit)?
    ) = ajaxButtonFactory(
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

    @Test
    fun `test onSubmit`() {
        var onSubmitCalled = false
        var onErrorCalled = false
        val panel = makePanel(
            id = panelWicketId,
            model = Person().model(),
            onSubmit = { onSubmitCalled = true },
            onError = { onErrorCalled = true })
        tester.render(panel) {
            val formTester = newFormTester("panel:form")
            formTester.setValue("field", "Name")
            formTester.submit("button")
            assertTrue(onSubmitCalled)
            assertFalse(onErrorCalled)
        }
    }

    @Test
    fun `test onError`() {
        var onSubmitCalled = false
        var onErrorCalled = false
        val panel = makePanel(
            id = panelWicketId,
            model = Person().model(),
            onSubmit = { onSubmitCalled = true },
            onError = { onErrorCalled = true })
        tester.render(panel) {
            val formTester = newFormTester("panel:form")
            formTester.setValue("field", null)
            formTester.submit("button")
            assertFalse(onSubmitCalled)
            assertTrue(onErrorCalled)
        }
    }

}
