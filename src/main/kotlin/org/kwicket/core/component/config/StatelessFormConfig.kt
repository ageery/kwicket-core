package org.kwicket.core.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.markup.html.form.StatelessForm
import org.apache.wicket.model.IModel
import org.kwicket.core.FileSize

/**
 * Configuration for creating a [WebMarkupContainer] component.
 *
 * @param T type of the model
 */
interface IStatelessFormConfig<T> : IAbstractFormConfig<StatelessForm<T>, T>

/**
 * Configuration for creating a [StatelessForm] component.
 *
 * @param T type of the model
 * @param onSubmit lambda to call when the form is successfully submitted
 * @param onError lambda to call when the form data has validation errors
 * @param isMultiPart whether the form is multi-part
 * @param maxSize max size of the data being submitted through the form
 * @param fileMaxSize max size of a file submitted as part of the form
 * @param model model for the component
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
class StatelessFormConfig<T>(
    model: IModel<T>? = null,
    onSubmit: (StatelessForm<T>.() -> Unit)? = null,
    onError: (StatelessForm<T>.() -> Unit)? = null,
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
    onConfig: (StatelessForm<T>.() -> Unit)? = null,
    postInit: (StatelessForm<T>.() -> Unit)? = null
) : IStatelessFormConfig<T>,
    AbstractFormConfig<StatelessForm<T>, T>(
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