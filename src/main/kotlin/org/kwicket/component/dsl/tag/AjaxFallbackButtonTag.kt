package org.kwicket.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.model.IModel
import org.kwicket.component.config.AjaxFallbackButtonConfig
import org.kwicket.component.config.IAjaxFallbackButtonConfig
import org.kwicket.component.dsl.ComponentTag
import org.kwicket.component.factory.invoke

fun HTMLTag.ajaxFallbackButton(
    id: String? = null,
    tagName: String = "button",
    model: IModel<String>? = null,
    form: Form<*>? = null,
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
    block: AjaxFallbackButtonTag.() -> Unit = {}
): Unit =
    AjaxFallbackButtonTag(
        id = id,
        tagName = tagName,
        config = AjaxFallbackButtonConfig(
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

open class AjaxFallbackButtonTag(
    id: String? = null,
    tagName: String = "button",
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>,
    val config: IAjaxFallbackButtonConfig
) : IAjaxFallbackButtonConfig by config,
    ComponentTag<AjaxFallbackButton>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName
    ), HtmlBlockTag {

    override fun build(id: String) = config.invoke(id)

}