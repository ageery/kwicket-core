package org.kwicket.component.queued

import org.apache.wicket.MarkupContainer
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.StatelessForm
import org.apache.wicket.model.IModel
import org.kwicket.component.config.IStatelessFormConfig
import org.kwicket.component.config.StatelessFormConfig
import org.kwicket.component.factory.statelessFormFactory

fun MarkupContainer.statelessForm(
    id: String,
    onSubmit: (StatelessForm<*>.() -> Unit)? = null,
    onError: (StatelessForm<*>.() -> Unit)? = null,
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
    onConfig: (StatelessForm<*>.() -> Unit)? = null,
    postInit: (StatelessForm<*>.() -> Unit)? = null,
    block: (IStatelessFormConfig<*>.() -> Unit)? = null
): StatelessForm<*> = q(
    id = id, block = block, factory = { cid, config -> statelessFormFactory(cid, config) }, config = StatelessFormConfig<Unit>(
        onSubmit = onSubmit,
        onError = onError,
        markupId = markupId,
        outputMarkupId = outputMarkupId,
        outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
        isVisible = visible,
        isEnabled = enabled,
        isVisibilityAllowed = visibilityAllowed,
        escapeModelStrings = escapeModelStrings,
        renderBodyOnly = renderBodyOnly,
        behavior = behavior,
        behaviors = behaviors,
        onConfig = onConfig,
        postInit = postInit
    )
)

fun <T> MarkupContainer.statelessForm(
    id: String,
    model: IModel<T>? = null,
    onSubmit: (StatelessForm<T>.() -> Unit)? = null,
    onError: (StatelessForm<T>.() -> Unit)? = null,
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
    onConfig: (StatelessForm<T>.() -> Unit)? = null,
    postInit: (StatelessForm<T>.() -> Unit)? = null,
    block: (IStatelessFormConfig<T>.() -> Unit)? = null
): StatelessForm<T> = q(id = id, block = block, factory = { cid, config ->
    statelessFormFactory(cid, config) }, config = StatelessFormConfig(
    model = model,
    onSubmit = onSubmit,
    onError = onError,
    markupId = markupId,
    outputMarkupId = outputMarkupId,
    outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
    isVisible = visible,
    isEnabled = enabled,
    isVisibilityAllowed = visibilityAllowed,
    escapeModelStrings = escapeModelStrings,
    renderBodyOnly = renderBodyOnly,
    behavior = behavior,
    behaviors = behaviors,
    onConfig = onConfig,
    postInit = postInit
))
