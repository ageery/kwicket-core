package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.image.Image
import org.apache.wicket.model.IModel
import org.apache.wicket.request.mapper.parameter.PageParameters
import org.apache.wicket.request.resource.IResource
import org.apache.wicket.request.resource.ResourceReference

interface IAbstractImageConfig<C : Image, T> : IComponentConfig<C, T> {
    var resRef: ResourceReference?
    var resParams: PageParameters?
    var resRefs: List<ResourceReference>?
    var imageResource: IResource?
    var imageResources: List<IResource>?
    var xValues: List<String>?
    var sizes: List<String>?
}

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