package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.Check
import org.apache.wicket.markup.html.form.CheckGroup
import org.apache.wicket.markup.html.form.Radio
import org.apache.wicket.markup.html.form.RadioGroup
import org.apache.wicket.model.IModel

interface IRadioConfig<T> : IComponentConfig<Radio<T>, T> {
    var label: IModel<String>?
    var group: RadioGroup<T>?
}

class RadioConfig<T>(
    model: IModel<T>? = null,
    override var group: RadioGroup<T>? = null,
    override var label: IModel<String>? = null,
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
    onConfig: (Radio<T>.() -> Unit)? = null,
    postInit: (Radio<T>.() -> Unit)? = null
) : IRadioConfig<T>,
    ComponentConfig<Radio<T>, T>(
        model = model,
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