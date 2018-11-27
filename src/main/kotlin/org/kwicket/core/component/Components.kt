package org.kwicket.core.component

import org.apache.wicket.Component
import org.apache.wicket.MarkupContainer
import org.apache.wicket.Page
import org.apache.wicket.WicketRuntimeException
import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.Button
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.markup.html.form.FormComponent
import org.apache.wicket.markup.html.image.Image
import org.apache.wicket.markup.html.link.Link
import org.apache.wicket.markup.html.media.MediaComponent
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.model.IModel
import org.apache.wicket.request.mapper.parameter.PageParameters
import org.apache.wicket.validation.IValidator
import org.kwicket.core.component.config.FormConfig
import org.kwicket.core.component.config.IAbstractButtonConfig
import org.kwicket.core.component.config.IAbstractFormConfig
import org.kwicket.core.component.config.IAbstractLinkConfig
import org.kwicket.core.component.config.IComponentConfig
import org.kwicket.core.component.config.IFormComponentConfig
import org.kwicket.core.component.config.IImageConfig
import org.kwicket.core.component.config.ILabelConfig
import org.kwicket.core.component.config.IMediaComponentConfig
import org.kwicket.core.component.config.LabelConfig
import org.kwicket.core.component.config.WebMarkupContainerConfig
import org.kwicket.core.component.factory.invoke
import org.kwicket.core.model.model
import kotlin.reflect.KClass

/**
 * Queues the [component] in the [MarkupContainer] and returns the queued [component], allowing a [Component] to be
 * included in a * parent [MarkupContainer] and also assigned to a variable in one line of code
 * (e.g., `val c = q(MyComponent("id")`).
 *
 * Note that the return type is different from the [MarkupContainer.add] and [MarkupContainer.queue] methods which
 * return the [MarkupContainer] that the component was added to, not the component that was added. This is because add
 * and queue are vararg whereas this method takes exactly one [Component].
 *
 * @receiver [MarkupContainer] to add the [component] to
 * @param C type of the [Component] to add to the [MarkupContainer]
 * @param M type of the @receiver
 * @param component [Component] to include in the @receiver
 * @return the [component] that was added to the [MarkupContainer]
 */
fun <C : Component, M : MarkupContainer> M.q(component: C): C = component.also { queue(it) }

/**
 * Configures the @receiver, a [Component].
 *
 * All parameters are optional; they are nullable and have default values of null so only ones that are of interest
 * need to be specified.
 *
 * Only non-null parameter values cause any change to the [Component].
. *
 * @param outputMarkupId sets whether an id is added to the markup if [outputMarkupId] is not null
 * @param outputMarkupPlaceholderTag sets whether a placeholder id is added to markup if [outputMarkupPlaceholderTag] is not null
 * @param visible sets whether the [Component] is isVisible if [visible] is not null
 * @param visibilityAllowed sets whether visibility is allowed on this [Component] if [visibilityAllowed] is not null
 * @param enabled sets whether the [Component] is isEnabled if [enabled] is not null
 * @param escapeModelStrings sets whether the [IModel] String values are to be HTML escaped is isEnabled
 *      if [escapeModelStrings] is not null
 * @param renderBodyOnly sets whether to only output the generated content or whether to also include the containing tag
 *      if [renderBodyOnly] is not null
 * @param behavior single behavior to add to the [Component]
 * @param behaviors [List] of [Behavior]s to add to the [Component]
 *
 * @receiver the [Component] to configure
 * @return the configured [Component]
 */
internal fun <C : Component> C.config(
    markupId: String? = null,
    outputMarkupId: Boolean? = null,
    outputMarkupPlaceholderTag: Boolean? = null,
    visible: Boolean? = null,
    visibilityAllowed: Boolean? = null,
    enabled: Boolean? = null,
    escapeModelStrings: Boolean? = null,
    renderBodyOnly: Boolean? = null,
    behavior: Behavior? = null,
    behaviors: List<Behavior>? = null
): C {
    markupId?.let { this.markupId = it }
    renderBodyOnly?.let { this.renderBodyOnly = it }
    outputMarkupId?.let { this.outputMarkupId = it }
    outputMarkupPlaceholderTag?.let { this.outputMarkupPlaceholderTag = it }
    visible?.let { this.isVisible = it }
    visibilityAllowed?.let { this.isVisibilityAllowed = it }
    enabled?.let { this.isEnabled = it }
    escapeModelStrings?.let { this.escapeModelStrings = it }
    when {
        behavior != null -> listOf(behavior) + (behaviors ?: emptyList())
        behaviors != null -> behaviors
        else -> emptyList()
    }.also { if (it.isNotEmpty()) add(*it.toTypedArray()) }
    return this
}

internal fun <C : Component> C.config(config: IComponentConfig<C, *>): C {
    config(
        markupId = config.markupId,
        outputMarkupId = config.outputMarkupId,
        outputMarkupPlaceholderTag = config.outputMarkupPlaceholderTag,
        visible = config.isVisible,
        visibilityAllowed = config.isVisibilityAllowed,
        enabled = config.isEnabled,
        escapeModelStrings = config.escapeModelStrings,
        renderBodyOnly = config.renderBodyOnly,
        behavior = config.behavior,
        behaviors = config.behaviors
    )
    config.postInit?.invoke(this)
    return this
}

