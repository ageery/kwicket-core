package org.kwicket.core.component.config

import org.apache.wicket.Component
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.CheckBox
import org.apache.wicket.markup.html.form.DropDownChoice
import org.apache.wicket.markup.html.form.IChoiceRenderer
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator

/**
 * Configuration for creating a [DropDownChoice].
 *
 * @param T type of the model
 * @property choices model of the list of choices in the drop-down
 * @property choiceRenderer describes how to read/write the choices from/to the markup
 */
interface IDropDownChoiceConfig<T> : IFormComponentConfig<DropDownChoice<T>, T> {
    var choices: IModel<out List<T>>?
    var choiceRenderer: IChoiceRenderer<in T>?
}

/**
 * Configuration for creating a [DropDownChoice].
 *
 * @param T type of the model
 * @property choices model of the list of choices in the drop-down
 * @property choiceRenderer describes how to read/write the choices from/to the markup
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
 */
class DropDownChoiceConfig<T>(
    model: IModel<T>? = null,
    override var choices: IModel<out List<T>>? = null,
    override var choiceRenderer: IChoiceRenderer<in T>? = null,
    label: IModel<String>? = null,
    isRequired: Boolean? = null,
    validator: IValidator<in T>? = null,
    validators: List<IValidator<in T>>? = null,
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
    onConfig: (DropDownChoice<T>.() -> Unit)? = null,
    postInit: (DropDownChoice<T>.() -> Unit)? = null
) : IDropDownChoiceConfig<T>,
    FormComponentConfig<DropDownChoice<T>, T>(
        model = model,
        label = label,
        isRequired = isRequired,
        validator = validator,
        validators = validators,
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