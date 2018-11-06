package org.kwicket.component.factory

import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.model.IModel
import org.kwicket.component.config
import java.util.*

fun <T> ajaxFallbackLinkFactory(
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
    onConfig: (AjaxFallbackLink<T>.() -> Unit)? = null,
    onClick: (AjaxFallbackLink<T>.(AjaxRequestTarget?) -> Unit)? = null,
    postInit: (AjaxFallbackLink<T>.() -> Unit)? = null
): AjaxFallbackLink<T> =
    object : AjaxFallbackLink<T>(id, model) {

        override fun onConfigure() {
            super.onConfigure()
            onConfig?.invoke(this)
        }

        override fun onClick(target: Optional<AjaxRequestTarget>) {
            onClick?.invoke(this, target.orElse(null))
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
    ).also {
        postInit?.invoke(it)
    }