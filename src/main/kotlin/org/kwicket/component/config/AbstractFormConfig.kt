package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.model.IModel
import org.kwicket.FileSize

interface IAbstractFormConfig<C: Form<T>, T> : IComponentConfig<C, T> {
    var onSubmit: (C.() -> Unit)?
    var onError: (C.() -> Unit)?
    var isMultiPart: Boolean?
    var maxSize: FileSize?
    var fileMaxSize: FileSize?
    override val requiresSubclass: Boolean
        get() = super.requiresSubclass || onSubmit != null || onError != null
}

open class AbstractFormConfig<C: Form<T>, T>(
    model: IModel<T>? = null,
    override var onSubmit: (C.() -> Unit)? = null,
    override var onError: (C.() -> Unit)? = null,
    override var isMultiPart: Boolean? = null,
    override var maxSize: FileSize? = null,
    override var fileMaxSize: FileSize? = null,
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
    onConfig: (C.() -> Unit)? = null,
    postInit: (C.() -> Unit)? = null
) : IAbstractFormConfig<C, T>,
    ComponentConfig<C, T>(
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
        stateless = stateless,
        onConfig = onConfig,
        postInit = postInit
    )