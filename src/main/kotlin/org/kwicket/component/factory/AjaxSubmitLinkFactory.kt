package org.kwicket.component.factory

import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink
import org.kwicket.component.config
import org.kwicket.component.config.IAjaxSubmitLinkConfig

fun <T> ajaxSubmitLinkFactory(
    id: String,
    config: IAjaxSubmitLinkConfig<T>
): AjaxSubmitLink {
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

        override fun getStatelessHint(): Boolean =
            stateless ?: super.getStatelessHint()

    }.config(config)
}