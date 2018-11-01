package org.kwicket.component.builder

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.TextField
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator
import org.kwicket.component.factory.textFieldFactory
import kotlin.reflect.KClass

interface ITextFieldBuilder<T : Any> : IComponentBuilder<TextField<T>, T?> {
    val type: KClass<T>?
    var label: IModel<String>?
    var isRequired: Boolean?
    var validator: IValidator<T>?
    var validators: List<IValidator<T>>?
}

class TextFieldBuilder<T : Any>(
    model: IModel<T?>? = null,
    override val type: KClass<T>? = null,
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
    onConfig: (TextField<T>.() -> Unit)? = null,
    postInit: (TextField<T>.() -> Unit)? = null
) : ITextFieldBuilder<T>,
    ComponentBuilder<TextField<T>, T?>(
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

    override fun build(id: String): TextField<T> =
        textFieldFactory(
            id = id,
            type = type,
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
            isRequired = isRequired,
            validator = validator,
            validators = validators
        )

}
