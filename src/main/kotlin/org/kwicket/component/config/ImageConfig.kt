package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.image.Image
import org.apache.wicket.markup.html.image.InlineImage
import org.apache.wicket.model.IModel
import org.apache.wicket.request.mapper.parameter.PageParameters
import org.apache.wicket.request.resource.IResource
import org.apache.wicket.request.resource.ResourceReference

/**
 * Configuration for creating an [Image] component.
 *
 * @param T type of the model
 * @property resRef resource reference for the image
 * @property resParams parameters to add to the resource url
 * @property resRefs list of resource references for the image
 * @property imageResource resource for the image
 * @property imageResources list of resources for the image
 * @property xValues list of x-values of the image
 * @property sizes list of sizes of the image
 */
interface IAbstractImageConfig<C : Image, T> : IComponentConfig<C, T> {
    var resRef: ResourceReference?
    var resParams: PageParameters?
    var resRefs: List<ResourceReference>?
    var imageResource: IResource?
    var imageResources: List<IResource>?
    var xValues: List<String>?
    var sizes: List<String>?
}

/**
 * Configuration for creating an [Image] component.
 *
 * @param T type of the model
 * @property resRef resource reference for the image
 * @property resParams parameters to add to the resource url
 * @property resRefs list of resource references for the image
 * @property imageResource resource for the image
 * @property imageResources list of resources for the image
 * @property xValues list of x-values of the image
 * @property sizes list of sizes of the image
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
open class AbstractImageConfig<C : Image, T>(
    model: IModel<T>? = null,
    override var resRef: ResourceReference? = null,
    override var resParams: PageParameters? = null,
    override var resRefs: List<ResourceReference>? = null,
    override var imageResource: IResource? = null,
    override var imageResources: List<IResource>? = null,
    override var xValues: List<String>? = null,
    override var sizes: List<String>? = null,
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
    postInit: (C.() -> Unit)? = null
) : IAbstractImageConfig<C, T>,
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

interface IImageConfig<T> : IAbstractImageConfig<Image, T>

open class ImageConfig<T>(
    model: IModel<T>? = null,
    resRef: ResourceReference? = null,
    resParams: PageParameters? = null,
    resRefs: List<ResourceReference>? = null,
    imageResource: IResource? = null,
    imageResources: List<IResource>? = null,
    xValues: List<String>? = null,
    sizes: List<String>? = null,
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
    onConfig: (Image.() -> Unit)? = null,
    postInit: (Image.() -> Unit)? = null
) : IImageConfig<T>,
    AbstractImageConfig<Image, T>(
        model = model,
        resRef = resRef,
        resParams = resParams,
        resRefs = resRefs,
        imageResource = imageResource,
        imageResources = imageResources,
        xValues = xValues,
        sizes = sizes,
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