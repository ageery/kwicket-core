package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.CheckGroup
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator

// FIXME: we should be able form component -- can't figure out why it doesn't work...
interface ICheckGroupConfig<T, L: Collection<T>> : IComponentConfig<CheckGroup<T>, L> {
    var label: IModel<String>?
    var isRequired: Boolean?
    var validator: IValidator<L>?
    var validators: List<IValidator<L>>?
}

class CheckGroupConfig<T, L: Collection<T>>(
    model: IModel<L>? = null,
    override var label: IModel<String>? = null,
    override var isRequired: Boolean? = null,
    override var validator: IValidator<L>? = null,
    override var validators: List<IValidator<L>>? = null,
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
    onConfig: (CheckGroup<T>.() -> Unit)? = null,
    postInit: (CheckGroup<T>.() -> Unit)? = null
) : ICheckGroupConfig<T, L>,
    ComponentConfig<CheckGroup<T>, L>(
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