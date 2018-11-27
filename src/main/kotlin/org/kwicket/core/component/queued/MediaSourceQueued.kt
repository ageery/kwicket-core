package org.kwicket.core.component.queued

import org.apache.wicket.MarkupContainer
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.media.Source
import org.apache.wicket.model.IModel
import org.apache.wicket.request.mapper.parameter.PageParameters
import org.apache.wicket.request.resource.ResourceReference
import org.kwicket.core.component.config.IMediaSourceConfig
import org.kwicket.core.component.config.MediaSourceConfig
import org.kwicket.core.component.factory.invoke
import org.kwicket.core.component.q

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
): Source = q(
    id = id,
    block = block,
    factory = { cid, config -> config(cid) },
    config = MediaSourceConfig<Unit>(
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
): Source = q(
    id = id, block = block, factory = { cid, config -> config(cid) }, config = MediaSourceConfig(
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