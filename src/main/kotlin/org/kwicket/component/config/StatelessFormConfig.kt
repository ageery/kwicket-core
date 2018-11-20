package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.StatelessForm
import org.apache.wicket.model.IModel
import org.kwicket.FileSize

interface IStatelessFormConfig<T> : IAbstractFormConfig<StatelessForm<T>, T>

class StatelessFormConfig<T>(
    model: IModel<T>? = null,
    onSubmit: (StatelessForm<T>.() -> Unit)? = null,
    onError: (StatelessForm<T>.() -> Unit)? = null,
    isMultiPart: Boolean? = null,
    maxSize: FileSize? = null,
    fileMaxSize: FileSize? = null,
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
    stateless: Boolean? = null,
    onConfig: (StatelessForm<T>.() -> Unit)? = null,
    postInit: (StatelessForm<T>.() -> Unit)? = null
) : IStatelessFormConfig<T>,
    AbstractFormConfig<StatelessForm<T>, T>(
        model = model,
        onSubmit = onSubmit,
        onError = onError,
        isMultiPart = isMultiPart,
        maxSize = maxSize,
        fileMaxSize = fileMaxSize,
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
        stateless = stateless,
        onConfig = onConfig,
        postInit = postInit
    )