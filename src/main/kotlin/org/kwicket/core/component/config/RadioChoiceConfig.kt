package org.kwicket.core.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.IChoiceRenderer
import org.apache.wicket.markup.html.form.RadioChoice
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator

/**
 * Configuration for creating a [RadioChoice] component.
 *
 * @param T type of the model
 * @property choices list of values
 * @property choiceRenderer how the choices should be displayed
 */
interface IRadioChoiceConfig<T> : IFormComponentConfig<RadioChoice<T>, T> {
    var choices: IModel<out List<T>>?
    var choiceRenderer: IChoiceRenderer<in T>?
}

/**
 * Configuration for creating a [RadioChoice] component.
 *
 * @param T type of the model
 * @property choices list of values
 * @property choiceRenderer how the choices should be displayed
 * @param label label for the field
 * @param isRequired whether the data in the field is required
 * @param validator validator to check whether the data in the field is valid
 * @param validators validators to check whether the data in the field is valid
 * @param model model for the component
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
class RadioChoiceConfig<T>(
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
    onConfig: (RadioChoice<T>.() -> Unit)? = null,
    postInit: (RadioChoice<T>.() -> Unit)? = null
) : IRadioChoiceConfig<T>,
    FormComponentConfig<RadioChoice<T>, T>(
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