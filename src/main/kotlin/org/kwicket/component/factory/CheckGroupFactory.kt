package org.kwicket.component.factory

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.CheckGroup
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator
import org.kwicket.component.config

fun <T> checkGroupFactory(
    id: String,
    model: IModel<MutableCollection<T>>? = null,
    label: IModel<String>? = null,
    validator: IValidator<MutableCollection<T>>? = null,
    validators: List<IValidator<MutableCollection<T>>>? = null,
    markupId: String? = null,
    outputMarkupId: Boolean? = null,
    outputMarkupPlaceholderTag: Boolean? = null,
    visible: Boolean? = null,
    enabled: Boolean? = null,
    visibilityAllowed: Boolean? = null,
    escapeModelStrings: Boolean? = null,
    renderBodyOnly: Boolean? = null,
    behavior: Behavior? = null,
    behaviors: List<Behavior>? = null,
    onConfig: (CheckGroup<T>.() -> Unit)? = null,
    postInit: (CheckGroup<T>.() -> Unit)? = null
): CheckGroup<T> =
    if (onConfig != null) {
        object : CheckGroup<T>(id, model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig.invoke(this)
            }

        }
    } else {
        CheckGroup(id, model)
    }.config(
        markupId = markupId,
        outputMarkupId = outputMarkupId,
        outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
        visible = visible,
        enabled = enabled,
        visibilityAllowed = visibilityAllowed,
        escapeModelStrings = escapeModelStrings,
        renderBodyOnly = renderBodyOnly,
        behavior = behavior,
        behaviors = behaviors,
        label = label,
        validator = validator,
        validators = validators
    ).also {
        postInit?.invoke(it)
    }