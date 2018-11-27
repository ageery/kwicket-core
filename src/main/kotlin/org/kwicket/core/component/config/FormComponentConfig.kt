package org.kwicket.core.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.FormComponent
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator

/**
 * Configuration for creating a sub-class of a [FormComponent] component.
 *
 * @param F type of the form component
 * @param T type of the model
 * @property label associated label of the form component
 * @property isRequired whether a value of the form component is required
 * @property validator how to determine whether the value is valid
 * @property validators list of validation checks
 */
interface IFormComponentConfig<F : FormComponent<in T>, T> : IComponentConfig<F, T> {
    var label: IModel<String>?
    var isRequired: Boolean?
    var validator: IValidator<in T>?
    var validators: List<IValidator<in T>>?
}

/**
 * Configuration for creating a sub-class of a [FormComponent] component.
 *
 * @param model backing model of the component
 * @param markupId optional unique id to use in the associated markup
 * @param outputMarkupId whether to include an HTML id for the component in the markup
 * @param outputMarkupPlaceholderTag whether to include a placeholder tag for the component in the markup when the
 * component is not visible
 * @param isVisible whether the component is initially visible
 * @param isEnabled whether the component is initially enabled
 * @param isVisibilityAllowed whether the component is allowed to be visible
 * @param escapeModelStrings whether model strings should be escaped
 * @param renderBodyOnly whether the tag associated with the component should be included in the markup
 * @param behavior optional [Behavior] to add to the component
 * @param behaviors optional List of [Behavior]s to add to the component
 * @param stateless whether to include a hint that the component is stateless
 * @param onConfig optional lambda to execute in the onConfigure lifecycle method
 * @param postInit optional lambda to execute after the component has been created
 * @property label associated label of the form component
 * @property isRequired whether a value of the form component is required
 * @property validator how to determine whether the value is valid
 * @property validators list of validation checks
 */
abstract class FormComponentConfig<F : FormComponent<in T>, T>(
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
    postInit: (F.() -> Unit)? = null,
    override var label: IModel<String>? = null,
    override var isRequired: Boolean? = null,
    override var validator: IValidator<in T>? = null,
    override var validators: List<IValidator<in T>>? = null
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