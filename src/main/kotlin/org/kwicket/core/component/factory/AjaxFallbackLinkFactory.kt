package org.kwicket.core.component.factory

import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink
import org.apache.wicket.ajax.markup.html.form.AjaxButton
import org.kwicket.core.component.config
import org.kwicket.core.component.config.IAjaxFallbackLinkConfig
import java.util.Optional

/**
 * Creates an [AjaxButton] component based on the configuration and with a Wicket identifier of [id].
 *
 * @param T type of the model of the [AjaxFallbackLink]
 * @param id Wicket component id to use for the [AjaxFallbackLink]
 * @receiver configuration for creating the [AjaxFallbackLink]
 * @return [AjaxFallbackLink] component based on the configuration and with a Wicket identifier of [id]
 */
operator fun <T> IAjaxFallbackLinkConfig<T>.invoke(id: String): AjaxFallbackLink<T> {
    val onConfig = onConfig
    val model = model
    val stateless = stateless
    val onClick = onClick
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

    }.config(this)
}