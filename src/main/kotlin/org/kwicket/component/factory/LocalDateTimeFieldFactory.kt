package org.kwicket.component.factory

import org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTimeField
import org.apache.wicket.model.IModel
import org.kwicket.component.config
import org.kwicket.component.config.IILocalDateTimeFieldConfig
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

fun <T : LocalDateTime?> localDateTimeFieldFactory(
    id: String,
    config: IILocalDateTimeFieldConfig<T>
): LocalDateTimeField {
    @Suppress("UNCHECKED_CAST")
    val model = config.model as IModel<LocalDateTime?>
    return if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        val toLocalDate = config.toLocalDate
        val toLocalTime = config.toLocalTime
        val defaultTime = config.defaultTime
        object : LocalDateTimeField(id, model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean =
                stateless ?: super.getStatelessHint()

            override fun getLocalDate(temporal: LocalDateTime): LocalDate =
                toLocalDate?.invoke(temporal) ?: super.getLocalDate(temporal)

            override fun getLocalTime(temporal: LocalDateTime): LocalTime =
                toLocalTime?.invoke(temporal) ?: super.getLocalTime(temporal)

            override fun getDefaultTime(): LocalTime? =
                defaultTime?.invoke() ?: super.getDefaultTime()

        }
    } else {
        LocalDateTimeField(id, model)
    }.config(config)
}