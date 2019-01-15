package org.kwicket.core.behavior

import org.apache.wicket.Component
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.model.IModel
import org.kwicket.core.model.ldm
import org.kwicket.core.model.obj

class EnabledWhen(private val isEnabledModel: IModel<Boolean>) : Behavior() {

    constructor(isEnabled: () -> Boolean) : this(isEnabled.ldm())

    override fun onConfigure(component: Component) {
        super.onConfigure(component)
        component.isEnabled = isEnabledModel.obj
    }

    override fun detach(component: Component?) {
        super.detach(component)
        isEnabledModel.detach()
    }
}