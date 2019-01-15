package org.kwicket.core.component.config

import org.apache.wicket.Component
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.model.IModel

/**
 * Whether the [IComponentConfig] requires a subclass to create using standard Wicket components
 */
val IComponentConfig<*, *>.requiresSubclass: Boolean
    get() = onConfig != null || stateless != null

/**
 * Configuration for creating a Wicket [Component].
 *
 * @param C type of Wicket component
 * @param T type of the component model
 * @property model model for the component
 * @property markupId optional unique id to use in the associated markup
 * @property outputMarkupId whether to include an HTML id for the component in the markup
 * @property outputMarkupPlaceholderTag whether to include a placeholder tag for the component in the markup when the
 * component is not visible
 * @property isVisible whether the component is initially visible
 * @property isEnabled whether the component is initially enabled
 * @property isVisibilityAllowed whether the component is allowed to be visible
 * @property escapeModelStrings whether model strings should be escaped
 * @property renderBodyOnly whether the tag associated with the component should be included in the markup
 * @property behavior optional [Behavior] to add to the component
 * @property behaviors optional List of [Behavior]s to add to the component
 * @property stateless whether to include a hint that the component is stateless
 * @property onConfig optional lambda to execute in the onConfigure lifecycle method
 * @property postInit optional lambda to execute after the component has been created
 */
interface IComponentConfig<C : Component, T> {
    var model: IModel<T>?
    var markupId: String?
    var outputMarkupId: Boolean?
    var outputMarkupPlaceholderTag: Boolean?
    var isVisible: Boolean?
    var isVisibilityAllowed: Boolean?
    var isEnabled: Boolean?
    var escapeModelStrings: Boolean?
    var renderBodyOnly: Boolean?
    var behavior: Behavior?
    var behaviors: List<Behavior>?
    var stateless: Boolean?
    var onConfig: (C.() -> Unit)?
    var postInit: (C.() -> Unit)?
}

/**
 * Configuration for creating a Wicket [Component].
 *
 * @property model model for the component
 * @property markupId optional unique id to use in the associated markup
 * @property outputMarkupId whether to include an HTML id for the component in the markup
 * @property outputMarkupPlaceholderTag whether to include a placeholder tag for the component in the markup when the
 * component is not isVisible
 * @property isVisible whether the component is initially visible
 * @property isEnabled whether the component is initially enabled
 * @property isVisibilityAllowed whether the component is allowed to be visible
 * @property escapeModelStrings whether model strings should be escaped
 * @property renderBodyOnly whether the tag associated with the component should be included in the markup
 * @property behavior optional [Behavior] to add to the component
 * @property behaviors optional List of [Behavior]s to add to the component
 * @property stateless whether to include a hint that the component is stateless
 * @property onConfig optional lambda to execute in the onConfigure lifecycle method
 * @property postInit optional lambda to execute after the component has been created
 */
abstract class ComponentConfig<C : Component, T>(
    override var model: IModel<T>? = null,
    override var markupId: String? = null,
    override var outputMarkupId: Boolean? = null,
    override var outputMarkupPlaceholderTag: Boolean? = null,
    override var isVisible: Boolean? = null,
    override var isVisibilityAllowed: Boolean? = null,
    override var isEnabled: Boolean? = null,
    override var escapeModelStrings: Boolean? = null,
    override var renderBodyOnly: Boolean? = null,
    override var behavior: Behavior? = null,
    override var behaviors: List<Behavior>? = null,
    override var stateless: Boolean? = null,
    override var onConfig: (C.() -> Unit)? = null,
    override var postInit: (C.() -> Unit)? = null
) : IComponentConfig<C, T>