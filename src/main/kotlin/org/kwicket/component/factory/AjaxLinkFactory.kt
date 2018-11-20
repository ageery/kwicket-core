package org.kwicket.component.factory

import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.markup.html.AjaxLink
import org.kwicket.component.config
import org.kwicket.component.config.IAjaxLinkConfig

fun <T> ajaxLinkFactory(
    id: String,
    config: IAjaxLinkConfig<T>
): AjaxLink<T> {
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

        override fun getStatelessHint(): Boolean =
            stateless ?: super.getStatelessHint()

    }.config(config)
}