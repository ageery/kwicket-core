package org.kwicket.component.builder

import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.markup.html.form.AjaxButton
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.model.IModel
import org.kwicket.component.factory.ajaxButtonFactory

interface IAjaxButtonBuilder : IComponentBuilder<AjaxButton, String> {
    var onSubmit: (AjaxButton.(AjaxRequestTarget) -> Unit)?
    var onError: (AjaxButton.(AjaxRequestTarget) -> Unit)?
    val form: Form<*>?
}

class AjaxButtonBuilder(
    model: IModel<String>? = null,
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
    onConfig: (AjaxButton.() -> Unit)? = null,
    override var onSubmit: (AjaxButton.(AjaxRequestTarget) -> Unit)? = null,
    override var onError: (AjaxButton.(AjaxRequestTarget) -> Unit)? = null,
    override val form: Form<*>? = null,
    postInit: (AjaxButton.() -> Unit)? = null
) : IAjaxButtonBuilder,
    ComponentBuilder<AjaxButton, String>(
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

    override fun build(id: String): AjaxButton =
        ajaxButtonFactory(
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
            form = form,
            postInit = postInit
        )

}
