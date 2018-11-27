package org.kwicket.core.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.link.Link
import org.apache.wicket.markup.html.link.PopupSettings
import org.apache.wicket.markup.html.list.ListView
import org.apache.wicket.model.IModel

/**
 * Configuration for creating a [Link] component.
 *
 * @param T type of the model
 * @property onClick lambda to execute when the link is clicked
 */
interface ILinkConfig<T> : IAbstractLinkConfig<Link<T>, T> {
    var onClick: (Link<T>.() -> Unit)?
}

/**
 * Configuration for creating a [Link] component.
 *
 * @param T type of the model
 * @property onClick lambda to execute when the link is clicked
 * @param model model for the component
 * @param markupId optional unique id to use in the associated markup
 * @param outputMarkupId whether to include an HTML id for the component in the markup
 * @param outputMarkupPlaceholderTag whether to include a placeholder tag for the component in the markup when the
 * component is not visible
 * @param isVisible whether the component is initially visible
 * @param isEnabled whether the component is initially enabled
 * @param isVisibilityAllowed whether the component is allowed to be visible
 * @param escapeModelStrings whether model strings should be escaped
 * @param renderBodyOnly whether the tag associated with the component should be included in the markup
 * @param behavior optional [Behavior] to add to the component
 * @param behaviors optional List of [Behavior]s to add to the component
 * @param stateless whether to include a hint that the component is stateless
 * @param onConfig optional lambda to execute in the onConfigure lifecycle method
 * @param postInit optional lambda to execute after the component has been created
 */
open class LinkConfig<T>(
    model: IModel<T>? = null,
    override var onClick: (Link<T>.() -> Unit)? = null,
    popupSettings: PopupSettings? = null,
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
    stateless: Boolean? = null,
    onConfig: (Link<T>.() -> Unit)? = null,
    postInit: (Link<T>.() -> Unit)? = null
) : ILinkConfig<T>,
    AbstractLinkConfig<Link<T>, T>(
        model = model,
        popupSettings = popupSettings,
        markupId = markupId,
        outputMarkupId = outputMarkupId,
        outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
        isVisible = isVisible,
        isVisibilityAllowed = isVisibilityAllowed,
        isEnabled = isEnabled,
        escapeModelStrings = escapeModelStrings,
        renderBodyOnly = renderBodyOnly,
        behavior = behavior,
        behaviors = behaviors,
        stateless = stateless,
        onConfig = onConfig,
        postInit = postInit
    )