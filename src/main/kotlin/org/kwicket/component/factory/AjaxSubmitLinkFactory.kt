package org.kwicket.component.factory

import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.markup.html.AjaxLink
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink
import org.kwicket.component.config
import org.kwicket.component.config.IAjaxSubmitLinkConfig

/**
 * Creates an [AjaxSubmitLink] component with the Wicket identifier set to [id] and configured using [config].
 *
 * @param T type of the model of the [AjaxSubmitLink]
 * @param id Wicket component id
 * @param config specifies the settings for the [AjaxSubmitLink]
 * @return [AjaxSubmitLink] with the Wicket component id of [id] and configured by [config]
 */
fun <T> ajaxSubmitLinkFactory(id: String, config: IAjaxSubmitLinkConfig<T>): AjaxSubmitLink {
    val onConfig = config.onConfig
    val form = config.form
    val stateless = config.stateless
    val onSubmit = config.onSubmit
    val onError = config.onError
    return object : AjaxSubmitLink(id, form) {

        override fun onConfigure() {
            super.onConfigure()
            onConfig?.invoke(this)
        }

        override fun onSubmit(target: AjaxRequestTarget) {
            super.onSubmit(target)
            onSubmit?.invoke(this, target)
        }

        override fun onError(target: AjaxRequestTarget) {
            super.onError(target)
            onError?.invoke(this, target)
        }

        override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

    }.config(config)
}