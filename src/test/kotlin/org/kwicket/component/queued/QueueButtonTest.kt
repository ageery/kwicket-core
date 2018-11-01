package org.kwicket.component.queued

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.model.IModel
import org.junit.jupiter.api.Test
import org.kwicket.component.TestPanel
import org.kwicket.component.q
import org.kwicket.component.render
import org.kwicket.model.model

class QueueButtonTest : AbstractQueuedTest<Label, String?>() {

    override val model: IModel<String?> = "test label".model()

    override fun Panel.queueComponent(
        id: String,
        model: IModel<String?>?,
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
        onConfig: (Label.() -> Unit)?,
        postInit: (Label.() -> Unit)?
    ): Label = label(
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
    fun f() {
        val x = object : TestPanel(id = panelWicketId, markup = """<form wicket:id="form"><button wicket:id="button"></button></form>""") {
            init {
                q(Form("form", "andrew".model()))
                button("button", model = "andrew".model()) {
                    onConfig = {
                        println("here")
                        isVisible = false
                    }
                }
//                ajaxButton(id = "ajaxButton", model = "here".model()) {
//                    onSubmit = { target ->
//                        println(target)
//                    }
//                    onError = {
//
//                    }
//                }
            }
        }
        tester.render(x) {
            println(lastResponseAsString)
        }
    }

}