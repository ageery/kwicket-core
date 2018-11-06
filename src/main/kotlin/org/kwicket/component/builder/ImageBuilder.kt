package org.kwicket.component.builder

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.image.Image
import org.apache.wicket.model.IModel
import org.apache.wicket.request.mapper.parameter.PageParameters
import org.apache.wicket.request.resource.IResource
import org.apache.wicket.request.resource.ResourceReference
import org.kwicket.component.factory.imageFactory

interface IImageBuilder<T> : IComponentBuilder<Image, T> {
    var resourceReference: ResourceReference?
    var resourceParameters: PageParameters?
    var resourceReferences: List<ResourceReference>?
    var imageResource: IResource?
    var imageResources: List<IResource>?
}

class ImageBuilder<T>(
    override var resourceReference: ResourceReference? = null,
    override var resourceParameters: PageParameters? = null,
    override var resourceReferences: List<ResourceReference>? = null,
    override var imageResource: IResource? = null,
    override var imageResources: List<IResource>? = null,
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
    onConfig: (Image.() -> Unit)? = null,
    postInit: (Image.() -> Unit)? = null
) : IImageBuilder<T>,
    ComponentBuilder<Image, T>(
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

    override fun build(id: String): Image =
        imageFactory(
            id = id,
            resourceReference = resourceReference,
            resourceParameters = resourceParameters,
            resourceReferences = resourceReferences,
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
            postInit = postInit
        )

}
