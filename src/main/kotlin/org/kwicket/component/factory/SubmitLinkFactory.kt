package org.kwicket.component.factory

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.markup.html.form.SubmitLink
import org.apache.wicket.model.IModel
import org.kwicket.component.config

fun <T> submitLinkFactory(
    id: String,
    model: IModel<T>? = null,
    form: Form<*>? = null,
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
    onConfig: (SubmitLink.() -> Unit)? = null,
    postInit: (SubmitLink.() -> Unit)? = null
): SubmitLink =
    if (onConfig != null) {
        object : SubmitLink(id, model, form) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig.invoke(this)
            }
        }
    }else {
        SubmitLink(id, model, form)
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
    ).also {
        postInit?.invoke(it)
    }