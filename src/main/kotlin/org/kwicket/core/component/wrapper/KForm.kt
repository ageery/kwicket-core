package org.kwicket.core.component.wrapper

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.model.IModel
import org.kwicket.core.component.config

/**
 * Wrapper for Wicket [Form] with named and default parameters.
 *
 * @param id Wicket component id
 * @param model model for the component
 * @param markupId optional unique id to use in the associated markup
 * @param outputMarkupId whether to include an HTML id for the component in the markup
 * @param outputMarkupPlaceholderTag whether to include a placeholder tag for the component in the markup when the
 * component is not isVisible
 * @param visible whether the component is isVisible
 * @param enabled whether the component is isEnabled
 * @param visibilityAllowed whether the component is allowed to be isVisible
 * @param escapeModelStrings whether model strings should be escaped
 * @param renderBodyOnly whether the tag associated with the component should be included in the markup
 * @param behavior optional [Behavior] to add to the component
 * @param behaviors optional List of [Behavior]s to add to the component
 * @param onConfig optional lambda to execute in the onConfigure lifecycle method
 * @param block optional block to execute to configure the component
 */
class KForm<T>(
    id: String,
    model: IModel<T>? = null,
    markupId: String? = null,
    outputMarkupId: Boolean? = null,
    outputMarkupPlaceholderTag: Boolean? = null,
    visible: Boolean? = null,
    enabled: Boolean? = null,
    visibilityAllowed: Boolean? = null,
    escapeModelStrings: Boolean? = null,
    renderBodyOnly: Boolean? = null,
    behavior: Behavior? = null,
    behaviors: List<Behavior>? = null,
    private val onConfig: (KForm<T>.() -> Unit)? = null,
    private val onSubmit: (KForm<T>.() -> Unit)? = null,
    private val onError: (KForm<T>.() -> Unit)? = null,
    block: (Form<T>.() -> Unit)? = null
) : Form<T>(id, model) {
    init {
        config(
            markupId = markupId,
            outputMarkupId = outputMarkupId,
            outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
            visible = visible,
            enabled = enabled,
            visibilityAllowed = visibilityAllowed,
            escapeModelStrings = escapeModelStrings,
            renderBodyOnly = renderBodyOnly,
            behavior = behavior,
            behaviors = behaviors
        )
        block?.invoke(this)
    }

    override fun onSubmit() {
        super.onSubmit()
        onSubmit?.invoke(this)
    }

    override fun onError() {
        super.onError()
        onError?.invoke(this)
    }

    override fun onConfigure() {
        super.onConfigure()
        onConfig?.invoke(this)
    }

}

