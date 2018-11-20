package org.kwicket.component.queued

import org.apache.wicket.Component
import org.apache.wicket.MarkupContainer
import org.kwicket.component.config.IComponentConfig
import org.kwicket.component.q

fun <C: Component, T, I: IComponentConfig<C, T>> MarkupContainer.q(
    id: String,
    config: I,
    block: (I.() -> Unit)? = null,
    factory: (String, I) -> C
) = q(factory.invoke(id, config.apply { block?.invoke(this) }))