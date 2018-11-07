package org.kwicket.behavior

import org.apache.wicket.Component
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.model.IModel
import org.kwicket.model.ldm
import org.kwicket.model.obj

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