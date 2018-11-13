package org.kwicket.component.factory

import org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTextField
import org.kwicket.component.config
import org.kwicket.component.config.ILocalDateTextFieldConfig
import org.kwicket.component.config.useAnonSubClass

// FIXME: could we get rid of the Factory at the end?
fun localDateTextFieldFactory(
    id: String,
    config: ILocalDateTextFieldConfig
): LocalDateTextField =
    if (config.useAnonSubClass()) {
        if (config.dateStyle != null) {
            object : LocalDateTextField(id, config.model, config.dateStyle) {
                override fun onConfigure() {
                    super.onConfigure()
                    config.onConfig?.invoke(this)
                }
            }
        } else {
            object : LocalDateTextField(id, config.model, config.formatPattern, config.parsePattern) {
                override fun onConfigure() {
                    super.onConfigure()
                    config.onConfig?.invoke(this)
                }
            }
        }
    } else {
        if (config.dateStyle != null) {
            LocalDateTextField(id, config.model, config.dateStyle)
        } else {
            LocalDateTextField(id, config.model, config.formatPattern, config.parsePattern)
        }
    }.config(config).apply { config.postInit?.invoke(this) }