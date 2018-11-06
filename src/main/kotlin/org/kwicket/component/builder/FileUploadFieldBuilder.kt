package org.kwicket.component.builder

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.upload.FileUpload
import org.apache.wicket.markup.html.form.upload.FileUploadField
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator
import org.kwicket.component.factory.fileUploadFieldFactory

interface IFileUploadFieldBuilder : IComponentBuilder<FileUploadField, MutableList<FileUpload>> {
    var label: IModel<String>?
    var isRequired: Boolean?
    var validator: IValidator<MutableList<FileUpload>>?
    var validators: List<IValidator<MutableList<FileUpload>>>?
}

class FileUploadFieldBuilder(
    model: IModel<MutableList<FileUpload>>? = null,
    override var label: IModel<String>? = null,
    override var isRequired: Boolean? = null,
    override var validator: IValidator<MutableList<FileUpload>>? = null,
    override var validators: List<IValidator<MutableList<FileUpload>>>? = null,
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
    onConfig: (FileUploadField.() -> Unit)? = null,
    postInit: (FileUploadField.() -> Unit)? = null
) : IFileUploadFieldBuilder,
    ComponentBuilder<FileUploadField, MutableList<FileUpload>>(
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

    override fun build(id: String): FileUploadField =
        fileUploadFieldFactory(
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
            isRequired = isRequired,
            validator = validator,
            validators = validators
        )

}
