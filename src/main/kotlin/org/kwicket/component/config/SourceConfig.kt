package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.image.Image
import org.apache.wicket.markup.html.image.Source
import org.apache.wicket.model.IModel
import org.apache.wicket.request.mapper.parameter.PageParameters
import org.apache.wicket.request.resource.IResource
import org.apache.wicket.request.resource.ResourceReference

interface ISourceConfig<T> : IAbstractImageConfig<Source, T> {
    var media: String?
    var crossOrigin: Image.Cors?
}

class SourceConfig<T>(
    model: IModel<T>? = null,
    override var media: String? = null,
    override var crossOrigin: Image.Cors? = null,
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
    onConfig: (Source.() -> Unit)? = null,
    postInit: (Source.() -> Unit)? = null
) : ISourceConfig<T>,
    AbstractImageConfig<Source, T>(
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