internal fun <F : FormComponent<T>, T> F.config(config: IFormComponentConfig<F, T>): F {
    this.config(config as IComponentConfig<F, T>)
    config.isRequired?.let { isRequired = it }
    config.label?.let { label = it }
    config.validator?.let { add(it) }
    config.validators?.let { add(*it.toTypedArray()) }
    return this
}

internal fun <C : Button> C.config(config: IAbstractButtonConfig<C>): C {
    this.config(config as IComponentConfig<C, *>)
    config.defaultFormProcessing?.let { defaultFormProcessing = it }
    return this
}

internal fun <C : Link<T>, T> C.config(config: IAbstractLinkConfig<C, T>): C {
    this.config(config as IComponentConfig<C, *>)
    config.popupSettings?.let { popupSettings = it }
    return this
}

internal fun <C : MediaComponent, T> C.config(config: IMediaComponentConfig<C, T>): C {
    this.config(config as IComponentConfig<C, *>)
    config.isMuted?.let { isMuted = it }
    config.hasControls?.let { setControls(it) }
    config.preload?.let { preload = it }
    config.isAutoPlay?.let { isAutoplay = it }
    config.isLooping?.let { isLooping = it }
    config.startTime?.let { startTime = it }
    config.endTime?.let { endTime = it }
    config.mediaGroup?.let { mediaGroup = it }
    config.crossOrigin?.let { crossOrigin = it }
    config.type?.let { type = it }
    return this
}

internal fun <C : Image, T> C.config(config: IImageConfig<T>): C {
    this.config(config as IComponentConfig<Image, T>)
    config.resRef?.let { setImageResourceReference(it, config.resParams) }
    config.resRefs?.let { setImageResourceReferences(config.resParams, *it.toTypedArray()) }
    config.imageResource?.let { setImageResource(it) }
    config.imageResources?.let { setImageResources(*it.toTypedArray()) }
    config.xValues?.let { setXValues(*it.toTypedArray()) }
    config.sizes?.let { setSizes(*it.toTypedArray()) }
    config.postInit?.invoke(this)
    return this
}

internal fun <C : Form<T>, T> C.config(config: IAbstractFormConfig<C, T>): C {
    this.config(config as IComponentConfig<C, T>)
    config.isMultiPart?.let { isMultiPart = it }
    config.maxSize?.let { maxSize = it.bytes }
    config.fileMaxSize?.let { fileMaxSize = it.bytes }
    config.postInit?.invoke(this)
    return this
}


/**
 * Configures the @receiver, a [FormComponent].
 *
 * All parameters are optional; they are nullable and have default values of null so only ones that are of interest
 * need to be specified.
 *
 * Only non-null parameter values cause any change to the [FormComponent].
. *
 * @param outputMarkupId sets whether an id is added to the markup if [outputMarkupId] is not null
 * @param outputMarkupPlaceholderTag sets whether a placeholder id is added to markup if [outputMarkupPlaceholderTag] is not null
 * @param visible sets whether the [Component] is isVisible if [visible] is not null
 * @param visibilityAllowed sets whether visibility is allowed on this [Component] if [visibilityAllowed] is not null
 * @param enabled sets whether the [Component] is isEnabled if [enabled] is not null
 * @param escapeModelStrings sets whether the [IModel] String values are to be HTML escaped is isEnabled
 *      if [escapeModelStrings] is not null
 * @param renderBodyOnly sets whether to only output the generated content or whether to also include the containing tag
 *      if [renderBodyOnly] is not null
 * @param behavior single behavior to add to the [Component]
 * @param behaviors [List] of [Behavior]s to add to the [Component]
 * @param isRequired whether the [FormComponent] is isRequired
 * @param label name of the [FormComponent] that is used for a label and in validator messages
 * @param validator [IValidator] to add to the [FormComponent]
 * @param validators list of [IValidator]s to add to the [FormComponent]
 *
 * @receiver the [FormComponent] to configure
 * @return the configured [FormComponent]
 */
internal fun <T, C : FormComponent<T>> C.config(
    markupId: String? = null,
    outputMarkupId: Boolean? = null,
    outputMarkupPlaceholderTag: Boolean? = null,
    visible: Boolean? = null,
    visibilityAllowed: Boolean? = null,
    enabled: Boolean? = null,
    escapeModelStrings: Boolean? = null,
    renderBodyOnly: Boolean? = null,
    behavior: Behavior? = null,
    behaviors: List<Behavior>? = null,
    isRequired: Boolean? = null,
    label: IModel<String>? = null,
    validator: IValidator<T>? = null,
    validators: List<IValidator<T>>? = null
): C {
    (this as Component).config(
        markupId = markupId,
        outputMarkupId = outputMarkupId,
        outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
        visible = visible,
        visibilityAllowed = visibilityAllowed,
        enabled = enabled,
        escapeModelStrings = escapeModelStrings,
        renderBodyOnly = renderBodyOnly,
        behavior = behavior,
        behaviors = behaviors
    )
    isRequired?.let { this.isRequired = it }
    label?.let { this.label = it }
    when {
        validator != null -> listOf(validator) + (validators ?: emptyList())
        validators != null -> validators
        else -> emptyList()
    }.also { if (it.isNotEmpty()) add(*it.toTypedArray()) }
    return this
}

