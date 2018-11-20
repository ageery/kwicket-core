package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.IChoiceRenderer
import org.apache.wicket.markup.html.form.RadioChoice
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator

interface IRadioChoiceConfig<C: Any, T: C?> : IFormComponentConfig<RadioChoice<C>, C, T> {
    var choices: IModel<List<C>>?
    var choiceRenderer: IChoiceRenderer<C>?
}

class RadioChoiceConfig<C: Any, T: C?>(
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
    onConfig: (RadioChoice<C>.() -> Unit)? = null,
    postInit: (RadioChoice<C>.() -> Unit)? = null
) : IRadioChoiceConfig<C, T>,
    FormComponentConfig<RadioChoice<C>, C, T>(
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