package org.kwicket.component.dsl.tag

import kotlinx.html.*
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.Check
import org.apache.wicket.model.IModel
import org.kwicket.component.builder.CheckBuilder
import org.kwicket.component.builder.CheckBuilder2
import org.kwicket.component.builder.ICheckBuilder
import org.kwicket.component.config.ICheckConfig
import org.kwicket.component.dsl.ComponentTag

//fun <T> HTMLTag.check(
//    id: String? = null,
//    config: ICheckConfig<T>,
//    block: CheckTag<T>.() -> Unit = {}
//): Unit =

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
        model = model,
        markupId = markupId,
        outputMarkupId = outputMarkupId,
        outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
        visible = visible,
        visibilityAllowed = visibilityAllowed,
        enabled = enabled,
        escapeModelStrings = escapeModelStrings,
        renderBodyOnly = renderBodyOnly,
        behavior = behavior,
        behaviors = behaviors,
        initialAttributes = initialAttributes,
        consumer = consumer
    ).visit(block)

fun InputType?.toAttr() = this?.let { mapOf("type" to it.realValue) } ?: emptyMap

// FIXME: I wonder if she use InputType here as a parameter and then map it to the initialAttributes
class CheckTag2<T>(
    id: String? = null,
    tagName: String = "input",
    consumer: TagConsumer<*>,
    inputType: InputType? = InputType.checkBox,
    val config: ICheckConfig<T>
) : ICheckConfig<T> by config,
    ComponentTag<Check<T>>(
        id = id,
        initialAttributes = inputType.toAttr(),
        consumer = consumer,
        tagName = tagName
    ), HtmlBlockTag {

    override fun build(id: String): Check<T> = CheckBuilder2(config).build(id)

}

open class CheckTag<T>(
    id: String? = null,
    tagName: String = "input",
    initialAttributes: Map<String, String> = mapOf("type" to "checkbox"),
    consumer: TagConsumer<*>,
    val builder: CheckBuilder<T>
) : ICheckBuilder<T> by builder,
    ComponentTag<Check<T>>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName
    ), HtmlBlockTag {

    constructor(
        id: String? = null,
        tagName: String = "input",
        initialAttributes: Map<String, String> = mapOf("type" to "checkbox"),
        consumer: TagConsumer<*>,
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
        behaviors: List<Behavior>? = null
    ) : this(
        id = id,
        tagName = tagName,
        initialAttributes = initialAttributes,
        consumer = consumer,
        builder = CheckBuilder(
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
        )
    )

    override fun build(id: String) = builder.build(id)
}