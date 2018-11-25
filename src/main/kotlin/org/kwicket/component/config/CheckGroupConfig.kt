package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.extensions.markup.html.form.datetime.TimeField
import org.apache.wicket.markup.html.form.CheckBox
import org.apache.wicket.markup.html.form.CheckGroup
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator

/**
 * Configuration for creating a [CheckGroup].
 *
 * @param T type of the model
 * @param L type of the [Collection] containing the model objects
 */
interface ICheckGroupConfig<T, L: Collection<T>> : IFormComponentConfig<CheckGroup<T>, L>

/**
 * Configuration for creating a [CheckGroup] component.
 *
 * @param T type of the model
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
class CheckGroupConfig<T, L: Collection<T>>(
    model: IModel<L>? = null,
    label: IModel<String>? = null,
    isRequired: Boolean? = null,
    validator: IValidator<in L>? = null,
    validators: List<IValidator<in L>>? = null,
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
    onConfig: (CheckGroup<T>.() -> Unit)? = null,
    postInit: (CheckGroup<T>.() -> Unit)? = null
) : ICheckGroupConfig<T, L>,
    FormComponentConfig<CheckGroup<T>, L>(
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