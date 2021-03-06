package org.kwicket.core.component.config

import org.apache.wicket.ajax.markup.html.AjaxLink
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.markup.html.media.MediaComponent
import org.apache.wicket.markup.html.media.audio.Audio
import org.apache.wicket.model.IModel
import org.apache.wicket.request.mapper.parameter.PageParameters
import org.apache.wicket.request.resource.ResourceReference

/**
 * Configuration for creating an [AjaxLink].
 *
 * @param T type of the model
 */
interface IAudioConfig<T> : IMediaComponentConfig<Audio, T>

/**
 * Configuration for creating a sub-class of a [AjaxSubmitLink].
 * @param model backing model for the component
 * @param resRef reference to the resource
 * @param url
 * @param pageParams
 * @param isMuted
 * @param hasControls
 * @param preload
 * @param isAutoPlay
 * @param isLooping
 * @param startTime
 * @param endTime
 * @param mediaGroup
 * @param crossOrigin
 * @param type
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
class AudioConfig<T>(
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
    isVisible: Boolean? = null,
    isVisibilityAllowed: Boolean? = null,
    isEnabled: Boolean? = null,
    escapeModelStrings: Boolean? = null,
    renderBodyOnly: Boolean? = null,
    behavior: Behavior? = null,
    behaviors: List<Behavior>? = null,
    stateless: Boolean? = null,
    onConfig: (Audio.() -> Unit)? = null,
    postInit: (Audio.() -> Unit)? = null
) : IAudioConfig<T>,
    MediaComponentConfig<Audio, T>(
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