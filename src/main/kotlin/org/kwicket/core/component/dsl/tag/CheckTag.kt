package org.kwicket.core.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.Check
import org.apache.wicket.model.IModel
import org.kwicket.core.component.config.CheckConfig
import org.kwicket.core.component.config.ICheckConfig
import org.kwicket.core.component.dsl.ConfigurableComponentTag
import org.kwicket.core.component.factory.invoke

fun <T> HTMLTag.check(
    id: String? = null,
    tagName: String = "input",
    model: IModel<T>? = null,
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
    initialAttributes: Map<String, String> = mapOf("type" to "checkbox"),
    block: CheckTag<T>.() -> Unit = {}
): Unit =
    CheckTag(
        id = id,
        tagName = tagName,
        config = CheckConfig(
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
            behaviors = behaviors
        ),
        initialAttributes = initialAttributes,
        consumer = consumer
    ).visit(block)

open class CheckTag<T>(
    id: String? = null,
    tagName: String = "input",
    initialAttributes: Map<String, String> = mapOf("type" to "checkbox"),
    consumer: TagConsumer<*>,
    config: ICheckConfig<T>,
    factory: (String, ICheckConfig<T>) -> Check<T> = { cid, c -> c(cid) }
) : ICheckConfig<T> by config,
    ConfigurableComponentTag<T, Check<T>, ICheckConfig<T>>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName,
        config = config,
        factory = factory
    ), HtmlBlockTag