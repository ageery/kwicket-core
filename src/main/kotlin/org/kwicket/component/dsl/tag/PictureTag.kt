package org.kwicket.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.image.Picture
import org.apache.wicket.model.IModel
import org.kwicket.component.config.IPictureConfig
import org.kwicket.component.config.PictureConfig
import org.kwicket.component.dsl.ConfigurableComponentTag
import org.kwicket.component.factory.invoke

fun <T> HTMLTag.picture(
    model: IModel<T>? = null,
    tagName: String = "picture",
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
    onConfig: (Picture.() -> Unit)? = null,
    initialAttributes: Map<String, String> = emptyMap(),
    block: PictureTag<T>.() -> Unit = {}
): Unit =
    PictureTag(
        id = id,
        tagName = tagName,
        config = PictureConfig(
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

open class PictureTag<T>(
    id: String? = null,
    tagName: String = "picture",
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>,
    config: IPictureConfig<T>,
    factory: (String, IPictureConfig<T>) -> Picture = { cid, c -> c(cid) }
) : IPictureConfig<T> by config,
    ConfigurableComponentTag<T, Picture, IPictureConfig<T>>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName,
        config = config,
        factory = factory
    ), HtmlBlockTag