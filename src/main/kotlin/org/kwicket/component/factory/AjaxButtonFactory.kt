package org.kwicket.component.factory

import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.markup.html.form.AjaxButton
import org.kwicket.component.config
import org.kwicket.component.config.IAjaxButtonConfig

/**
 * Creates an [AjaxButton] component based on the configuration and with a Wicket identifier of [id].
 *
 * @param id Wicket component id to use for the [AjaxButton]
 * @receiver configuration for creating the [AjaxButton]
 * @return [AjaxButton] component based on the configuration and with a Wicket identifier of [id]
 */

// FIXME: move the sub-class decision function into _this_ function

operator fun IAjaxButtonConfig.invoke(id: String): AjaxButton {
    val onConfig = this.onConfig
    val model = this.model
    val form = this.form
    val stateless = this.stateless
    val onSubmit = this.onSubmit
    val onError = this.onError
    return object : AjaxButton(id, model, form) {

        override fun onConfigure() {
            super.onConfigure()
            onConfig?.invoke(this)
        }

        override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        override fun onSubmit(target: AjaxRequestTarget) {
            super.onSubmit()
            onSubmit?.invoke(this, target)
        }

        override fun onError(target: AjaxRequestTarget) {
            super.onError()
            onError?.invoke(this, target)
        }

    }.config(this)
}