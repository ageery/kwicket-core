package org.kwicket.component.queued

import org.apache.wicket.MarkupContainer
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.media.MediaComponent
import org.apache.wicket.markup.html.media.audio.Audio
import org.apache.wicket.model.IModel
import org.apache.wicket.request.mapper.parameter.PageParameters
import org.apache.wicket.request.resource.ResourceReference
import org.kwicket.component.config.AudioConfig
import org.kwicket.component.config.IAudioConfig
import org.kwicket.component.factory.audioFactory
import org.kwicket.component.q

fun MarkupContainer.audio(
    id: String,
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
    onConfig: (Audio.() -> Unit)? = null,
    postInit: (Audio.() -> Unit)? = null,
    block: (IAudioConfig<*>.() -> Unit)? = null
): Audio = audio<Any?>(
    id = id, block = block, config = AudioConfig(
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

fun <T> MarkupContainer.audio(
    id: String,
    model: IModel<T>? = null,
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
    onConfig: (Audio.() -> Unit)? = null,
    postInit: (Audio.() -> Unit)? = null,
    block: (IAudioConfig<T>.() -> Unit)? = null
): Audio = audio(
    id = id, block = block, config = AudioConfig(
        model = model,
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

fun <T> MarkupContainer.audio(
    id: String,
    config: IAudioConfig<T>,
    block: (IAudioConfig<T>.() -> Unit)? = null
): Audio {
    block?.invoke(config)
    return q(audioFactory(id = id, config = config))
}