package org.kwicket.component.builder

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.CheckGroup
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator
import org.kwicket.component.factory.checkGroupFactory

interface ICheckGroupBuilder<T> : IComponentBuilder<CheckGroup<T>, MutableCollection<T>> {
    var label: IModel<String>?
    var validator: IValidator<MutableCollection<T>>?
    var validators: List<IValidator<MutableCollection<T>>>?
}

class CheckGroupBuilder<T>(
    model: IModel<MutableCollection<T>>? = null,
    override var label: IModel<String>? = null,
    override var validator: IValidator<MutableCollection<T>>? = null,
    override var validators: List<IValidator<MutableCollection<T>>>? = null,
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
    onConfig: (CheckGroup<T>.() -> Unit)? = null,
    postInit: (CheckGroup<T>.() -> Unit)? = null
) : ICheckGroupBuilder<T>,
    ComponentBuilder<CheckGroup<T>, MutableCollection<T>>(
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
        onConfig = onConfig,
        postInit = postInit
    ) {

    override fun build(id: String): CheckGroup<T> =
        checkGroupFactory(
            id = id,
            model = model,
            markupId = markupId,
            outputMarkupId = outputMarkupId,
            outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
            visible = isVisible,
            visibilityAllowed = isVisibilityAllowed,
            enabled = isEnabled,
            escapeModelStrings = escapeModelStrings,
            renderBodyOnly = renderBodyOnly,
            behavior = behavior,
            behaviors = behaviors,
            onConfig = onConfig,
            postInit = postInit,
            label = label,
            validator = validator,
            validators = validators
        )

}
