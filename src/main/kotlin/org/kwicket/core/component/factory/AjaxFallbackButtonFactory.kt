package org.kwicket.core.component.factory

import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.markup.html.form.AjaxButton
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton
import org.kwicket.core.component.config
import org.kwicket.core.component.config.IAjaxFallbackButtonConfig
import java.util.Optional

/**
 * Creates an [AjaxFallbackButton] component based on the configuration and with a Wicket identifier of [id].
 *
 * @param id Wicket component id to use for the [AjaxFallbackButton]
 * @receiver configuration for creating the [AjaxFallbackButton]
 * @return [AjaxFallbackButton] component based on the configuration and with a Wicket identifier of [id]
 */
operator fun IAjaxFallbackButtonConfig.invoke(id: String): AjaxFallbackButton {
    val onConfig = this.onConfig
    val model = this.model
    val form = this.form
    val stateless = this.stateless
    val onSubmit = this.onSubmit
    val onError = this.onError
    return object : AjaxFallbackButton(id, model, form) {

        override fun onConfigure() {
            super.onConfigure()
            onConfig?.invoke(this)
        }

        override fun getStatelessHint(): Boolean =
            stateless ?: super.getStatelessHint()

        override fun onSubmit(target: Optional<AjaxRequestTarget>) {
            super.onSubmit()
            onSubmit?.invoke(this, target.orElse(null))
        }

        override fun onError(target: Optional<AjaxRequestTarget>) {
            super.onError()
            onError?.invoke(this, target.orElse(null))
        }

    }.config(this)
}