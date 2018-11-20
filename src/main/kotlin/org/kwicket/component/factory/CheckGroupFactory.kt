package org.kwicket.component.factory

import org.apache.wicket.markup.html.form.CheckGroup
import org.kwicket.component.config
import org.kwicket.component.config.ICheckGroupConfig

// FIXME: the problem is that checkGroup should extend FormComponentFactory but it doesn't...
// FIXME: probably better to fix it at the source than here...

fun <T, L: Collection<T>> checkGroupFactory(
    id: String,
    config: ICheckGroupConfig<T, L>
): CheckGroup<T> {
    val model = config.model
    val onConfig = config.onConfig
    val stateless = config.stateless
    return if (config.requiresSubclass) {
        object : CheckGroup<T>(id, model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean =
                stateless ?: super.getStatelessHint()

        }
    } else {
        CheckGroup(id, model)
    }.config(config)
        .also { checkGroup ->
            // FIXME: this should not be necessary...
            config.isRequired?.let { checkGroup.isRequired = it }
            config.label?.let { checkGroup.label = it }
            // FIXME: not sure what the problem is here....
//        val x = when {
//            config.validator != null -> listOf(config.validator) + (config.validators ?: emptyList())
//            config.validators != null -> config.validators
//            else -> emptyList()
//        }!!
//        x.also { validators ->
//            if (validators.isNotEmpty()) checkGroup.add(*validators.toTypedArray() as Array<IValidator<T>>)
//        }
        }
}