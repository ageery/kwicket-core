package org.kwicket.component.builder

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.model.IModel
import org.kwicket.component.factory.formFactory

interface IFormBuilder<T> : IComponentBuilder<Form<T>, T> {
    var onSubmit: (Form<T>.() -> Unit)?
    var onError: (Form<T>.() -> Unit)?
}

class FormBuilder<T>(
    override val model: IModel<T>? = null,
    override var markupId: String? = null,
    override var outputMarkupId: Boolean? = null,
    override var outputMarkupPlaceholderTag: Boolean? = null,
    override var isVisible: Boolean? = null,
    override var isVisibilityAllowed: Boolean? = null,
    override var isEnabled: Boolean? = null,
    override var isEscapeModelStrings: Boolean? = null,
    override var isRenderBodyOnly: Boolean? = null,
    override var behavior: Behavior? = null,
    override var behaviors: List<Behavior>? = null,
    override var onConfig: (Form<T>.() -> Unit)? = null,
    override var onSubmit: (Form<T>.() -> Unit)? = null,
    override var onError: (Form<T>.() -> Unit)? = null,
    override var postInit: (Form<T>.() -> Unit)? = null
) : IFormBuilder<T> {

    override fun build(id: String): Form<T> =
        formFactory(
            id = id,
            model = model,
            markupId = markupId,
            outputMarkupId = outputMarkupId,
            outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
            visible = isVisible,
            visibilityAllowed = isVisibilityAllowed,
            enabled = isEnabled,
            escapeModelStrings = isEscapeModelStrings,
            renderBodyOnly = isRenderBodyOnly,
            behavior = behavior,
            behaviors = behaviors,
            onConfig = onConfig,
            onSubmit = onSubmit,
            onError = onError,
            postInit = postInit
        )

}
