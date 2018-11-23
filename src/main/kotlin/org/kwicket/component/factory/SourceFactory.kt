package org.kwicket.component.factory

import org.apache.wicket.extensions.markup.html.form.select.Select
import org.apache.wicket.markup.html.image.Source
import org.kwicket.component.config
import org.kwicket.component.config.ISourceConfig
import org.kwicket.component.config.requiresSubclass

/**
 * Creates a [Source] component based on the configuration and with a Wicket identifier of [id].
 *
 * @param T type of the model of the [Source]
 * @param id Wicket component id to use for the [Source]
 * @receiver configuration for creating the [Source]
 * @return [Source] component based on the configuration and with a Wicket identifier of [id]
 */
operator fun <T> ISourceConfig<T>.invoke(id: String): Source =
    if (requiresSubclass) {
        val onConfig = onConfig
        val stateless = stateless
        val model = model
        object : Source(id, model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }
    } else {
        Source(id, model)
    }.config(this)
        .also { source ->
            media?.let { source.media = it }
            crossOrigin?.let { source.crossOrigin = it }
        }