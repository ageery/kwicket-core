package org.kwicket.component.factory

import org.apache.wicket.extensions.markup.html.form.datetime.ZonedDateTimeField
import org.apache.wicket.model.IModel
import org.kwicket.component.config
import org.kwicket.component.config.IIZonedDateTimeFieldConfig
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZonedDateTime

/**
 * Creates a [ZonedDateTimeField] component with the Wicket identifier set to [id] and configured using [config].

 * @param T type of the model of the [ZonedDateTimeField]
 * @param id Wicket component id
 * @param config specifies the settings for the [ZonedDateTimeField] component
 * @return [ZonedDateTimeField] with the Wicket component id of [id] and configured by [config]
 */
fun <T : ZonedDateTime?> zonedDateTimeFieldFactory(
    id: String,
    config: IIZonedDateTimeFieldConfig<T>
): ZonedDateTimeField {
    @Suppress("UNCHECKED_CAST")
    val model = config.model as IModel<ZonedDateTime?>
    return if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        val toZonedDate = config.toZonedDate
        val toZonedTime = config.toZonedTime
        val defaultTime = config.defaultTime
        object : ZonedDateTimeField(id, model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

            override fun getLocalDate(temporal: ZonedDateTime): LocalDate =
                toZonedDate?.invoke(temporal) ?: super.getLocalDate(temporal)

            override fun getLocalTime(temporal: ZonedDateTime): LocalTime =
                toZonedTime?.invoke(temporal) ?: super.getLocalTime(temporal)

            override fun getDefaultTime(): LocalTime? = defaultTime?.invoke() ?: super.getDefaultTime()

        }
    } else {
        ZonedDateTimeField(id, model)
    }.config(config)
}