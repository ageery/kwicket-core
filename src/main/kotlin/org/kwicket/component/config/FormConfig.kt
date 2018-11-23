package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.model.IModel
import org.kwicket.FileSize

/**
 * Configuration for creating a [Form].
 *
 * @param T type of the model
 */
interface IFormConfig<T> : IAbstractFormConfig<Form<T>, T>

/**
 * Configuration for creating a [Form].
 *
 * @param onSubmit lambda to call when the form data is successfully submitted
 * @param onError lambda to call when the form data is not valid
 * @param isMultiPart whether the form is multi-part
 * @param maxSize max size of the form data to submit
 * @param fileMaxSize max size of a file that can be submitted by the form
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
class FormConfig<T>(
    model: IModel<T>? = null,
    onSubmit: (Form<T>.() -> Unit)? = null,
    onError: (Form<T>.() -> Unit)? = null,
    isMultiPart: Boolean? = null,
    maxSize: FileSize? = null,
    fileMaxSize: FileSize? = null,
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
    onConfig: (Form<T>.() -> Unit)? = null,
    postInit: (Form<T>.() -> Unit)? = null
) : IFormConfig<T>,
    AbstractFormConfig<Form<T>, T>(
        model = model,
        onSubmit = onSubmit,
        onError = onError,
        isMultiPart = isMultiPart,
        maxSize = maxSize,
        fileMaxSize = fileMaxSize,
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