package org.kwicket.component.factory

import org.apache.wicket.extensions.markup.html.form.datetime.ZonedDateTimeField
import org.apache.wicket.model.IModel
import org.kwicket.component.config
import org.kwicket.component.config.IIZonedDateTimeFieldConfig
import org.kwicket.component.config.requiresSubclass
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
operator fun <T : ZonedDateTime?> IIZonedDateTimeFieldConfig<T>.invoke(id: String): ZonedDateTimeField {
    @Suppress("UNCHECKED_CAST")
    val model = model as IModel<ZonedDateTime?>
    return if (requiresSubclass) {
        val onConfig = onConfig
        val stateless = stateless
        val toZonedDate = toZonedDate
        val toZonedTime = toZonedTime
        val defaultTime = defaultTime
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
    }.config(this)
}