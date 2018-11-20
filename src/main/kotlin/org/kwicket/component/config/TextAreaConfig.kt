package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.extensions.markup.html.form.select.Select
import org.apache.wicket.markup.html.form.CheckGroup
import org.apache.wicket.markup.html.form.RadioGroup
import org.apache.wicket.markup.html.form.TextArea
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator

interface ITextAreaConfig<C: Any, T: C?> : IFormComponentConfig<TextArea<C>, C, T>

class TextAreaConfig<C: Any, T: C?>(
    model: IModel<T>? = null,
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
    onConfig: (TextArea<C>.() -> Unit)? = null,
    postInit: (TextArea<C>.() -> Unit)? = null
) : ITextAreaConfig<C, T>,
    FormComponentConfig<TextArea<C>, C, T>(
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