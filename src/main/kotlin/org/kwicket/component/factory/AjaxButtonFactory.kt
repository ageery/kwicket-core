package org.kwicket.component.factory

import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.markup.html.form.AjaxButton
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.model.IModel
import org.kwicket.component.config

// FIXME: default submittions type

fun ajaxBbuttonFactory(
    id: String,
    model: IModel<String>? = null,
    markupId: String? = null,
    outputMarkupId: Boolean? = null,
    outputMarkupPlaceholderTag: Boolean? = null,
    visible: Boolean? = null,
    enabled: Boolean? = null,
    visibilityAllowed: Boolean? = null,
    escapeModelStrings: Boolean? = null,
    renderBodyOnly: Boolean? = null,
    behavior: Behavior? = null,
    behaviors: List<Behavior>? = null,
    onConfig: (AjaxButton.() -> Unit)? = null,
    onSubmit: (AjaxButton.(AjaxRequestTarget) -> Unit)? = null,
    onError: (AjaxButton.(AjaxRequestTarget) -> Unit)? = null,
    form: Form<*>? = null,
    postInit: (AjaxButton.() -> Unit)? = null
): AjaxButton =
    object : AjaxButton(id, model, form) {

        override fun onConfigure() {
            super.onConfigure()
            onConfig?.invoke(this)
        }

        override fun onSubmit(target: AjaxRequestTarget) {
            super.onSubmit()
            onSubmit?.invoke(this, target)
        }

        override fun onError(target: AjaxRequestTarget) {
            super.onError()
            onError?.invoke(this, target)
        }

    }.config(
        markupId = markupId,
        outputMarkupId = outputMarkupId,
        outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
        visible = visible,
        enabled = enabled,
        visibilityAllowed = visibilityAllowed,
        escapeModelStrings = escapeModelStrings,
        renderBodyOnly = renderBodyOnly,
        behavior = behavior,
        behaviors = behaviors
    ).also { postInit?.invoke(it) }