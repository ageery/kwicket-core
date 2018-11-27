package org.kwicket.core.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.media.MediaComponent
import org.apache.wicket.model.IModel
import org.apache.wicket.request.mapper.parameter.PageParameters
import org.apache.wicket.request.resource.ResourceReference

interface IMediaComponentConfig<C: MediaComponent, T> : IComponentConfig<C, T> {
    var resRef: ResourceReference?
    var url: String?
    var pageParams: PageParameters?
    var isMuted: Boolean?
    var hasControls: Boolean?
    var preload: MediaComponent.Preload?
    var isAutoPlay: Boolean?
    var isLooping: Boolean?
    var startTime: String?
    var endTime: String?
    var mediaGroup: String?
    var crossOrigin: MediaComponent.Cors?
    var type: String?
}

abstract class MediaComponentConfig<C: MediaComponent, T>(
    model: IModel<T>? = null,
    override var resRef: ResourceReference? = null,
    override var url: String? = null,
    override var pageParams: PageParameters? = null,
    override var isMuted: Boolean? = null,
    override var hasControls: Boolean? = null,
    override var preload: MediaComponent.Preload? = null,
    override var isAutoPlay: Boolean? = null,
    override var isLooping: Boolean? = null,
    override var startTime: String? = null,
    override var endTime: String? = null,
    override var mediaGroup: String? = null,
    override var crossOrigin: MediaComponent.Cors? = null,
    override var type: String? = null,
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
    onConfig: (C.() -> Unit)? = null,
    postInit: (C.() -> Unit)? = null
) : IMediaComponentConfig<C, T>,
        ComponentConfig<C, T>(
            model = model,
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