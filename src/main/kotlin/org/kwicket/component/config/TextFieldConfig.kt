package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.TextField
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator
import kotlin.reflect.KClass

interface ITextFieldConfig<C : Any, T : C?> : IFormComponentConfig<TextField<C>, C, T> {
    var type: KClass<C>?
}

class TextFieldConfig<C : Any, T : C?>(
    model: IModel<T>? = null,
    override var type: KClass<C>? = null,
    label: IModel<String>? = null,
    isRequired: Boolean? = null,
    validator: IValidator<C>? = null,
    validators: List<IValidator<C>>? = null,
    markupId: String? = null,
    outputMarkupId: Boolean? = null,
    outputMarkupPlaceholderTag: Boolean? = null,
    isVisible: Boolean? = null,
    isVisibilityAllowed: Boolean? = null,
    isEnabled: Boolean? = null,
    escapeModelStrings: Boolean? = null,
    renderBodyOnly: Boolean? = null,
    behavior: Behavior? = null,
    behaviors: List<Behavior>? = null,
    stateless: Boolean? = null,
    onConfig: (TextField<C>.() -> Unit)? = null,
    postInit: (TextField<C>.() -> Unit)? = null
) : ITextFieldConfig<C, T>,
    FormComponentConfig<TextField<C>, C, T>(
        model = model,
        label = label,
        isRequired = isRequired,
        validator = validator,
        validators = validators,
        markupId = markupId,
        outputMarkupId = outputMarkupId,
        outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
        isVisible = isVisible,
        isVisibilityAllowed = isVisibilityAllowed,
        isEnabled = isEnabled,
        escapeModelStrings = escapeModelStrings,
        renderBodyOnly = renderBodyOnly,
        behavior = behavior,
        behaviors = behaviors,
        stateless = stateless,
        onConfig = onConfig,
        postInit = postInit
    )