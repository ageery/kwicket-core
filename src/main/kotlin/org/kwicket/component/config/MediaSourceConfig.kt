package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.basic.MultiLineLabel
import org.apache.wicket.markup.html.media.Source
import org.apache.wicket.model.IModel
import org.apache.wicket.request.mapper.parameter.PageParameters
import org.apache.wicket.request.resource.ResourceReference

/**
 * Configuration for creating a media [Source] component.
 *
 * @param T type of the model
 * @property resRef resource reference of the media
 * @property url URL to the source
 * @property pageParams parameters to add to the URL
 * @property isDisplayType whether it is a display type
 * @property type type of the source
 * @property media type of the media
 */
interface IMediaSourceConfig<T> : IComponentConfig<Source, T> {
    var resRef: ResourceReference?
    var url: String?
    var pageParams: PageParameters?
    var isDisplayType: Boolean?
    var type: String?
    var media: String?
}

/**
 * Configuration for creating a media [Source] component.
 *
 * @param T type of the model
 * @property resRef resource reference of the media
 * @property url URL to the source
 * @property pageParams parameters to add to the URL
 * @property isDisplayType whether it is a display type
 * @property type type of the source
 * @property media type of the media
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