package org.kwicket.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.ajax.markup.html.form.AjaxButton
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.model.IModel
import org.kwicket.component.config.AjaxButtonConfig
import org.kwicket.component.config.IAjaxButtonConfig
import org.kwicket.component.dsl.ComponentTag
import org.kwicket.component.factory.invoke

fun HTMLTag.ajaxButton(
    id: String? = null,
    tagName: String = "button",
    model: IModel<String>? = null,
    form: Form<*>? = null,
    markupId: String? = null,
    outputMarkupId: Boolean? = null,
    outputMarkupPlaceholderTag: Boolean? = null,
    isVisible: Boolean? = null,
    isVisibilityAllowed: Boolean? = null,
    isEnabled: Boolean? = null,
    escapeModelStrings: Boolean? = null,
    renderBodyOnly: Boolean? = null,
    behavior: Behavior? = null,
    behaviors: List<Behavior>? = null,
    block: AjaxButtonTag.() -> Unit = {}
): Unit =
    AjaxButtonTag(
        id = id,
        tagName = tagName,
        config = AjaxButtonConfig(
            model = model,
            form = form,
            markupId = markupId,
            outputMarkupId = outputMarkupId,
            outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
            isVisible = isVisible,
            isVisibilityAllowed = isVisibilityAllowed,
            isEnabled = isEnabled,
            escapeModelStrings = escapeModelStrings,
            renderBodyOnly = renderBodyOnly,
            behavior = behavior,
            behaviors = behaviors
        ),
        consumer = consumer
    ).visit(block)

class AjaxButtonTag(
    id: String? = null,
    tagName: String = "button",
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>,
    val config: IAjaxButtonConfig
) : IAjaxButtonConfig by config,
    ComponentTag<AjaxButton>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName
    ),
    HtmlBlockTag {

    override fun build(id: String) = config(id)

}