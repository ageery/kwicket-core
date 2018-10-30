package org.kwicket.component.factory

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.model.IModel
import org.kwicket.component.config

fun webMarkupContainerFactory(
    id: String,
    model: IModel<*>? = null,
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
    onConfig: (WebMarkupContainer.() -> Unit)? = null,
    postInit: (WebMarkupContainer.() -> Unit)? = null
): WebMarkupContainer =
    if (onConfig != null) {
        object : WebMarkupContainer(id, model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig.invoke(this)
            }

        }
    } else {
        WebMarkupContainer(id, model)
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