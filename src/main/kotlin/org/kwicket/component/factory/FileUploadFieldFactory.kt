package org.kwicket.component.factory

import org.apache.wicket.markup.html.form.upload.FileUploadField
import org.kwicket.component.config
import org.kwicket.component.config.FileUploadFieldConfig
import org.kwicket.component.config.IFileUploadFieldConfig

fun fileUploadFieldFactory(
    id: String,
    config: IFileUploadFieldConfig = FileUploadFieldConfig()
): FileUploadField {
    val model = config.model
    return if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        object : FileUploadField(id, model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean =
                stateless ?: super.getStatelessHint()

        }
    } else {
        FileUploadField(id, model)
    }.config(config)
}