/**
 * Configures the @receiver, a [FormComponent].
 *
 * All parameters are optional; they are nullable and have default values of null so only ones that are of interest
 * need to be specified.
 *
 * Only non-null parameter values cause any change to the [FormComponent].
. *
 * @param outputMarkupId sets whether an id is added to the markup if [outputMarkupId] is not null
 * @param outputMarkupPlaceholderTag sets whether a placeholder id is added to markup if [outputMarkupPlaceholderTag] is not null
 * @param visible sets whether the [Component] is isVisible if [visible] is not null
 * @param visibilityAllowed sets whether visibility is allowed on this [Component] if [visibilityAllowed] is not null
 * @param enabled sets whether the [Component] is isEnabled if [enabled] is not null
 * @param escapeModelStrings sets whether the [IModel] String values are to be HTML escaped is isEnabled
 *      if [escapeModelStrings] is not null
 * @param renderBodyOnly sets whether to only output the generated content or whether to also include the containing tag
 *      if [renderBodyOnly] is not null
 * @param behavior single behavior to add to the [Component]
 * @param behaviors [List] of [Behavior]s to add to the [Component]
 * @param required whether the [FormComponent] is required
 * @param label name of the [FormComponent] that is used for a label and in validator messages
 * @param validators list of [IValidator]s to add to the [FormComponent]
 * @param defaultFormProcessing whether the backing model should be populated
 *
 * @receiver the [FormComponent] to configure
 * @return the configured [FormComponent]
 */
internal fun <B : Button> B.config(
    markupId: String? = null,
    outputMarkupId: Boolean? = null,
    outputMarkupPlaceholderTag: Boolean? = null,
    visible: Boolean? = null,
    visibilityAllowed: Boolean? = null,
    enabled: Boolean? = null,
    escapeModelStrings: Boolean? = null,
    renderBodyOnly: Boolean? = null,
    behavior: Behavior? = null,
    behaviors: List<Behavior>? = null,
    required: Boolean? = null,
    label: IModel<String>? = null,
    validator: IValidator<String>? = null,
    validators: List<IValidator<String>>? = null,
    defaultFormProcessing: Boolean? = null
): B {
    (this as FormComponent<String>).config(
        markupId = markupId,
        outputMarkupId = outputMarkupId,
        outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
        visible = visible,
        visibilityAllowed = visibilityAllowed,
        enabled = enabled,
        escapeModelStrings = escapeModelStrings,
        renderBodyOnly = renderBodyOnly,
        behavior = behavior,
        behaviors = behaviors,
        isRequired = required,
        label = label,
        validator = validator,
        validators = validators
    )
    defaultFormProcessing?.let { this.defaultFormProcessing = defaultFormProcessing }
    return this
}

fun <P : Page> Component.setResponsePage(page: KClass<P>, params: PageParameters? = null) =
    setResponsePage(page.java, params)

fun Component.target(target: AjaxRequestTarget? = null): AjaxRequestTarget =
    target ?: requestCycle.find(AjaxRequestTarget::class.java)
        .orElseThrow { WicketRuntimeException("No AjaxRequestTarget found in the request cycle") }

fun Component.refresh(target: AjaxRequestTarget? = null) = target(target).add(this)

fun <C: Component, T, I: IComponentConfig<C, T>> MarkupContainer.q(
    id: String,
    config: I,
    block: (I.() -> Unit)? = null,
    factory: (String, I) -> C
) = q(factory.invoke(id, config.apply { block?.invoke(this) }))

// FIXME: below is an experiment in adding components by their configuration

fun <C: KClass<I>, I: IComponentConfig<*, *>> findFactory(config: C): ((String, I) -> C)? = { id, config -> (config as ILabelConfig<*>).invoke(id) as C}

inline fun <C: Component, T, reified I: IComponentConfig<C, T>> MarkupContainer.x(id: String, config: I, block: I.() -> Unit = {}): C {
    //return config.also{ block?.invoke(it) }.findFactory()?.invoke(id) ?: throw RuntimeException("No factory!")
    val x = findFactory(I::class)!!
    //val config = I::class.primaryConstructor?.call()!!
    block.invoke(config)
    return x.invoke(id, config) as C
}

class Andrew() : Panel("test") {

    init {
        val y = x("test", LabelConfig(model = "hi".model())) {
            onConfig = {
                isVisible = false
            }
        }
        val z = x("container", WebMarkupContainerConfig<Unit>())
        val form = x("container", FormConfig<String>()) {
            model = "Hi".model()
        }
    }

}
