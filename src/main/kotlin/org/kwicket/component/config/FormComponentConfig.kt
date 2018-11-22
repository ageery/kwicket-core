package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.FormComponent
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator

// C is for satisfying the type for the component -- it is always non null
// T is for the type of the model for the component -- it is either C or C?
// the open question is: what should the various other types be? I think they should almost always be T
// but the onConfig and the postInit are in terms of C!
// Tricky one is LocalDateTextField -- need to accomodate a nullable LocaDate!

interface IFormComponentConfig<F : FormComponent<in T>, T> : IComponentConfig<F, T> {
    var label: IModel<String>?
    var isRequired: Boolean?
    var validator: IValidator<T>?
    var validators: List<IValidator<T>>?
}

open class FormComponentConfig<F : FormComponent<in T>, T>(
    override var label: IModel<String>? = null,
    override var isRequired: Boolean? = null,
    override var validator: IValidator<T>? = null,
    override var validators: List<IValidator<T>>? = null,
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