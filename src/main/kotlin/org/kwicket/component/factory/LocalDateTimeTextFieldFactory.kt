package org.kwicket.component.factory

import org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTimeTextField
import org.apache.wicket.model.IModel
import org.kwicket.component.config
import org.kwicket.component.config.ILocalDateTimeTextFieldConfig
import org.kwicket.component.config.requiresSubclass
import java.time.LocalDateTime

/**
 * Creates a [LocalDateTimeTextField] component based on the configuration and with a Wicket identifier of [id].
 *
 * @param T type of the model of the [LocalDateTimeTextField]
 * @param id Wicket component id to use for the [LocalDateTimeTextField]
 * @receiver configuration for creating the [LocalDateTimeTextField]
 * @return [LocalDateTimeTextField] component based on the configuration and with a Wicket identifier of [id]
 */
operator fun <T : LocalDateTime?> ILocalDateTimeTextFieldConfig<T>.invoke(id: String): LocalDateTimeTextField {
    @Suppress("UNCHECKED_CAST")
    val model = model as IModel<LocalDateTime?>
    return if (requiresSubclass) {
        val onConfig = onConfig
        val stateless = stateless
        if (dateTimePattern != null) {
            val dateTimePattern = dateTimePattern
            object : LocalDateTimeTextField(id, model, dateTimePattern) {

                override fun onConfigure() {
                    super.onConfigure()
                    onConfig?.invoke(this)
                }

                override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

            }
        } else {
            val dateStyle = dateStyle
            val timeStyle = timeStyle
            object : LocalDateTimeTextField(id, model, dateStyle, timeStyle) {

                override fun onConfigure() {
                    super.onConfigure()
                    onConfig?.invoke(this)
                }

                override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

            }
        }
    } else {
        if (dateTimePattern != null) {
            LocalDateTimeTextField(id, model, dateTimePattern)
        } else {
            LocalDateTimeTextField(id, model, dateStyle, timeStyle)
        }
    }.config(this)
}