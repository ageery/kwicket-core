package org.kwicket.behavior

import org.apache.wicket.Component
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.model.IModel
import org.kwicket.model.ldm
import org.kwicket.model.obj

class EnabledWhen(private val isEnabledModel: IModel<Boolean>) : Behavior() {

    constructor(isEnabled: () -> Boolean) : this(isEnabled.ldm())

    override fun onConfigure(component: Component) {
        super.onConfigure(component)
        component.isVisible = isEnabledModel.obj
    }

    override fun detach(component: Component?) {
        super.detach(component)
        isEnabledModel.detach()
    }
}