package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.Radio
import org.apache.wicket.markup.html.form.RadioGroup
import org.apache.wicket.model.IModel

/**
 * Configuration for creating a [Radio] component.
 *
 * @param T type of the model
 * @property label label associated with the radio
 * @property group [RadioGroup] the radio belongs to
 */
interface IRadioConfig<T> : IComponentConfig<Radio<T>, T> {
    var label: IModel<String>?
    var group: RadioGroup<T>?
}

/**
 * Configuration for creating a [Radio] component.
 *
 * @param T type of the model
 * @property label label for the field
 * @property group [RadioGroup] the radio belongs to
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
class RadioConfig<T>(
    model: IModel<T>? = null,
    override var group: RadioGroup<T>? = null,
    override var label: IModel<String>? = null,
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
    onConfig: (Radio<T>.() -> Unit)? = null,
    postInit: (Radio<T>.() -> Unit)? = null
) : IRadioConfig<T>,
    ComponentConfig<Radio<T>, T>(
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