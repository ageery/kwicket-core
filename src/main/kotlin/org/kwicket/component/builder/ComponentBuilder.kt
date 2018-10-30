package org.kwicket.component.builder

import org.apache.wicket.Component
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.model.IModel

interface IComponentBuilder<C : Component, T> : Builder<C> {
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
    var onConfig: (C.() -> Unit)?
    var postInit: (C.() -> Unit)?
}

abstract class ComponentBuilder<C : Component, T>(
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
    override var onConfig: (C.() -> Unit)? = null,
    override var postInit: (C.() -> Unit)? = null
) : IComponentBuilder<C, T>