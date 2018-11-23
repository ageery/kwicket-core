package org.kwicket.component.config

import org.apache.wicket.Component
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.model.IModel
import java.io.Serializable

internal val IComponentConfig<*, *>.requiresSubclass: Boolean
    get() = onConfig != null || stateless != null

interface IComponentConfig<C : Component, T> {
    val model: IModel<T>?
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
//    val requiresSubclass: Boolean
//        get() = onConfig != null || stateless != null
//    val isValid: Boolean
//        get() = true
}

open class ComponentConfig<C : Component, T>(
    override val model: IModel<T>? = null,
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