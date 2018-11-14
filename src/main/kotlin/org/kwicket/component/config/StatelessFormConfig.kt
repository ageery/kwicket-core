package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.StatelessForm
import org.apache.wicket.model.IModel

// FIXME: I wonder if we should add id...

interface IStatelessFormConfig<T> : IComponentConfig<StatelessForm<T>, T> {
    var onSubmit: (StatelessForm<T>.() -> Unit)?
    var onError: (StatelessForm<T>.() -> Unit)?
}

class StatelessFormConfig<T>(
    model: IModel<T>? = null,
    override var onSubmit: (StatelessForm<T>.() -> Unit)? = null,
    override var onError: (StatelessForm<T>.() -> Unit)? = null,
    markupId: String? = null,
    outputMarkupId: Boolean? = null,
    outputMarkupPlaceholderTag: Boolean? = null,
    isVisible: Boolean? = null,
    isVisibilityAllowed: Boolean? = null,
    isEnabled: Boolean? = null,
    escapeModelStrings: Boolean? = null,
    renderBodyOnly: Boolean? = null,
    behavior: Behavior? = null,
    behaviors: List<Behavior>? = null,
    onConfig: (StatelessForm<T>.() -> Unit)? = null,
    postInit: (StatelessForm<T>.() -> Unit)? = null
) : IStatelessFormConfig<T>,
    ComponentConfig<StatelessForm<T>, T>(
        model = model,
        markupId = markupId,
        outputMarkupId = outputMarkupId,
        outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
        isVisible = isVisible,
        isVisibilityAllowed = isVisibilityAllowed,
        isEnabled = isEnabled,
        escapeModelStrings = escapeModelStrings,
        renderBodyOnly = renderBodyOnly,
        behavior = behavior,
        behaviors = behaviors,
        onConfig = onConfig,
        postInit = postInit
    )