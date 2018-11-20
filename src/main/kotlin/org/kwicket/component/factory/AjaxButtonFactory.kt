package org.kwicket.component.factory

import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.markup.html.form.AjaxButton
import org.kwicket.component.config
import org.kwicket.component.config.IAjaxButtonConfig

/**
 * Creates an [AjaxButton] component with the Wicket identifier set to [id] and configured using [config].
 *
 * @param id Wicket component id
 * @param config specifies the settings for the [AjaxButton]
 * @return [AjaxButton] with the Wicket component id of [id] and configured by [config]
 */
fun ajaxButtonFactory(id: String, config: IAjaxButtonConfig): AjaxButton {
    val onConfig = config.onConfig
    val model = config.model
    val form = config.form
    val stateless = config.stateless
    val onSubmit = config.onSubmit
    val onError = config.onError
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

    }.config(config)
}