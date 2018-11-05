package org.kwicket.component.factory

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.Button
import org.apache.wicket.model.IModel
import org.kwicket.component.config
import org.kwicket.hasNonNull

fun buttonFactory(
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
    onConfig: (Button.() -> Unit)? = null,
    onSubmit: (Button.() -> Unit)? = null,
    onError: (Button.() -> Unit)? = null,
    postInit: (Button.() -> Unit)? = null
): Button =
    if (hasNonNull(onSubmit, onError, onConfig)) {
        object : Button(id, model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun onSubmit() {
                super.onSubmit()
                onSubmit?.invoke(this)
            }

            override fun onError() {
                super.onError()
                onError?.invoke(this)
            }

        }
    } else {
        Button(id, model)
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