package org.kwicket.component.queued

import org.apache.wicket.MarkupContainer
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.media.MediaComponent
import org.apache.wicket.markup.html.media.video.Video
import org.apache.wicket.model.IModel
import org.apache.wicket.request.mapper.parameter.PageParameters
import org.apache.wicket.request.resource.ResourceReference
import org.kwicket.component.config.IVideoConfig
import org.kwicket.component.config.VideoConfig
import org.kwicket.component.factory.invoke
import org.kwicket.component.q

fun MarkupContainer.video(
    id: String,
    width: Int? = null,
    height: Int? = null,
    resRef: ResourceReference? = null,
    url: String? = null,
    pageParams: PageParameters? = null,
    isMuted: Boolean? = null,
    hasControls: Boolean? = null,
    preload: MediaComponent.Preload? = null,
    isAutoPlay: Boolean? = null,
    isLooping: Boolean? = null,
    startTime: String? = null,
    endTime: String? = null,
    mediaGroup: String? = null,
    crossOrigin: MediaComponent.Cors? = null,
    type: String? = null,
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
    onConfig: (Video.() -> Unit)? = null,
    postInit: (Video.() -> Unit)? = null,
    block: (IVideoConfig<*>.() -> Unit)? = null
): Video = q(
    id = id, block = block, factory = { cid, config -> config(cid) }, config = VideoConfig<Unit>(
        width = width,
        height = height,
        resRef = resRef,
        url = url,
        pageParams = pageParams,
        isMuted = isMuted,
        hasControls = hasControls,
        preload = preload,
        isAutoPlay = isAutoPlay,
        isLooping = isLooping,
        startTime = startTime,
        endTime = endTime,
        mediaGroup = mediaGroup,
        crossOrigin = crossOrigin,
        type = type,
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

fun <T> MarkupContainer.video(
    id: String,
    model: IModel<T>? = null,
    width: Int? = null,
    height: Int? = null,
    resRef: ResourceReference? = null,
    url: String? = null,
    pageParams: PageParameters? = null,
    isMuted: Boolean? = null,
    hasControls: Boolean? = null,
    preload: MediaComponent.Preload? = null,
    isAutoPlay: Boolean? = null,
    isLooping: Boolean? = null,
    startTime: String? = null,
    endTime: String? = null,
    mediaGroup: String? = null,
    crossOrigin: MediaComponent.Cors? = null,
    type: String? = null,
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
    onConfig: (Video.() -> Unit)? = null,
    postInit: (Video.() -> Unit)? = null,
    block: (IVideoConfig<T>.() -> Unit)? = null
): Video = video(
    id = id, block = block, config = VideoConfig(
        model = model,
        width = width,
        height = height,
        resRef = resRef,
        url = url,
        pageParams = pageParams,
        isMuted = isMuted,
        hasControls = hasControls,
        preload = preload,
        isAutoPlay = isAutoPlay,
        isLooping = isLooping,
        startTime = startTime,
        endTime = endTime,
        mediaGroup = mediaGroup,
        crossOrigin = crossOrigin,
        type = type,
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

fun <T> MarkupContainer.video(
    id: String,
    config: IVideoConfig<T>,
    block: (IVideoConfig<T>.() -> Unit)? = null
): Video {
    block?.invoke(config)
    return q(config(id))
}