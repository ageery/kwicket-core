package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.Button
import org.apache.wicket.markup.html.form.Check
import org.apache.wicket.markup.html.form.CheckGroup
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.model.IModel

interface IAbstractButtonConfig<C: Button> : IComponentConfig<C, String> {
    var defaultFormProcessing: Boolean?
    // FIXME: does it make sense for Button to be able to set its form?
    var form: Form<*>?
}

abstract class AbstractButtonConfig<C: Button, T>(
    model: IModel<String>? = null,
    override var defaultFormProcessing: Boolean? = null,
    override var form: Form<*>? = null,
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
) : IAbstractButtonConfig<C>,
    ComponentConfig<C, String>(
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