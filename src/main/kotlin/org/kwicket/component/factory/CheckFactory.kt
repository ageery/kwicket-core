package org.kwicket.component.factory

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.Check
import org.apache.wicket.markup.html.form.CheckGroup
import org.apache.wicket.model.IModel
import org.kwicket.component.config
import org.kwicket.component.config.ICheckConfig
import org.kwicket.component.config.useAnonSubClass

fun <T> checkFactory(
    id: String,
    config: ICheckConfig<T>
): Check<T> =
    if (config.useAnonSubClass()) {
        object : Check<T>(id, config.model, config.group) {
            override fun onConfigure() {
                super.onConfigure()
                config.onConfig?.invoke(this)
            }
        }
    } else {
        Check(id, config.model, config.group)
    }.config(config).apply { config.postInit?.invoke(this) }


fun <T> checkFactory(
    id: String,
    model: IModel<T>? = null,
    group: CheckGroup<T>? = null,
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
    onConfig: (Check<T>.() -> Unit)? = null,
    postInit: (Check<T>.() -> Unit)? = null
): Check<T> =
    if (onConfig != null) {
        object : Check<T>(id, model, group) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig.invoke(this)
            }

        }
    } else {
        Check(id, model, group)
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