package org.kwicket.core.component.dsl.tag

//import kotlinx.html.HTMLTag
//import kotlinx.html.HtmlBlockTag
//import kotlinx.html.TagConsumer
//import kotlinx.html.visit
//import org.apache.wicket.behavior.Behavior
//import org.apache.wicket.markup.html.form.CheckGroup
//import org.apache.wicket.model.IModel
//import org.apache.wicket.validation.IValidator
//import org.kwicket.core.component.config.CheckGroupConfig
//import org.kwicket.core.component.config.ICheckGroupConfig
//import org.kwicket.core.component.dsl.ConfigurableComponentTag
//import org.kwicket.core.component.factory.checkGroupFactory
//
//fun <T, L: Collection<T>> HTMLTag.checkGroup(
//    id: String? = null,
//    tagName: String = "input",
//    label: IModel<String>? = null,
//    validator: IValidator<MutableCollection<T>>? = null,
//    validators: List<IValidator<MutableCollection<T>>>? = null,
//    model: IModel<MutableCollection<T>>? = null,
//    markupId: String? = null,
//    outputMarkupId: Boolean? = null,
//    outputMarkupPlaceholderTag: Boolean? = null,
//    visible: Boolean? = null,
//    visibilityAllowed: Boolean? = null,
//    enabled: Boolean? = null,
//    escapeModelStrings: Boolean? = null,
//    renderBodyOnly: Boolean? = null,
//    behavior: Behavior? = null,
//    behaviors: List<Behavior>? = null,
//    initialAttributes: Map<String, String> = emptyMap(),
//    block: CheckGroupTag<T, L>.() -> Unit = {}
//): Unit =
//    CheckGroupTag(
//        id = id,
//        tagName = tagName,
//        config = CheckGroupConfig(
//            label = label,
//            validator = validator,
//            validators = validators,
//            model = model,
//            markupId = markupId,
//            outputMarkupId = outputMarkupId,
//            outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
//            isVisible = visible,
//            isVisibilityAllowed = visibilityAllowed,
//            isEnabled = enabled,
//            escapeModelStrings = escapeModelStrings,
//            renderBodyOnly = renderBodyOnly,
//            behavior = behavior,
//            behaviors = behaviors
//        ),
//        initialAttributes = initialAttributes,
//        consumer = consumer
//    ).visit(block)
//
//open class CheckGroupTag<T, L : Collection<T>>(
//    id: String? = null,
//    tagName: String = "span",
//    initialAttributes: Map<String, String> = emptyMap(),
//    consumer: TagConsumer<*>,
//    config: ICheckGroupConfig<T, L>,
//    factory: (String, ICheckGroupConfig<T, L>) -> CheckGroup<T> = { cid, c -> checkGroupFactory(cid, c) }
//) : ICheckGroupConfig<T, L> by config,
//    ConfigurableComponentTag<L, CheckGroup<T>, ICheckGroupConfig<CheckGroup<T>, L>>(
//        id = id,
//        initialAttributes = initialAttributes,
//        consumer = consumer,
//        tagName = tagName,
//        config = config,
//        factory = factory
//    ), HtmlBlockTag
