package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.image.InlineImage
import org.apache.wicket.model.IModel
import org.apache.wicket.request.resource.PackageResourceReference

interface IInlineImageConfig<T> : IComponentConfig<InlineImage, T> {
    var resRef: PackageResourceReference?
}

class InlineImageConfig<T>(
    model: IModel<T>? = null,
    override var resRef: PackageResourceReference? = null,
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
    onConfig: (InlineImage.() -> Unit)? = null,
    postInit: (InlineImage.() -> Unit)? = null
) : IInlineImageConfig<T>,
    ComponentConfig<InlineImage, T>(
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