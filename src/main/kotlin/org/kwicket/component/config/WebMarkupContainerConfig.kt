package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.markup.html.form.Check
import org.apache.wicket.markup.html.form.CheckGroup
import org.apache.wicket.model.IModel

interface IWebMarkupContainerConfig<T> : IComponentConfig<WebMarkupContainer, T>

class WebMarkupContainerConfig<T>(
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
    onConfig: (WebMarkupContainer.() -> Unit)? = null,
    postInit: (WebMarkupContainer.() -> Unit)? = null
) : IWebMarkupContainerConfig<T>,
    ComponentConfig<WebMarkupContainer, T>(
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