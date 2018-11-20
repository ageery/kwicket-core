package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.media.MediaComponent
import org.apache.wicket.markup.html.media.video.Video
import org.apache.wicket.model.IModel
import org.apache.wicket.request.mapper.parameter.PageParameters
import org.apache.wicket.request.resource.ResourceReference

interface IVideoConfig<T> : IMediaComponentConfig<Video, T> {
    var width: Int?
    var height: Int?
    var poster: ResourceReference?
}

class VideoConfig<T>(
    model: IModel<T>? = null,
    override var width: Int? = null,
    override var height: Int? = null,
    override var poster: ResourceReference? = null,
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
    isVisible: Boolean? = null,
    isVisibilityAllowed: Boolean? = null,
    isEnabled: Boolean? = null,
    escapeModelStrings: Boolean? = null,
    renderBodyOnly: Boolean? = null,
    behavior: Behavior? = null,
    behaviors: List<Behavior>? = null,
    stateless: Boolean? = null,
    onConfig: (Video.() -> Unit)? = null,
    postInit: (Video.() -> Unit)? = null
) : IVideoConfig<T>,
    MediaComponentConfig<Video, T>(
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