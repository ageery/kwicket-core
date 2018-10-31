package org.kwicket.component.factory

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.TextField
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator
import org.kwicket.component.config
import org.kwicket.hasNonNull
import org.kwicket.toJavaType
import kotlin.reflect.KClass

fun <T: Any> textFieldFactory(
    id: String,
    model: IModel<T?>? = null,
    type: KClass<T>? = null,
    label: IModel<String>? = null,
    isRequired: Boolean? = null,
    validator: IValidator<T>? = null,
    validators: List<IValidator<T>>? = null,
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
    onConfig: (TextField<T>.() -> Unit)? = null,
    postInit: (TextField<T>.() -> Unit)? = null
): TextField<T> =
    if (hasNonNull(onConfig)) {
        object : TextField<T>(id, model, type.toJavaType(isRequired = isRequired)) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }
        }
    } else {
        TextField<T>(id, model, type.toJavaType(isRequired = isRequired))
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
        isRequired = isRequired,
        label = label,
        validator = validator,
        validators = validators
    ).also { postInit?.invoke(it) }