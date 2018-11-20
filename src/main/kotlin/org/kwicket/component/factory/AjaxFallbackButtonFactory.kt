package org.kwicket.component.factory

import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.markup.html.form.AjaxButton
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton
import org.kwicket.component.config
import org.kwicket.component.config.IAjaxFallbackButtonConfig
import java.util.Optional

/**
 * Creates an [AjaxFallbackButton] component with the Wicket identifier set to [id] and configured using [config].
 *
 * @param id Wicket component id
 * @param config specifies the settings for the [AjaxFallbackButton]
 * @return [AjaxFallbackButton] with the Wicket component id of [id] and configured by [config]
 */
fun ajaxFallbackButtonFactory(id: String, config: IAjaxFallbackButtonConfig): AjaxFallbackButton {
    val onConfig = config.onConfig
    val model = config.model
    val form = config.form
    val stateless = config.stateless
    val onSubmit = config.onSubmit
    val onError = config.onError
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

    }.config(config)
}