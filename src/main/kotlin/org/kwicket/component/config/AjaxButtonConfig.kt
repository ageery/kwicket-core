package org.kwicket.component.config

import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.markup.html.form.AjaxButton
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.model.IModel

/**
 * Configuration for creating an [AjaxButton].
 *
 * @property onSubmit lambda that is called when the form is successfully submitted
 * @property onError lambda that is called when the form has validation errors
 */
interface IAjaxButtonConfig : IAbstractButtonConfig<AjaxButton> {
    var onSubmit: (AjaxButton.(AjaxRequestTarget) -> Unit)?
    var onError: (AjaxButton.(AjaxRequestTarget) -> Unit)?
}

/**
 * Configuration for creating an [AjaxButton].
 *
 * @param model model of the component
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
 * @param defaultFormProcessing whether the data is submitted by the button
 * @param form the form the button is associated with
 * @property onSubmit lambda that is called when the form is successfully submitted
 * @property onError lambda that is called when the form has validation errors
 */
class AjaxButtonConfig(
    model: IModel<String>? = null,
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
    onConfig: (AjaxButton.() -> Unit)? = null,
    postInit: (AjaxButton.() -> Unit)? = null,
    defaultFormProcessing: Boolean? = null,
    form: Form<*>? = null,
    override var onSubmit: (AjaxButton.(AjaxRequestTarget) -> Unit)? = null,
    override var onError: (AjaxButton.(AjaxRequestTarget) -> Unit)? = null
) : IAjaxButtonConfig,
    AbstractButtonConfig<AjaxButton>(
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
        postInit = postInit,
        defaultFormProcessing = defaultFormProcessing,
        form = form
    )