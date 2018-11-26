package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.extensions.markup.html.form.datetime.ZonedDateTimeField
import org.apache.wicket.markup.html.media.MediaComponent
import org.apache.wicket.markup.html.media.video.Video
import org.apache.wicket.model.IModel
import org.apache.wicket.request.mapper.parameter.PageParameters
import org.apache.wicket.request.resource.ResourceReference

/**
 * Configuration for creating a [Video] component.
 *
 * @param T type of the model
 * @property width width of the video player
 * @property height height of the video player
 * @property poster the image to use to represent the video
 */
interface IVideoConfig<T> : IMediaComponentConfig<Video, T> {
    var width: Int?
    var height: Int?
    var poster: ResourceReference?
}

// FIXME: Add missing properties

/**
 * Configuration for creating a [ZonedDateTimeField] component.
 *
 * @param T type of the model
 * @property width width of the video player
 * @property height height of the video player
 * @property poster the image to use to represent the video
 * @param model model for the component
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