package org.kwicket.core.component.dsl

import org.apache.wicket.MarkupContainer
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.IMarkupCacheKeyProvider
import org.apache.wicket.markup.IMarkupResourceStreamProvider
import org.apache.wicket.markup.html.border.Border
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.util.resource.IResourceStream
import org.apache.wicket.util.resource.StringResourceStream
import org.kwicket.core.component.wrapper.KBorder

///**
// * Returns a [Panel] where the content is constructed from the [body] parameter tag.
// *
// * @param id Wicket markup wicketId
// * @param markupId wicketId of the tag to use in the associated markup
// * @param outputMarkupId whether to include an HTML wicketId for the component in the markup
// * @param outputMarkupPlaceholderTag whether to include a placeholder tag for the component in the markup when the
// * component is not isVisible
// * @param isVisible whether the component is isVisible
// * @param isEnabled whether the component is isEnabled
// * @param isVisibilityAllowed whether the component is allowed to be isVisible
// * @param escapeModelStrings whether model strings should be escaped
// * @param renderBodyOnly whether the tag associated with the component should be included in the markup
// * @param behavior [Behavior] to add to the component
// * @param behaviors List of [Behavior]s to add to the component
// * @param onConfig lambda to execute in the onConfigure lifecycle method
// * @param body lambda for creating the body of the panel
// */
//fun panel(
//    id: String,
//    markupId: String? = null,
//    outputMarkupId: Boolean? = null,
//    outputMarkupPlaceholderTag: Boolean? = null,
//    isVisibilityAllowed: Boolean? = null,
//    isVisible: Boolean? = null,
//    isEnabled: Boolean? = null,
//    renderBodyOnly: Boolean? = null,
//    escapeModelStrings: Boolean? = null,
//    behavior: Behavior? = null,
//    behaviors: List<Behavior>? = null,
//    onConfig: ((Panel) -> Unit)? = null,
//    body: PanelTag.() -> Unit
//): Panel =
//    BuilderPanel(
//        id = id,
//        markupId = markupId,
//        outputMarkupId = outputMarkupId,
//        outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
//        isVisible = isVisible,
//        isVisibilityAllowed = isVisibilityAllowed,
//        isEnabled = isEnabled,
//        escapeModelStrings = escapeModelStrings,
//        renderBodyOnly = renderBodyOnly,
//        behavior = behavior,
//        behaviors = behaviors,
//        onConfig = onConfig
//    ) {
//        wicket().panel { body() }
//    }

/**
 * Returns a [Panel] where the content is constructed from the [body] parameter tag.
 *
 * @param id Wicket markup wicketId
 * @param markupId wicketId of the tag to use in the associated markup
 * @param outputMarkupId whether to include an HTML wicketId for the component in the markup
 * @param outputMarkupPlaceholderTag whether to include a placeholder tag for the component in the markup when the
 * component is not isVisible
 * @param visible whether the component is isVisible
 * @param enabled whether the component is isEnabled
 * @param visibilityAllowed whether the component is allowed to be isVisible
 * @param escapeModelStrings whether model strings should be escaped
 * @param renderBodyOnly whether the tag associated with the component should be included in the markup
 * @param behavior [Behavior] to add to the component
 * @param behaviors List of [Behavior]s to add to the component
 * @param onConfig lambda to execute in the onConfigure lifecycle method
 * @param body FIXME: lambda for creating the body of the panel
 */
internal class BuilderBorder(
    id: String,
    markupId: String? = null,
    outputMarkupId: Boolean? = null,
    outputMarkupPlaceholderTag: Boolean? = null,
    visibilityAllowed: Boolean? = null,
    visible: Boolean? = null,
    enabled: Boolean? = null,
    renderBodyOnly: Boolean? = null,
    escapeModelStrings: Boolean? = null,
    behavior: Behavior? = null,
    behaviors: List<Behavior>? = null,
    onConfig: ((Border) -> Unit)? = null,
    body: RegionDescriptor
) : KBorder(
    id = id,
    markupId = markupId,
    visible = visible,
    visibilityAllowed = visibilityAllowed,
    enabled = enabled,
    escapeModelStrings = escapeModelStrings,
    behavior = behavior,
    outputMarkupId = outputMarkupId,
    outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
    renderBodyOnly = renderBodyOnly,
    behaviors = behaviors,
    onConfig = onConfig
), IMarkupResourceStreamProvider, IMarkupCacheKeyProvider {
    private val markup: IResourceStream

    init {
        markup = StringResourceStream(body.markup)
        body.builders.forEach { it.addTo(this) }
    }

    override fun getMarkupResourceStream(container: MarkupContainer, containerClass: Class<*>): IResourceStream = markup
    override fun getCacheKey(container: MarkupContainer, containerClass: Class<*>): String? = null

    override fun onConfigure() {
        super.onConfigure()
        onConfig?.invoke(this)
    }

}