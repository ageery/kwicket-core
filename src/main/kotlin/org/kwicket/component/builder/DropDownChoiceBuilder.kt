package org.kwicket.component.builder

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.DropDownChoice
import org.apache.wicket.markup.html.form.IChoiceRenderer
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator
import org.kwicket.component.factory.dropDownChoiceFactory

interface IDropDownChoiceBuilder<T> : IComponentBuilder<DropDownChoice<T>, T?> {
    var label: IModel<String>?
    var isRequired: Boolean?
    var validator: IValidator<T>?
    var validators: List<IValidator<T>>?
}

class DropDownChoiceBuilder<T>(
    model: IModel<T?>? = null,
    val choices: IModel<List<T>>,
    val choiceRenderer: IChoiceRenderer<T>? = null,
    override var label: IModel<String>? = null,
    override var isRequired: Boolean? = null,
    override var validator: IValidator<T>? = null,
    override var validators: List<IValidator<T>>? = null,
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
    onConfig: (DropDownChoice<T>.() -> Unit)? = null,
    postInit: (DropDownChoice<T>.() -> Unit)? = null
) : IDropDownChoiceBuilder<T>,
    ComponentBuilder<DropDownChoice<T>, T?>(
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

    override fun build(id: String): DropDownChoice<T> =
        dropDownChoiceFactory(
            id = id,
            model = model as IModel<T?>,
            choices = choices,
            choiceRenderer = choiceRenderer,
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
            isRequired = isRequired,
            validator = validator,
            validators = validators
        )

}
