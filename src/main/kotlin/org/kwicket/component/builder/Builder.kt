package org.kwicket.component.builder

import org.apache.wicket.Component
import org.kwicket.component.config.IComponentConfig

// FIXME: I would like a builder to be a config object + the build method

// FIXME: what is a good name for this interface? -- it should be component builder -- maybe make the other one abstractcomponentbuilder -- base builder?
interface Builder<T: Component> {
    fun build(id: String): T
}

interface Builder2<M, T: Component, C: IComponentConfig<T, M>> {
    val config: C
    fun build(id: String): T
}