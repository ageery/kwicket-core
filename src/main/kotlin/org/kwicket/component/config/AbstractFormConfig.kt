package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.model.IModel
import org.kwicket.FileSize

/**
 * Returns whether the form configuration requires a sub-class for the standard Wicket component.
 */
internal val IAbstractFormConfig<*, *>.requiresSubclass: Boolean
    get() = (this as IComponentConfig<*, *>).requiresSubclass || onSubmit != null || onError != null

/**
 * Configuration for creating a sub-class of [Form].
 *
 * @param C type of [Form] component
 * @param T type of the model
 * @property onSubmit lambda to execute when the form is successfully submitted
 * @property onError lambda to execute when there are validation errors in the form
 * @property isMultiPart whether the form is multi-part
 * @property maxSize maximum amount of data that can be submitted via the form
 * @property fileMaxSize maximum size of a file that can be submitted as part of the form
 */
interface IAbstractFormConfig<C : Form<T>, T> : IComponentConfig<C, T> {
    var onSubmit: (C.() -> Unit)?
    var onError: (C.() -> Unit)?
    var isMultiPart: Boolean?
    var maxSize: FileSize?
    var fileMaxSize: FileSize?
}

/**
 * Configuration for creating a sub-class of [Form].
 *
 * @param C type of [Form] component
 * @param T type of the model
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
 * @property onSubmit lambda to execute when the form is successfully submitted
 * @property onError lambda to execute when there are validation errors in the form
 * @property isMultiPart whether the form is multi-part
 * @property maxSize maximum amount of data that can be submitted via the form
 * @property fileMaxSize maximum size of a file that can be submitted as part of the form
 */
abstract class AbstractFormConfig<C : Form<T>, T>(
    model: IModel<T>? = null,
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
    onConfig: (C.() -> Unit)? = null,
    postInit: (C.() -> Unit)? = null,
    override var onSubmit: (C.() -> Unit)? = null,
    override var onError: (C.() -> Unit)? = null,
    override var isMultiPart: Boolean? = null,
    override var maxSize: FileSize? = null,
    override var fileMaxSize: FileSize? = null
) : IAbstractFormConfig<C, T>,
    ComponentConfig<C, T>(
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