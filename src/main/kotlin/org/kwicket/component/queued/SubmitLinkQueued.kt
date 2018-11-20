package org.kwicket.component.queued

import org.apache.wicket.MarkupContainer
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.markup.html.form.SubmitLink
import org.apache.wicket.model.IModel
import org.kwicket.component.config.SubmitLinkConfig
import org.kwicket.component.factory.submitLinkFactory

/**
 * Creates and queues a [Label] into the parent container.
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
 * @return the created [Label] that has been queued into the parent container
 */
fun <T> MarkupContainer.submitLink(
    id: String,
    form: Form<*>? = null,
    model: IModel<T>? = null,
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
    onConfig: (SubmitLink.() -> Unit)? = null,
    postInit: (SubmitLink.() -> Unit)? = null,
    block: (SubmitLinkConfig<T>.() -> Unit)? = null
): SubmitLink = q(
    id = id, block = block, factory = { cid, config -> submitLinkFactory(cid, config) }, config = SubmitLinkConfig(
        model = model,
        form = form,
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
        postInit = postInit
    )
)