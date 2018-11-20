package org.kwicket.component.queued

import org.apache.wicket.MarkupContainer
import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.markup.html.form.AjaxButton
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.model.IModel
import org.kwicket.component.config.AjaxButtonConfig
import org.kwicket.component.config.IAjaxButtonConfig
import org.kwicket.component.factory.ajaxButtonFactory

/**
 * Creates and queues a [AjaxButton] into the parent container.
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
 * @return the created [AjaxButton] that has been queued into the parent container
 */

fun MarkupContainer.ajaxButton(
    id: String,
    model: IModel<String>? = null,
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
    onConfig: (AjaxButton.() -> Unit)? = null,
    onSubmit: (AjaxButton.(AjaxRequestTarget) -> Unit)? = null,
    onError: (AjaxButton.(AjaxRequestTarget) -> Unit)? = null,
    form: Form<*>? = null,
    postInit: (AjaxButton.() -> Unit)? = null,
    block: (IAjaxButtonConfig.() -> Unit)? = null
) = q(
    id = id, block = block, factory = ::ajaxButtonFactory, config = AjaxButtonConfig(
        model = model,
        markupId = markupId,
        outputMarkupId = outputMarkupId,
        outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
        isVisible = visible,
        isEnabled = enabled,
        isVisibilityAllowed = visibilityAllowed,
        escapeModelStrings = escapeModelStrings,
        renderBodyOnly = renderBodyOnly,
        behavior = behavior,
        behaviors = behaviors,
        onConfig = onConfig,
        onSubmit = onSubmit,
        onError = onError,
        form = form,
        postInit = postInit
    )
)

/*
 * - config: IAjaxButtonConfig = properties of a component; how to create a component
 * - factory: ajaxButtonFactory = id + config => comp = how to create a component from config and an id
 * - builder (config + factory): id + ajaxButton(config: IAjaxButtonConfig): AjaxButton = associates a factory with config
 * ajaxButton(ajaxConfig()) -- yuck!
 *
 * "myLink" + ajaxLink(model = "hi!".model()) {
 *   onClick = { target ->
 *     println("hi! clicked")
 *   }
 * }
 *
 * val container = webMarkupContainer() to "here"
 *
 * val label = label(model = "hi".model()) to "myLabel"
 *
 * ajaxLink(model = "hi!".model()) {
 *   onClick = { target ->
 *     println("hi! clicked")
 *   }
 * } + "myLink"
 *
 * q("id", ajaxLinkConfig(model = "myLink") {
 *   onClick = { target ->
 *     println("hi")
 *   }
 * }
 *
 * val c = webMarkupContainerConfig() + "here"
 *
 * q "id" ajaxLinkConfig(model = ...)
 *
 *
 */