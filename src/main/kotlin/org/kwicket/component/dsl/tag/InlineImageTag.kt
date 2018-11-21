package org.kwicket.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.image.InlineImage
import org.apache.wicket.model.IModel
import org.apache.wicket.request.resource.PackageResourceReference
import org.kwicket.component.config.IInlineImageConfig
import org.kwicket.component.config.InlineImageConfig
import org.kwicket.component.dsl.ConfigurableComponentTag
import org.kwicket.component.factory.invoke

fun <T> HTMLTag.inlineImage(
    resRef: PackageResourceReference,
    model: IModel<T>? = null,
    tagName: String = "img",
    id: String? = null,
    markupId: String? = null,
    outputMarkupId: Boolean? = null,
    outputMarkupPlaceholderTag: Boolean? = null,
    visible: Boolean? = null,
    visibilityAllowed: Boolean? = null,
    enabled: Boolean? = null,
    escapeModelStrings: Boolean? = null,
    renderBodyOnly: Boolean? = null,
    behavior: Behavior? = null,
    behaviors: List<Behavior>? = null,
    onConfig: (InlineImage.() -> Unit)? = null,
    initialAttributes: Map<String, String> = emptyMap(),
    block: InlineImageTag<T>.() -> Unit = {}
): Unit =
    InlineImageTag(
        id = id,
        tagName = tagName,
        config = InlineImageConfig(
            resRef = resRef,
            model = model,
            markupId = markupId,
            outputMarkupId = outputMarkupId,
            outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
            isVisible = visible,
            isVisibilityAllowed = visibilityAllowed,
            isEnabled = enabled,
            escapeModelStrings = escapeModelStrings,
            renderBodyOnly = renderBodyOnly,
            behavior = behavior,
            behaviors = behaviors,
            onConfig = onConfig
        ),
        initialAttributes = initialAttributes,
        consumer = consumer
    ).visit(block)

open class InlineImageTag<T>(
    id: String? = null,
    tagName: String = "img",
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>,
    config: IInlineImageConfig<T>,
    factory: (String, IInlineImageConfig<T>) -> InlineImage = { cid, c -> c(cid) }
) : IInlineImageConfig<T> by config,
    ConfigurableComponentTag<T, InlineImage, IInlineImageConfig<T>>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName,
        config = config,
        factory = factory
    ), HtmlBlockTag