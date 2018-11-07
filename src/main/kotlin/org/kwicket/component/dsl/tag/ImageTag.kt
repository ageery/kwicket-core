package org.kwicket.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.image.Image
import org.apache.wicket.model.IModel
import org.apache.wicket.request.mapper.parameter.PageParameters
import org.apache.wicket.request.resource.IResource
import org.apache.wicket.request.resource.ResourceReference
import org.kwicket.component.builder.IImageBuilder
import org.kwicket.component.builder.ImageBuilder
import org.kwicket.component.dsl.ComponentTag

fun HTMLTag.image(
    resRef: ResourceReference? = null,
    resourceParameters: PageParameters? = null,
    resourceReferences: List<ResourceReference>? = null,
    imageResource: IResource? = null,
    imageResources: List<IResource>? = null,
    model: IModel<*>? = null,
    tagName: String = "img",
    id: String? = null,
    markupId: String? = null,
    outputMarkupId: Boolean? = null,
    outputMarkupPlaceholderTag: Boolean? = null,
    visible: Boolean? = null,
    visibilityAllowed: Boolean? = null,
    enabled: Boolean? = null,
    isEscapeModelStrings: Boolean? = null,
    isRenderBodyOnly: Boolean? = null,
    behavior: Behavior? = null,
    behaviors: List<Behavior>? = null,
    onConfig: (Image.() -> Unit)? = null,
    initialAttributes: Map<String, String> = emptyMap(),
    block: ImageTag<*>.() -> Unit = {}
): Unit =
    ImageTag(
        resourceReference = resRef,
        resourceParameters = resourceParameters,
        resourceReferences = resourceReferences,
        imageResource = imageResource,
        imageResources = imageResources,
        id = id,
        tagName = tagName,
        model = model,
        markupId = markupId,
        outputMarkupId = outputMarkupId,
        outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
        visible = visible,
        visibilityAllowed = visibilityAllowed,
        enabled = enabled,
        isEscapeModelStrings = isEscapeModelStrings,
        isRenderBodyOnly = isRenderBodyOnly,
        behavior = behavior,
        behaviors = behaviors,
        onConfig = onConfig,
        initialAttributes = initialAttributes,
        consumer = consumer
    ).visit(block)

open class ImageTag<T>(
    id: String? = null,
    tagName: String = "img",
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>,
    val builder: ImageBuilder<T>
) : IImageBuilder<T> by builder,
    ComponentTag<Image> (
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName
    ), HtmlBlockTag {
    constructor(
        resourceReference: ResourceReference? = null,
        resourceParameters: PageParameters? = null,
        resourceReferences: List<ResourceReference>? = null,
        imageResource: IResource? = null,
        imageResources: List<IResource>? = null,
        id: String? = null,
        onConfig: (Image.() -> Unit)? = null,
        tagName: String = "img",
        initialAttributes: Map<String, String> = emptyMap(),
        consumer: TagConsumer<*>,
        model: IModel<T>? = null,
        markupId: String? = null,
        outputMarkupId: Boolean? = null,
        outputMarkupPlaceholderTag: Boolean? = null,
        visible: Boolean? = null,
        visibilityAllowed: Boolean? = null,
        enabled: Boolean? = null,
        isEscapeModelStrings: Boolean? = null,
        isRenderBodyOnly: Boolean? = null,
        behavior: Behavior? = null,
        behaviors: List<Behavior>? = null
    ) : this(
        id = id,
        tagName = tagName,
        initialAttributes = initialAttributes,
        consumer = consumer,
        builder = ImageBuilder(
            onConfig = onConfig,
            resourceReference = resourceReference,
            resourceParameters = resourceParameters,
            resourceReferences = resourceReferences,
            imageResource = imageResource,
            imageResources = imageResources,
            model = model,
            markupId = markupId,
            outputMarkupId = outputMarkupId,
            outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
            isVisible = visible,
            isVisibilityAllowed = visibilityAllowed,
            isEnabled = enabled,
            isEscapeModelStrings = isEscapeModelStrings,
            isRenderBodyOnly = isRenderBodyOnly,
            behavior = behavior,
            behaviors = behaviors
        )
    )

    override fun build(id: String) = builder.build(id)
}