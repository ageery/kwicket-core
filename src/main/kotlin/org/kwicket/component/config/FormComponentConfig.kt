package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.FormComponent
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator

interface IFormComponentConfig<F : FormComponent<in T>, T> : IComponentConfig<F, T> {
    var label: IModel<String>?
    var isRequired: Boolean?
    var validator: IValidator<in T>?
    var validators: List<IValidator<in T>>?
}

abstract class FormComponentConfig<F : FormComponent<in T>, T>(
    override var label: IModel<String>? = null,
    override var isRequired: Boolean? = null,
    override var validator: IValidator<in T>? = null,
    override var validators: List<IValidator<in T>>? = null,
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
    stateless: Boolean? = null,
    onConfig: (F.() -> Unit)? = null,
    postInit: (F.() -> Unit)? = null
) : IFormComponentConfig<F, T>,
    ComponentConfig<F, T>(
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