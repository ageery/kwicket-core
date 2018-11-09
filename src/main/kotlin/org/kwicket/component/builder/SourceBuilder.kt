package org.kwicket.component.builder

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.image.Source
import org.apache.wicket.model.IModel
import org.apache.wicket.request.mapper.parameter.PageParameters
import org.apache.wicket.request.resource.IResource
import org.apache.wicket.request.resource.ResourceReference
import org.kwicket.component.factory.sourceFactory

interface ISourceBuilder<T> : IComponentBuilder<Source, T> {
    var resRef: ResourceReference?
    var resParams: PageParameters?
    var resRefs: List<ResourceReference>?
    var imageResource: IResource?
    var imageResources: List<IResource>?
    var xValues: List<String>?
    var sizes: List<String>?
    var media: String?
}

class SourceBuilder<T>(
    override var resRef: ResourceReference? = null,
    override var resParams: PageParameters? = null,
    override var resRefs: List<ResourceReference>? = null,
    override var imageResource: IResource? = null,
    override var imageResources: List<IResource>? = null,
    override var xValues: List<String>? = null,
    override var sizes: List<String>? = null,
    override var media: String? = null,
    model: IModel<T>? = null,
    markupId: String? = null,
    outputMarkupId: Boolean? = null,
    outputMarkupPlaceholderTag: Boolean? = null,
    isVisible: Boolean? = null,
    isVisibilityAllowed: Boolean? = null,
    isEnabled: Boolean? = null,
    isEscapeModelStrings: Boolean? = null,
    isRenderBodyOnly: Boolean? = null,
    behavior: Behavior? = null,
    behaviors: List<Behavior>? = null,
    onConfig: (Source.() -> Unit)? = null,
    postInit: (Source.() -> Unit)? = null
) : ISourceBuilder<T>,
    ComponentBuilder<Source, T>(
        model = model,
        markupId = markupId,
        outputMarkupId = outputMarkupId,
        outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
        isVisible = isVisible,
        isVisibilityAllowed = isVisibilityAllowed,
        isEnabled = isEnabled,
        escapeModelStrings = isEscapeModelStrings,
        renderBodyOnly = isRenderBodyOnly,
        behavior = behavior,
        behaviors = behaviors,
        onConfig = onConfig,
        postInit = postInit
    ) {

    override fun build(id: String): Source =
        sourceFactory(
            id = id,
            resRef = resRef,
            resParams = resParams,
            resRefs = resRefs,
            imageResource = imageResource,
            imageResources = imageResources,
            model = model,
            markupId = markupId,
            outputMarkupId = outputMarkupId,
            outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
            visible = isVisible,
            visibilityAllowed = isVisibilityAllowed,
            enabled = isEnabled,
            escapeModelStrings = escapeModelStrings,
            renderBodyOnly = renderBodyOnly,
            behavior = behavior,
            behaviors = behaviors,
            onConfig = onConfig,
            xValues = xValues,
            sizes = sizes,
            media = media,
            postInit = postInit
        )

}
