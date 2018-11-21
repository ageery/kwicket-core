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
import org.kwicket.component.config.ISourceConfig
import org.kwicket.component.config.SourceConfig
import org.kwicket.component.dsl.ConfigurableComponentTag
import org.kwicket.component.factory.invoke

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
        id = id,
        tagName = tagName,
        config = SourceConfig(
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
            onConfig = onConfig,
            sizes = sizes,
            xValues = xValues,
            media = media
        ),
        initialAttributes = initialAttributes,
        consumer = consumer
    ).visit(block)

open class SourceTag<T>(
    id: String? = null,
    tagName: String = "source",
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>,
    config: ISourceConfig<T>,
    factory: (String, ISourceConfig<T>) -> Source = { cid, c -> c(cid) }
) : ISourceConfig<T> by config,
    ConfigurableComponentTag<T, Source, ISourceConfig<T>>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName,
        config = config,
        factory = factory
    ), HtmlBlockTag