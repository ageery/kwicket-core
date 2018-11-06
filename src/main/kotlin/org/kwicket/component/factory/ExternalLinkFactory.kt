package org.kwicket.component.factory

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.link.ExternalLink
import org.apache.wicket.model.IModel
import org.kwicket.component.config

// FIXME: do settings

fun externalLinkFactory(
    id: String,
    model: IModel<String>? = null,
    label: IModel<*>? = null,
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
    onConfig: (ExternalLink.() -> Unit)? = null,
    postInit: (ExternalLink.() -> Unit)? = null
): ExternalLink =
    if (onConfig != null) {
        object : ExternalLink(id, model, label) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig.invoke(this)
            }
        }
    } else {
        ExternalLink(id, model, label)
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