package org.kwicket.component.queued

import org.apache.wicket.MarkupContainer
import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.markup.html.form.AjaxButton
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.Button
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.model.IModel
import org.kwicket.component.builder.AjaxFallbackButtonBuilder
import org.kwicket.component.q

/**
 * Creates and queues a [AjaxButton] into the parent container.
 *
 * @param id Wicket component id
 * @param model model for the component
 * @param markupId optional unique id to use in the associated markup
 * @param outputMarkupId whether to include an HTML id for the component in the markup
 * @param outputMarkupPlaceholderTag whether to include a placeholder tag for the component in the markup when the
 * component is not isVisible
 * @param visible whether the component is isVisible
 * @param enabled whether the component is isEnabled
 * @param visibilityAllowed whether the component is allowed to be isVisible
 * @param escapeModelStrings whether model strings should be escaped
 * @param renderBodyOnly whether the tag associated with the component should be included in the markup
 * @param behavior optional [Behavior] to add to the component
 * @param behaviors optional List of [Behavior]s to add to the component
 * @param onConfig optional lambda to execute in the onConfigure lifecycle method
 * @param block optional block to execute to configure the component
 * @return the created [AjaxButton] that has been queued into the parent container
 */
fun MarkupContainer.ajaxFallbackButton(
    id: String,
    model: IModel<String>? = null,
    markupId: String? = null,
    outputMarkupId: Boolean? = null,
    outputMarkupPlaceholderTag: Boolean? = null,
    visible: Boolean? = null,
    enabled: Boolean? = null,
    visibilityAllowed: Boolean? = null,
    escapeModelStrings: Boolean? = null,
    renderBodyOnly: Boolean? = null,
    behavior: Behavior? = null,
    behaviors: List<Behavior>? = null,
    onConfig: (AjaxFallbackButton.() -> Unit)? = null,
    onSubmit: (AjaxFallbackButton.(AjaxRequestTarget?) -> Unit)? = null,
    onError: (AjaxFallbackButton.(AjaxRequestTarget?) -> Unit)? = null,
    form: Form<*>? = null,
    postInit: (AjaxFallbackButton.() -> Unit)? = null,
    block: (AjaxFallbackButtonBuilder.() -> Unit)? = null
): Button = q(AjaxFallbackButtonBuilder(
    model = model,
    markupId = markupId,
    outputMarkupId = outputMarkupId,
    outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
    isVisible = visible,
    isVisibilityAllowed = visibilityAllowed,
    isEnabled = enabled,
    escapeModelStrings = escapeModelStrings,
    renderBodyOnly = renderBodyOnly,
    behavior = behavior,
    behaviors = behaviors,
    onConfig = onConfig,
    onSubmit = onSubmit,
    onError = onError,
    form = form,
    postInit = postInit
).also { block?.invoke(it) }.build(id))