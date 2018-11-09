package org.kwicket.component.factory

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.image.Source
import org.apache.wicket.model.IModel
import org.apache.wicket.request.mapper.parameter.PageParameters
import org.apache.wicket.request.resource.IResource
import org.apache.wicket.request.resource.ResourceReference
import org.kwicket.component.config

fun sourceFactory(
    id: String,
    resRef: ResourceReference? = null,
    resParams: PageParameters? = null,
    resRefs: List<ResourceReference>? = null,
    imageResource: IResource? = null,
    imageResources: List<IResource>? = null,
    model: IModel<*>? = null,
    markupId: String? = null,
    outputMarkupId: Boolean? = null,
    outputMarkupPlaceholderTag: Boolean? = null,
    visible: Boolean? = null,
    enabled: Boolean? = null,
    visibilityAllowed: Boolean? = null,
    escapeModelStrings: Boolean? = null,
    renderBodyOnly: Boolean? = null,
    behavior: Behavior? = null,
    xValues: List<String>? = null,
    sizes: List<String>? = null,
    media: String? = null,
    behaviors: List<Behavior>? = null,
    onConfig: (Source.() -> Unit)? = null,
    postInit: (Source.() -> Unit)? = null
): Source =
    if (onConfig != null) {
        object : Source(id, model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig.invoke(this)
            }

        }
    } else {
        Source(id, model)
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
        behaviors = behaviors
    ).also {  image ->
        resRef?.let { image.setImageResourceReference(it, resParams) }
        resRefs?.let { image.setImageResourceReferences(resParams, *it.toTypedArray()) }
        imageResource?.let { image.setImageResource(it) }
        imageResources?.let { image.setImageResources(*it.toTypedArray()) }
        xValues?.let { image.setXValues(*it.toTypedArray()) }
        sizes?.let { image.setSizes(*it.toTypedArray()) }
        media?.let { image.media = it }
        postInit?.invoke(image)
    }