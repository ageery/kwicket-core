package org.kwicket.component.factory

import org.apache.wicket.Component
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.model.IModel
import org.kwicket.component.AbstractPanelTest
import org.kwicket.component.q

abstract class AbstractFactoryTest<C : Component, M> : AbstractPanelTest<C, M>() {

    override fun Panel.queueComponent(comp: C) {
        q(comp)
    }

    override fun Panel.createComponent(
        id: String,
        model: IModel<M>?,
        markupId: String?,
        outputMarkupId: Boolean?,
        outputMarkupPlaceholderTag: Boolean?,
        visible: Boolean?,
        visibilityAllowed: Boolean?,
        enabled: Boolean?,
        escapeModelStrings: Boolean?,
        renderBodyOnly: Boolean?,
        behavior: Behavior?,
        behaviors: List<Behavior>?,
        onConfig: (C.() -> Unit)?,
        postInit: (C.() -> Unit)?
    ) =
        factoryCreate(
            id = id,
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
            onConfig = onConfig,
            postInit = postInit
        )

    abstract fun factoryCreate(
        id: String,
        model: IModel<M>? = null,
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
        onConfig: (C.() -> Unit)? = null,
        postInit: (C.() -> Unit)? = null
    ): C

}