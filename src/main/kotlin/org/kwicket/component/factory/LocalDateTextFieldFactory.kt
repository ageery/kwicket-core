package org.kwicket.component.factory

import org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTextField
import org.apache.wicket.model.IModel
import org.kwicket.component.config
import org.kwicket.component.config.ILocalDateTextFieldConfig
import org.kwicket.component.config.requiresSubclass
import java.time.LocalDate

/**
 * Creates a [LocalDateTextField] component based on the configuration and with a Wicket identifier of [id].
 *
 * @param T type of the model of the [LocalDateTextField]
 * @param id Wicket component id to use for the [LocalDateTextField]
 * @receiver configuration for creating the [LocalDateTextField]
 * @return [LocalDateTextField] component based on the configuration and with a Wicket identifier of [id]
 */
operator fun <T: LocalDate?> ILocalDateTextFieldConfig<T>.invoke(id: String): LocalDateTextField {
    @Suppress("UNCHECKED_CAST")
    val model = model as IModel<LocalDate?>
    return if (requiresSubclass) {
        val onConfig = onConfig
        val stateless = stateless
        if (dateStyle != null) {
            val dateStyle = dateStyle
            object : LocalDateTextField(id, model, dateStyle) {

                override fun onConfigure() {
                    super.onConfigure()
                    onConfig?.invoke(this)
                }

                override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

            }
        } else {
            val formatPattern = formatPattern
            val parsePattern = parsePattern
            object :
                LocalDateTextField(id, model, formatPattern, parsePattern) {

                override fun onConfigure() {
                    super.onConfigure()
                    onConfig?.invoke(this)
                }

                override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

            }
        }
    } else {
        if (dateStyle != null) {
            LocalDateTextField(id, model, dateStyle)
        } else {
            LocalDateTextField(id, model, formatPattern, parsePattern)
        }
    }.config(this)
}