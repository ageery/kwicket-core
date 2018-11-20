package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.FormComponent
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator

interface IFormComponentConfig<F : FormComponent<C>, C: Any, T: C?> : IComponentConfig<F, T> {
    var label: IModel<String>?
    var isRequired: Boolean?
    var validator: IValidator<C>?
    var validators: List<IValidator<C>>?
}

open class FormComponentConfig<F : FormComponent<C>, C: Any, T: C?>(
    var label: IModel<String>? = null,
    var isRequired: Boolean? = null,
    var validator: IValidator<C>? = null,
    var validators: List<IValidator<C>>? = null,
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
) : ComponentConfig<F, T>(
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