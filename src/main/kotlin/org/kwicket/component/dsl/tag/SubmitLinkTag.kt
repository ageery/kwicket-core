package org.kwicket.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.markup.html.form.SubmitLink
import org.apache.wicket.model.IModel
import org.kwicket.component.config.ISubmitLinkConfig
import org.kwicket.component.config.SubmitLinkConfig
import org.kwicket.component.dsl.ConfigurableComponentTag
import org.kwicket.component.factory.invoke

fun <T> HTMLTag.submitLink(
    id: String? = null,
    form: Form<*>? = null,
    tagName: String = "a",
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
    initialAttributes: Map<String, String> = emptyMap(),
    block: SubmitLinkTag<T>.() -> Unit = {}
): Unit =
    SubmitLinkTag(
        id = id,
        tagName = tagName,
        config = SubmitLinkConfig(
            model = model,
            form = form,
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

open class SubmitLinkTag<T>(
    id: String? = null,
    tagName: String = "a",
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>,
    config: ISubmitLinkConfig<T>,
    factory: (String, ISubmitLinkConfig<T>) -> SubmitLink = { cid, c -> c(cid) }
) : ISubmitLinkConfig<T> by config,
    ConfigurableComponentTag<T, SubmitLink, ISubmitLinkConfig<T>>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName,
        config = config,
        factory = factory
    ), HtmlBlockTag