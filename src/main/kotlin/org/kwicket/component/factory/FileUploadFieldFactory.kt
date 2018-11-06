package org.kwicket.component.factory

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.upload.FileUpload
import org.apache.wicket.markup.html.form.upload.FileUploadField
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator
import org.kwicket.component.config

fun fileUploadFieldFactory(
    id: String,
    model: IModel<MutableList<FileUpload>>? = null,
    label: IModel<String>? = null,
    isRequired: Boolean? = null,
    validator: IValidator<MutableList<FileUpload>>? = null,
    validators: List<IValidator<MutableList<FileUpload>>>? = null,
    markupId: String? = null,
    outputMarkupId: Boolean? = null,
    outputMarkupPlaceholderTag: Boolean? = null,
    visible: Boolean? = null,
    enabled: Boolean? = null,
    visibilityAllowed: Boolean? = null,
    escapeModelStrings: Boolean? = null,
    renderBodyOnly: Boolean? = null,
    behavior: Behavior? = null,
    behaviors: List<Behavior>? = null,
    onConfig: (FileUploadField.() -> Unit)? = null,
    postInit: (FileUploadField.() -> Unit)? = null
): FileUploadField =
    if (onConfig != null) {
        object : FileUploadField(id, model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig.invoke(this)
            }
        }
    } else {
        FileUploadField(id, model)
    }.config(
        markupId = markupId,
        outputMarkupId = outputMarkupId,
        outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
        visible = visible,
        enabled = enabled,
        visibilityAllowed = visibilityAllowed,
        escapeModelStrings = escapeModelStrings,
        renderBodyOnly = renderBodyOnly,
        behavior = behavior,
        behaviors = behaviors,
        isRequired = isRequired,
        label = label,
        validator = validator,
        validators = validators
    ).also { postInit?.invoke(it) }