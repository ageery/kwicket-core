package org.kwicket.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.image.Source
import org.apache.wicket.model.IModel
import org.apache.wicket.request.mapper.parameter.PageParameters
import org.apache.wicket.request.resource.IResource
import org.apache.wicket.request.resource.ResourceReference
import org.kwicket.component.builder.ISourceBuilder
import org.kwicket.component.builder.SourceBuilder
import org.kwicket.component.dsl.ComponentTag

fun HTMLTag.source(
    resRef: ResourceReference? = null,
    resParams: PageParameters? = null,
    resRefs: List<ResourceReference>? = null,
    imageResource: IResource? = null,
    imageResources: List<IResource>? = null,
    model: IModel<*>? = null,
    tagName: String = "source",
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
    media: String? = null,
    onConfig: (Source.() -> Unit)? = null,
    initialAttributes: Map<String, String> = emptyMap(),
    block: SourceTag<*>.() -> Unit = {}
): Unit =
    SourceTag(
        resRef = resRef,
        resParams = resParams,
        resRefs = resRefs,
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
        sizes = sizes,
        xValues = xValues,
        media = media,
        consumer = consumer
    ).visit(block)

open class SourceTag<T>(
    id: String? = null,
    tagName: String = "source",
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>,
    val builder: SourceBuilder<T>
) : ISourceBuilder<T> by builder,
    ComponentTag<Source> (
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName
    ), HtmlBlockTag {
    constructor(
        resRef: ResourceReference? = null,
        resParams: PageParameters? = null,
        resRefs: List<ResourceReference>? = null,
        imageResource: IResource? = null,
        imageResources: List<IResource>? = null,
        id: String? = null,
        onConfig: (Source.() -> Unit)? = null,
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
        xValues: List<String>? = null,
        sizes: List<String>? = null,
        media: String? = null,
        behavior: Behavior? = null,
        behaviors: List<Behavior>? = null
    ) : this(
        id = id,
        tagName = tagName,
        initialAttributes = initialAttributes,
        consumer = consumer,
        builder = SourceBuilder(
            onConfig = onConfig,
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
            isEscapeModelStrings = isEscapeModelStrings,
            isRenderBodyOnly = isRenderBodyOnly,
            xValues = xValues,
            sizes = sizes,
            media = media,
            behavior = behavior,
            behaviors = behaviors
        )
    )

    override fun build(id: String) = builder.build(id)
}