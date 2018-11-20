package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.DropDownChoice
import org.apache.wicket.markup.html.form.IChoiceRenderer
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator

interface IDropDownChoiceConfig<C: Any, T: C?> : IFormComponentConfig<DropDownChoice<C>, C, T> {
    var choices: IModel<List<C>>?
    var choiceRenderer: IChoiceRenderer<C>?
}

class DropDownChoiceConfig<C: Any, T: C?>(
    model: IModel<T>? = null,
    override var choices: IModel<List<C>>? = null,
    override var choiceRenderer: IChoiceRenderer<C>? = null,
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
    onConfig: (DropDownChoice<C>.() -> Unit)? = null,
    postInit: (DropDownChoice<C>.() -> Unit)? = null
) : IDropDownChoiceConfig<C, T>,
    FormComponentConfig<DropDownChoice<C>, C, T>(
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