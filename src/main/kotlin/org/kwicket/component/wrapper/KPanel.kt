package org.kwicket.component.wrapper

import org.apache.wicket.Component
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.model.IModel
import org.kwicket.component.config

/**
 * [Panel] with named and default constructor arguments.
 *
 * @param id wicketId of the [Component]
 * @param model optional [IModel] of the [Component]
 * @param outputMarkupId optional flag indicating whether an wicketId attribute will be created on the HTML element
 * @param outputMarkupPlaceholderTag optional flag indicating whether an wicketId attribtue will be created on the HTML
 *      element, creating a placeholder wicketId if the component is initially not visible
 * @param visible optional flag indicating whether the [Component] is visible
 * @param visibilityAllowed sets whether visibility is allowed on this [Panel] if the parameter is not null
 * @param enabled optional flag indicating whether the [Component] is enabled
 * @param renderBodyOnly optional flag indicating whether only the [Component]'s HTML will be rendered or whether the
 *      tag the [Component] is attached to will also be rendered
 * @param escapeModelStrings optional flag indicating whether the [Component]'s model String values will be escaped
 * @param behavior [Behavior] to add to the [Component]
 * @param behaviors [List] of [Behavior]s to add to the [Component]
 * @param onConfig optional lambda to execute in the onConfigure lifecycle method
 * @param block optional block to execute to configure the component
 */
abstract class KPanel(
    id: String,
    model: IModel<*>? = null,
    markupId: String? = null,
    outputMarkupId: Boolean? = null,
    outputMarkupPlaceholderTag: Boolean? = null,
    visibilityAllowed: Boolean? = null,
    visible: Boolean? = null,
    enabled: Boolean? = null,
    renderBodyOnly: Boolean? = null,
    escapeModelStrings: Boolean? = null,
    behavior: Behavior? = null,
    behaviors: List<Behavior>? = null,
    var onConfig: (Panel.() -> Unit)? = null,
    block: (Panel.() -> Unit)? = null
) : Panel(id, model) {

    init {
        config(
            markupId = markupId,
            outputMarkupId = outputMarkupId,
            outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
            visible = visible,
            visibilityAllowed = visibilityAllowed,
            enabled = enabled,
            escapeModelStrings = escapeModelStrings,
            renderBodyOnly = renderBodyOnly,
            behavior = behavior,
            behaviors = behaviors
        )
        block?.invoke(this)
    }

    override fun onConfigure() {
        super.onConfigure()
        onConfig?.invoke(this)
    }

}