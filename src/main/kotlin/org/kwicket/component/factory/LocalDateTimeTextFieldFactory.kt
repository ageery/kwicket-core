package org.kwicket.component.factory

import org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTimeTextField
import org.apache.wicket.model.IModel
import org.kwicket.component.config
import org.kwicket.component.config.ILocalDateTimeTextFieldConfig
import java.time.LocalDateTime

fun <T: LocalDateTime?>localDateTimeTextFieldFactory(
    id: String,
    config: ILocalDateTimeTextFieldConfig<T>
): LocalDateTimeTextField {
    @Suppress("UNCHECKED_CAST")
    val model = config.model as IModel<LocalDateTime?>
    return if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        if (config.dateTimePattern != null) {

            object : LocalDateTimeTextField(id, model, config.dateTimePattern) {

                override fun onConfigure() {
                    super.onConfigure()
                    onConfig?.invoke(this)
                }

                override fun getStatelessHint(): Boolean =
                    stateless ?: super.getStatelessHint()

            }
        } else {
            @Suppress("UNCHECKED_CAST")
            object :
                LocalDateTimeTextField(id, model, config.dateStyle, config.timeStyle) {

                override fun onConfigure() {
                    super.onConfigure()
                    onConfig?.invoke(this)
                }

                override fun getStatelessHint(): Boolean =
                    stateless ?: super.getStatelessHint()

            }
        }
    } else {
        if (config.dateTimePattern != null) {
            LocalDateTimeTextField(id, model, config.dateTimePattern)
        } else {
            @Suppress("UNCHECKED_CAST")
            LocalDateTimeTextField(id, model, config.dateStyle, config.timeStyle)
        }
    }.config(config)
}