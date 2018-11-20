package org.kwicket.component.factory

import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink
import org.kwicket.component.config
import org.kwicket.component.config.IAjaxFallbackLinkConfig
import java.util.Optional

/**
 * Creates an [AjaxFallbackLink] component with the Wicket identifier set to [id] and configured using [config].
 *
 * @param T type of the model of the [AjaxFallbackLink]
 * @param id Wicket component id
 * @param config specifies the settings for the [AjaxFallbackLink]
 * @return [AjaxFallbackLink] with the Wicket component id of [id] and configured by [config]
 */
fun <T> ajaxFallbackLinkFactory(id: String, config: IAjaxFallbackLinkConfig<T>): AjaxFallbackLink<T> {
    val onConfig = config.onConfig
    val model = config.model
    val stateless = config.stateless
    val onClick = config.onClick
    return object : AjaxFallbackLink<T>(id, model) {

        override fun onConfigure() {
            super.onConfigure()
            onConfig?.invoke(this)
        }

        override fun onClick(target: Optional<AjaxRequestTarget>) {
            onClick?.invoke(this, target.orElse(null))
        }

        override fun getStatelessHint(): Boolean =
            stateless ?: super.getStatelessHint()

    }.config(config)
}