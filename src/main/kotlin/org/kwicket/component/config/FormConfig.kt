package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.model.IModel
import org.kwicket.FileSize

interface IFormConfig<T> : IAbstractFormConfig<Form<T>, T>

class FormConfig<T>(
    model: IModel<T>? = null,
    onSubmit: (Form<T>.() -> Unit)? = null,
    onError: (Form<T>.() -> Unit)? = null,
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
    onConfig: (Form<T>.() -> Unit)? = null,
    postInit: (Form<T>.() -> Unit)? = null
) : IFormConfig<T>,
    AbstractFormConfig<Form<T>, T>(
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