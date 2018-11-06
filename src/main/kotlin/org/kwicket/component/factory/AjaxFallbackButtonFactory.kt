package org.kwicket.component.factory

import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.model.IModel
import org.kwicket.component.config
import java.util.*

fun ajaxFallbackButtonFactory(
    id: String,
    model: IModel<String>? = null,
    defaultFormProcessing: Boolean? = null,
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
    onConfig: (AjaxFallbackButton.() -> Unit)? = null,
    onSubmit: (AjaxFallbackButton.(AjaxRequestTarget?) -> Unit)? = null,
    onError: (AjaxFallbackButton.(AjaxRequestTarget?) -> Unit)? = null,
    form: Form<*>? = null,
    postInit: (AjaxFallbackButton.() -> Unit)? = null
): AjaxFallbackButton =
    object : AjaxFallbackButton(id, model, form) {

        override fun onConfigure() {
            super.onConfigure()
            onConfig?.invoke(this)
        }

        override fun onSubmit(target: Optional<AjaxRequestTarget>) {
            super.onSubmit()
            onSubmit?.invoke(this, target.orElse(null))
        }

        override fun onError(target: Optional<AjaxRequestTarget>) {
            super.onError()
            onError?.invoke(this, target.orElse(null))
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
    ).apply {
        defaultFormProcessing?.let { this.defaultFormProcessing = it }
    }.also {
        postInit?.invoke(it)
    }