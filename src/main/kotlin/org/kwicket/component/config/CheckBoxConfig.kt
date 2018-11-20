package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.CheckBox
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator

interface ICheckBoxConfig : IFormComponentConfig<CheckBox, Boolean, Boolean>

class CheckBoxConfig(
    model: IModel<Boolean>? = null,
    label: IModel<String>? = null,
    isRequired: Boolean? = null,
    validator: IValidator<Boolean>? = null,
    validators: List<IValidator<Boolean>>? = null,
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
    onConfig: (CheckBox.() -> Unit)? = null,
    postInit: (CheckBox.() -> Unit)? = null
) : ICheckBoxConfig,
    FormComponentConfig<CheckBox, Boolean, Boolean>(
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