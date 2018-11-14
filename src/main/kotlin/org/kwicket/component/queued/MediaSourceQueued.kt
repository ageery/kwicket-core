package org.kwicket.component.queued

import org.apache.wicket.MarkupContainer
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.media.Source
import org.apache.wicket.model.IModel
import org.apache.wicket.request.mapper.parameter.PageParameters
import org.apache.wicket.request.resource.ResourceReference
import org.kwicket.component.config.IMediaSourceConfig
import org.kwicket.component.config.MediaSourceConfig
import org.kwicket.component.factory.mediaSourceFactory
import org.kwicket.component.q

fun MarkupContainer.mediaSource(
    id: String,
    resRef: ResourceReference? = null,
    url: String? = null,
    pageParams: PageParameters? = null,
    type: String? = null,
    media: String? = null,
    isDisplayType: Boolean? = null,
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
    onConfig: (Source.() -> Unit)? = null,
    postInit: (Source.() -> Unit)? = null,
    block: (IMediaSourceConfig<*>.() -> Unit)? = null
): Source = mediaSource<Any?>(
    id = id, block = block, config = MediaSourceConfig(
        resRef = resRef,
        url = url,
        pageParams = pageParams,
        type = type,
        media = media,
        isDisplayType = isDisplayType,
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

fun <T> MarkupContainer.mediaSource(
    id: String,
    model: IModel<T>? = null,
    resRef: ResourceReference? = null,
    url: String? = null,
    pageParams: PageParameters? = null,
    type: String? = null,
    media: String? = null,
    isDisplayType: Boolean? = null,
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
    onConfig: (Source.() -> Unit)? = null,
    postInit: (Source.() -> Unit)? = null,
    block: (IMediaSourceConfig<T>.() -> Unit)? = null
): Source = mediaSource(
    id = id, block = block, config = MediaSourceConfig(
        model = model,
        resRef = resRef,
        url = url,
        pageParams = pageParams,
        type = type,
        media = media,
        isDisplayType = isDisplayType,
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

fun <T> MarkupContainer.mediaSource(
    id: String,
    config: IMediaSourceConfig<T>,
    block: (IMediaSourceConfig<T>.() -> Unit)? = null
): Source {
    block?.invoke(config)
    return q(mediaSourceFactory(id = id, config = config))
}