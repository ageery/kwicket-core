package org.kwicket.component.factory

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.model.IModel
import org.kwicket.FileSize
import org.kwicket.component.config
import org.kwicket.hasNonNull

fun <T> formFactory(
    id: String,
    model: IModel<T>? = null,
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
    onConfig: (Form<T>.() -> Unit)? = null,
    onSubmit: (Form<T>.() -> Unit)? = null,
    onError: (Form<T>.() -> Unit)? = null,
    isMultiPart: Boolean? = null,
    maxSize: FileSize? = null,
    fileMaxSize: FileSize? = null,
    postInit: (Form<T>.() -> Unit)? = null
): Form<T> =
    if (hasNonNull(onConfig, onSubmit, onError)) {
        object : Form<T>(id, model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun onSubmit() {
                super.onConfigure()
                onSubmit?.invoke(this)
            }

            override fun onError() {
                super.onConfigure()
                onError?.invoke(this)
            }

        }
    } else {
        Form<T>(id, model)
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
    ).also { form ->
        isMultiPart?.let { form.isMultiPart = it }
        maxSize?.let { form.maxSize = it.bytes }
        fileMaxSize?.let { form.fileMaxSize = it.bytes }
        postInit?.invoke(form)
    }