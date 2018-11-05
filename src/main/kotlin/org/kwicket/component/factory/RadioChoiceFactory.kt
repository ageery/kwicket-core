package org.kwicket.component.factory

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.IChoiceRenderer
import org.apache.wicket.markup.html.form.RadioChoice
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator
import org.kwicket.component.config
import org.kwicket.hasNonNull

fun <T> radioChoiceFactory(
    id: String,
    model: IModel<T>? = null,
    choices: IModel<List<T>>,
    choiceRenderer: IChoiceRenderer<T>? = null,
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
    onConfig: (RadioChoice<T>.() -> Unit)? = null,
    postInit: (RadioChoice<T>.() -> Unit)? = null
): RadioChoice<T> =
    if (hasNonNull(onConfig)) {
        object : RadioChoice<T>(id, model, choices, choiceRenderer) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }
        }
    } else {
        RadioChoice<T>(id, model, choices, choiceRenderer)
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