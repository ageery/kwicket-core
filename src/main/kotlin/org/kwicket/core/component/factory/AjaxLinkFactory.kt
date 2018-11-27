package org.kwicket.core.component.factory

import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink
import org.apache.wicket.ajax.markup.html.AjaxLink
import org.apache.wicket.ajax.markup.html.form.AjaxButton
import org.kwicket.core.component.config
import org.kwicket.core.component.config.IAjaxLinkConfig

/**
 * Creates an [AjaxLink] component based on the configuration and with a Wicket identifier of [id].
 *
 * @param T type of the model of the [AjaxLink]
 * @param id Wicket component id to use for the [AjaxLink]
 * @receiver configuration for creating the [AjaxButton]
 * @return [AjaxLink] component based on the configuration and with a Wicket identifier of [id]
 */
fun <T> ajaxLinkFactory(id: String, config: IAjaxLinkConfig<T>): AjaxLink<T> {
    val onConfig = config.onConfig
    val model = config.model
    val stateless = config.stateless
    val onClick = config.onClick
    return object : AjaxLink<T>(id, model) {

        override fun onConfigure() {
            super.onConfigure()
            onConfig?.invoke(this)
        }

        override fun onClick(target: AjaxRequestTarget) {
            onClick?.invoke(this, target)
        }

        override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

    }.config(config)
}