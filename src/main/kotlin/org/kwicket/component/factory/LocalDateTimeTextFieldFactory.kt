package org.kwicket.component.factory

import org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTimeTextField
import org.kwicket.component.config
import org.kwicket.component.config.ILocalDateTimeTextFieldConfig
import org.kwicket.component.config.useAnonSubClass

fun localDateTimeTextFieldFactory(
    id: String,
    config: ILocalDateTimeTextFieldConfig
): LocalDateTimeTextField =
    if (config.useAnonSubClass()) {
        if (config.dateTimePattern != null) {
            object : LocalDateTimeTextField(id, config.model, config.dateTimePattern) {
                override fun onConfigure() {
                    super.onConfigure()
                    config.onConfig?.invoke(this)
                }
            }
        } else {
            object : LocalDateTimeTextField(id, config.model, config.dateStyle, config.timeStyle) {
                override fun onConfigure() {
                    super.onConfigure()
                    config.onConfig?.invoke(this)
                }
            }
        }
    } else {
        if (config.dateTimePattern != null) {
            LocalDateTimeTextField(id, config.model, config.dateTimePattern)
        } else {
            LocalDateTimeTextField(id, config.model, config.dateStyle, config.timeStyle)
        }
    }.config(config).apply { config.postInit?.invoke(this) }