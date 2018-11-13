package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.FormComponent
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator

interface IFormComponentConfig<C : FormComponent<T>, T> : IComponentConfig<C, T> {
    var label: IModel<String>?
    var isRequired: Boolean?
    var validator: IValidator<T>?
    var validators: List<IValidator<T>>?
}

open class FormComponentConfig<C : FormComponent<T>, T>(
    var label: IModel<String>? = null,
    var isRequired: Boolean? = null,
    var validator: IValidator<T>? = null,
    var validators: List<IValidator<T>>? = null,
    model: IModel<T>? = null,
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
    onConfig: (C.() -> Unit)? = null,
    postInit: (C.() -> Unit)? = null
) : ComponentConfig<C, T>(
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