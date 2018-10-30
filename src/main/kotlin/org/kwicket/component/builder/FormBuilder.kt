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
    model: IModel<T>? = null,
    markupId: String? = null,
    outputMarkupId: Boolean? = null,
    outputMarkupPlaceholderTag: Boolean? = null,
    isVisible: Boolean? = null,
    isVisibilityAllowed: Boolean? = null,
    isEnabled: Boolean? = null,
    escapeModelStrings: Boolean? = null,
    renderBodyOnly: Boolean? = null,
    behavior: Behavior? = null,
    behaviors: List<Behavior>? = null,
    onConfig: (Form<T>.() -> Unit)? = null,
    override var onSubmit: (Form<T>.() -> Unit)? = null,
    override var onError: (Form<T>.() -> Unit)? = null,
    postInit: (Form<T>.() -> Unit)? = null
) : IFormBuilder<T>,
    ComponentBuilder<Form<T>, T>(
        model = model,
        markupId = markupId,
        outputMarkupId = outputMarkupId,
        outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
        isVisible = isVisible,
        isVisibilityAllowed = isVisibilityAllowed,
        isEnabled = isEnabled,
        escapeModelStrings = escapeModelStrings,
        renderBodyOnly = renderBodyOnly,
        behavior = behavior,
        behaviors = behaviors,
        onConfig = onConfig,
        postInit = postInit
    ) {

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
            escapeModelStrings = escapeModelStrings,
            renderBodyOnly = renderBodyOnly,
            behavior = behavior,
            behaviors = behaviors,
            onConfig = onConfig,
            onSubmit = onSubmit,
            onError = onError,
            postInit = postInit
        )

}
