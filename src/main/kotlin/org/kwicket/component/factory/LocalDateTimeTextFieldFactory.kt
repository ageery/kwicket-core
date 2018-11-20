package org.kwicket.component.factory

import org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTimeTextField
import org.apache.wicket.model.IModel
import org.kwicket.component.config
import org.kwicket.component.config.ILocalDateTimeTextFieldConfig
import java.time.LocalDateTime

/**
 * Creates a [LocalDateTimeTextField] component with the Wicket identifier set to [id] and configured using [config].

 * @param T type of the model of the [LocalDateTimeTextField]
 * @param id Wicket component id
 * @param config specifies the settings for the [LocalDateTimeTextField] component
 * @return [LocalDateTimeTextField] with the Wicket component id of [id] and configured by [config]
 */
fun <T : LocalDateTime?> localDateTimeTextFieldFactory(
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

                override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

            }
        } else {
            object : LocalDateTimeTextField(id, model, config.dateStyle, config.timeStyle) {

                override fun onConfigure() {
                    super.onConfigure()
                    onConfig?.invoke(this)
                }

                override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

            }
        }
    } else {
        if (config.dateTimePattern != null) {
            LocalDateTimeTextField(id, model, config.dateTimePattern)
        } else {
            LocalDateTimeTextField(id, model, config.dateStyle, config.timeStyle)
        }
    }.config(config)
}