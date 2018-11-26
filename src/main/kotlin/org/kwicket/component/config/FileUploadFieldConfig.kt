package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.CheckBox
import org.apache.wicket.markup.html.form.FormComponent
import org.apache.wicket.markup.html.form.upload.FileUpload
import org.apache.wicket.markup.html.form.upload.FileUploadField
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator

/**
 * Configuration for creating a [FileUploadField] component.
 */
interface IFileUploadFieldConfig : IFormComponentConfig<FileUploadField, MutableList<FileUpload>>

/**
 * Configuration for creating a [FileUploadField] component.
 *
 * @param label associated label of the form component
 * @param isRequired whether a value of the form component is required
 * @param validator how to determine whether the value is valid
 * @param validators list of validation checks
 * @param markupId optional unique id to use in the associated markup
 * @param outputMarkupId whether to include an HTML id for the component in the markup
 * @param outputMarkupPlaceholderTag whether to include a placeholder tag for the component in the markup when the
 * component is not visible
 * @param isVisible whether the component is initially visible
 * @param isEnabled whether the component is initially enabled
 * @param isVisibilityAllowed whether the component is allowed to be visible
 * @param escapeModelStrings whether model strings should be escaped
 * @param renderBodyOnly whether the tag associated with the component should be included in the markup
 * @param behavior optional [Behavior] to add to the component
 * @param behaviors optional List of [Behavior]s to add to the component
 * @param stateless whether to include a hint that the component is stateless
 * @param onConfig optional lambda to execute in the onConfigure lifecycle method
 * @param postInit optional lambda to execute after the component has been created
 */
class FileUploadFieldConfig(
    model: IModel<MutableList<FileUpload>>? = null,
    label: IModel<String>? = null,
    isRequired: Boolean? = null,
    validator: IValidator<in MutableList<FileUpload>>? = null,
    validators: List<IValidator<in MutableList<FileUpload>>>? = null,
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
    onConfig: (FileUploadField.() -> Unit)? = null,
    postInit: (FileUploadField.() -> Unit)? = null
) : IFileUploadFieldConfig,
    FormComponentConfig<FileUploadField, MutableList<FileUpload>>(
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