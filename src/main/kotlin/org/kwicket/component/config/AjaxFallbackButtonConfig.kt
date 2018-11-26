package org.kwicket.component.config

import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.model.IModel

/**
 * Configuration for creating an [AjaxFallbackButton].
 *
 * @property onSubmit lambda that is called when the form is successfully submitted
 * @property onError lambda that is called when the form has validation errors
 */
interface IAjaxFallbackButtonConfig : IAbstractButtonConfig<AjaxFallbackButton> {
    var onSubmit: (AjaxFallbackButton.(AjaxRequestTarget?) -> Unit)?
    var onError: (AjaxFallbackButton.(AjaxRequestTarget?) -> Unit)?
}

/**
 * Configuration for creating an [AjaxFallbackButton].
 *
 * @property onSubmit lambda that is called when the form is successfully submitted
 * @property onError lambda that is called when the form has validation errors
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
class AjaxFallbackButtonConfig(
    model: IModel<String>? = null,
    override var onSubmit: (AjaxFallbackButton.(AjaxRequestTarget?) -> Unit)? = null,
    override var onError: (AjaxFallbackButton.(AjaxRequestTarget?) -> Unit)? = null,
    defaultFormProcessing: Boolean? = null,
    form: Form<*>? = null,
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
    onConfig: (AjaxFallbackButton.() -> Unit)? = null,
    postInit: (AjaxFallbackButton.() -> Unit)? = null
) : IAjaxFallbackButtonConfig,
    AbstractButtonConfig<AjaxFallbackButton>(
        model = model,
        defaultFormProcessing = defaultFormProcessing,
        form = form,
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