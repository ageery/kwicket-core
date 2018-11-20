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
import org.kwicket.component.config.IImageConfig
import org.kwicket.component.config.ImageConfig
import org.kwicket.component.dsl.ConfigurableComponentTag
import org.kwicket.component.factory.imageFactory

fun HTMLTag.image(
    resRef: ResourceReference? = null,
    resParams: PageParameters? = null,
    resRefs: List<ResourceReference>? = null,
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
    xValues: List<String>? = null,
    sizes: List<String>? = null,
    onConfig: (Image.() -> Unit)? = null,
    initialAttributes: Map<String, String> = emptyMap(),
    block: ImageTag<*>.() -> Unit = {}
): Unit =
    ImageTag(
        id = id,
        tagName = tagName,
        config = ImageConfig(
            resRef = resRef,
            resParams = resParams,
            resRefs = resRefs,
            imageResource = imageResource,
            imageResources = imageResources,
            model = model,
            markupId = markupId,
            outputMarkupId = outputMarkupId,
            outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
            isVisible = visible,
            isVisibilityAllowed = visibilityAllowed,
            isEnabled = enabled,
            escapeModelStrings = isEscapeModelStrings,
            renderBodyOnly = isRenderBodyOnly,
            behavior = behavior,
            behaviors = behaviors,
            sizes = sizes,
            xValues = xValues,
            onConfig = onConfig
        ),
        initialAttributes = initialAttributes,
        consumer = consumer
    ).visit(block)

open class ImageTag<T>(
    id: String? = null,
    tagName: String = "img",
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>,
    config: IImageConfig<T>,
    factory: (String, IImageConfig<T>) -> Image = { cid, c -> imageFactory(cid, c) }
) : IImageConfig<T> by config,
    ConfigurableComponentTag<T, Image, IImageConfig<T>>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName,
        config = config,
        factory = factory
    ), HtmlBlockTag