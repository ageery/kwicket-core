package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.Button
import org.apache.wicket.markup.html.form.Check
import org.apache.wicket.markup.html.form.CheckGroup
import org.apache.wicket.model.IModel

/**
 * Configuration for creating a [Button].
 *
 * @property onSubmit lambda to call when the form is successfully submitted
 * @property onError lambda to call when the form has a validation error
 */
interface IButtonConfig : IAbstractButtonConfig<Button> {
    var onSubmit: (Button.() -> Unit)?
    var onError: (Button.() -> Unit)?
}

/**
 * Configuration for creating a [Button].
 *
 * @property onSubmit lambda to call when the form is successfully submitted
 * @property onError lambda to call when the form has a validation error
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
class ButtonConfig(
    model: IModel<String>? = null,
    override var onSubmit: (Button.() -> Unit)? = null,
    override var onError: (Button.() -> Unit)? = null,
    defaultFormProcessing: Boolean? = null,
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
    onConfig: (Button.() -> Unit)? = null,
    postInit: (Button.() -> Unit)? = null
) : IButtonConfig,
    AbstractButtonConfig<Button, String>(
        model = model,
        defaultFormProcessing = defaultFormProcessing,
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