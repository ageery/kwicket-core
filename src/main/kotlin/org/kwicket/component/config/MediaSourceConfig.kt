package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.media.Source
import org.apache.wicket.model.IModel
import org.apache.wicket.request.mapper.parameter.PageParameters
import org.apache.wicket.request.resource.ResourceReference

interface IMediaSourceConfig<T> : IComponentConfig<Source, T> {
    var resRef: ResourceReference?
    var url: String?
    var pageParams: PageParameters?
    var isDisplayType: Boolean?
    var type: String?
    var media: String?
}

class MediaSourceConfig<T>(
    model: IModel<T>? = null,
    override var resRef: ResourceReference? = null,
    override var url: String? = null,
    override var pageParams: PageParameters? = null,
    override var isDisplayType: Boolean? = null,
    override var type: String? = null,
    override var media: String? = null,
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
    onConfig: (Source.() -> Unit)? = null,
    postInit: (Source.() -> Unit)? = null
) : IMediaSourceConfig<T>,
    ComponentConfig<Source, T>(
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