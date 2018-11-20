package org.kwicket.component.queued

import org.apache.wicket.MarkupContainer
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.image.InlineImage
import org.apache.wicket.markup.html.image.Picture
import org.apache.wicket.model.IModel
import org.apache.wicket.request.resource.PackageResourceReference
import org.kwicket.component.config.IInlineImageConfig
import org.kwicket.component.config.InlineImageConfig
import org.kwicket.component.factory.inlineImageFactory

/**
 * Creates and queues a [Picture] into the parent container.
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
 * @return the created [Picture] that has been queued into the parent container
 */
fun <T> MarkupContainer.inlineImage(
    id: String,
    resRef: PackageResourceReference,
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
    onConfig: (InlineImage.() -> Unit)? = null,
    postInit: (InlineImage.() -> Unit)? = null,
    block: (IInlineImageConfig<T>.() -> Unit)? = null
): InlineImage = q(
    id = id, block = block, factory = { cid, config -> inlineImageFactory(cid, config) }, config =
    InlineImageConfig(
        resRef = resRef,
        model = model,
        markupId = markupId,
        outputMarkupId = outputMarkupId,
        outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
        isVisible = visible,
        isEnabled = enabled,
        isVisibilityAllowed = visibilityAllowed,
        escapeModelStrings = escapeModelStrings,
        renderBodyOnly = renderBodyOnly,
        behavior = behavior,
        behaviors = behaviors,
        onConfig = onConfig,
        postInit = postInit
    )
)

fun MarkupContainer.inlineImage(
    id: String,
    resRef: PackageResourceReference,
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
    onConfig: (InlineImage.() -> Unit)? = null,
    postInit: (InlineImage.() -> Unit)? = null,
    block: (IInlineImageConfig<*>.() -> Unit)? = null
): InlineImage = q(
    id = id, block = block, factory = { cid, config -> inlineImageFactory(cid, config) }, config =
    InlineImageConfig<Any?>(
        resRef = resRef,
        markupId = markupId,
        outputMarkupId = outputMarkupId,
        outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
        isVisible = visible,
        isEnabled = enabled,
        isVisibilityAllowed = visibilityAllowed,
        escapeModelStrings = escapeModelStrings,
        renderBodyOnly = renderBodyOnly,
        behavior = behavior,
        behaviors = behaviors,
        onConfig = onConfig,
        postInit = postInit
    )
)