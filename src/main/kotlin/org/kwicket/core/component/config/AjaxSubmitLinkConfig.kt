package org.kwicket.core.component.config

import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.markup.html.AjaxLink
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.model.IModel

/**
 * Configuration for creating an [AjaxLink].
 *
 * @param T type of the backing model
 * @property onSubmit lambda that is executed when the form is successfully submitted
 * @property onError lambda that is executed when the form has data validation errors
 * @property form [Form] that the link is submitting
 */
interface IAjaxSubmitLinkConfig<T> : IComponentConfig<AjaxSubmitLink, T> {
    var onSubmit: (AjaxSubmitLink.(AjaxRequestTarget) -> Unit)?
    var onError: (AjaxSubmitLink.(AjaxRequestTarget) -> Unit)?
    var form: Form<*>?
}

/**
 * Configuration for creating a sub-class of a [AjaxSubmitLink].
 *
 * @param T type of the backing model
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
 * @property onSubmit lambda that is executed when the form is successfully submitted
 * @property onError lambda that is executed when the form has data validation errors
 * @property form [Form] that the link is submitting
 */
class AjaxSubmitLinkConfig<T>(
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
    onConfig: (AjaxSubmitLink.() -> Unit)? = null,
    postInit: (AjaxSubmitLink.() -> Unit)? = null,
    override var onSubmit: (AjaxSubmitLink.(AjaxRequestTarget) -> Unit)? = null,
    override var onError: (AjaxSubmitLink.(AjaxRequestTarget) -> Unit)? = null,
    override var form: Form<*>? = null
) : IAjaxSubmitLinkConfig<T>,
    ComponentConfig<AjaxSubmitLink, T>(
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