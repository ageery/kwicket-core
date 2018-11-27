package org.kwicket.core.component.factory

import org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTimeField
import org.apache.wicket.model.IModel
import org.kwicket.core.component.config
import org.kwicket.core.component.config.IILocalDateTimeFieldConfig
import org.kwicket.core.component.config.requiresSubclass
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

/**
 * Creates a [LocalDateTimeField] component based on the configuration and with a Wicket identifier of [id].
 *
 * @param T type of the model of the [LocalDateTimeField]
 * @param id Wicket component id to use for the [LocalDateTimeField]
 * @receiver configuration for creating the [LocalDateTimeField]
 * @return [LocalDateTimeField] component based on the configuration and with a Wicket identifier of [id]
 */
operator fun <T : LocalDateTime?> IILocalDateTimeFieldConfig<T>.invoke(id: String): LocalDateTimeField {
    @Suppress("UNCHECKED_CAST")
    val model = model as IModel<LocalDateTime?>
    return if (requiresSubclass) {
        val onConfig = onConfig
        val stateless = stateless
        val toLocalDate = toLocalDate
        val toLocalTime = toLocalTime
        val defaultTime = defaultTime
        object : LocalDateTimeField(id, model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

            override fun getLocalDate(temporal: LocalDateTime): LocalDate =
                toLocalDate?.invoke(temporal) ?: super.getLocalDate(temporal)

            override fun getLocalTime(temporal: LocalDateTime): LocalTime =
                toLocalTime?.invoke(temporal) ?: super.getLocalTime(temporal)

            override fun getDefaultTime(): LocalTime? = defaultTime?.invoke() ?: super.getDefaultTime()

        }
    } else {
        LocalDateTimeField(id, model)
    }.config(this)
}