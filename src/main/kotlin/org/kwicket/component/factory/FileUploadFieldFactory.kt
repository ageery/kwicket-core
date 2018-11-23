package org.kwicket.component.factory

import org.apache.wicket.markup.html.form.upload.FileUploadField
import org.kwicket.component.config
import org.kwicket.component.config.FileUploadFieldConfig
import org.kwicket.component.config.IFileUploadFieldConfig
import org.kwicket.component.config.requiresSubclass

/**
 * Creates an [FileUploadField] component with the Wicket identifier set to [id] and configured using [config].

 * @param id Wicket component id
 * @param config specifies the settings for the [FileUploadField] component -- defaults to an empty config
 * @return [FileUploadField] with the Wicket component id of [id] and configured by [config]
 */
// FIXME: change this method!
fun fileUploadFieldFactory(id: String, config: IFileUploadFieldConfig = FileUploadFieldConfig()): FileUploadField =
    if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        object : FileUploadField(id, config.model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }
    } else {
        FileUploadField(id, config.model)
    }.config(config)