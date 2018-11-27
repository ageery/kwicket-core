package org.kwicket.core.behavior

import org.apache.wicket.Component
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.model.IModel
import org.kwicket.core.model.ldm
import org.kwicket.core.model.obj

class VisibleWhen(private val isVisibleModel: IModel<Boolean>) : Behavior() {

    constructor(isVisible: () -> Boolean) : this(isVisible.ldm())

    override fun onConfigure(component: Component) {
        super.onConfigure(component)
        component.isVisible = isVisibleModel.obj
    }

    override fun detach(component: Component?) {
        super.detach(component)
        isVisibleModel.detach()
    }
}