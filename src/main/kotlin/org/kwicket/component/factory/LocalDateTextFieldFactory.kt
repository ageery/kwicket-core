package org.kwicket.component.factory

import org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTextField
import org.apache.wicket.model.IModel
import org.kwicket.component.config
import org.kwicket.component.config.ILocalDateTextFieldConfig
import java.time.LocalDate

/**
 * Creates a [LocalDateTextField] component with the Wicket identifier set to [id] and configured using [config].

 * @param T type of the model of the [LocalDateTextField]
 * @param id Wicket component id
 * @param config specifies the settings for the [LocalDateTextField] component
 * @return [LocalDateTextField] with the Wicket component id of [id] and configured by [config]
 */
fun <T: LocalDate?> localDateTextFieldFactory(id: String, config: ILocalDateTextFieldConfig<T>): LocalDateTextField {
    @Suppress("UNCHECKED_CAST")
    val model = config.model as IModel<LocalDate?>
    return if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        if (config.dateStyle != null) {

            object : LocalDateTextField(id, model, config.dateStyle) {

                override fun onConfigure() {
                    super.onConfigure()
                    onConfig?.invoke(this)
                }

                override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

            }
        } else {
            object :
                LocalDateTextField(id, model, config.formatPattern, config.parsePattern) {

                override fun onConfigure() {
                    super.onConfigure()
                    onConfig?.invoke(this)
                }

                override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

            }
        }
    } else {
        if (config.dateStyle != null) {
            LocalDateTextField(id, model, config.dateStyle)
        } else {
            LocalDateTextField(id, model, config.formatPattern, config.parsePattern)
        }
    }.config(config)
}