//package org.kwicket.component.queued
//
//import org.apache.wicket.behavior.Behavior
//import org.apache.wicket.markup.html.basic.Label
//import org.apache.wicket.markup.html.panel.Panel
//import org.apache.wicket.model.IModel
//import org.kwicket.model.model
//
//class QueuedLabelTest : AbstractQueuedTest<Label, String?>() {
//
//    override val model: IModel<String?> = "test label".model()
//
//    override fun Panel.queueComponent(
//        id: String,
//        model: IModel<String?>?,
//        markupId: String?,
//        outputMarkupId: Boolean?,
//        outputMarkupPlaceholderTag: Boolean?,
//        visible: Boolean?,
//        visibilityAllowed: Boolean?,
//        enabled: Boolean?,
//        escapeModelStrings: Boolean?,
//        renderBodyOnly: Boolean?,
//        behavior: Behavior?,
//        behaviors: List<Behavior>?,
//        onConfig: (Label.() -> Unit)?,
//        postInit: (Label.() -> Unit)?
//    ): Label = label(
//        id = id,
//        model = model,
//        markupId = markupId,
//        outputMarkupId = outputMarkupId,
//        outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
//        visible = visible,
//        enabled = enabled,
//        visibilityAllowed = visibilityAllowed,
//        escapeModelStrings = escapeModelStrings,
//        renderBodyOnly = renderBodyOnly,
//        behavior = behavior,
//        behaviors = behaviors,
//        onConfig = onConfig,
//        postInit = postInit
//    )
//
//}