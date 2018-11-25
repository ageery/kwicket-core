package org.kwicket.component.config

import org.apache.wicket.Page
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.form.Button
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.markup.html.link.BookmarkablePageLink
import org.apache.wicket.model.IModel
import org.apache.wicket.request.mapper.parameter.PageParameters
import kotlin.reflect.KClass

/**
 * Configuration for creating a sub-class of [BookmarkablePageLink].
 *
 * @param T type of the model
 * @param P type of the page
 * @property page class of the page the link points to
 * @property pageParams parameters to add to the link
 */
interface IBookmarkablePageLinkConfig<T, P: Page> : IAbstractLinkConfig<BookmarkablePageLink<T>, T> {
    var page: KClass<P>?
    var pageParams: PageParameters?
}

/**
 * Configuration for creating a sub-class of a [BookmarkablePageLink].
 *
 * @param T type of the model
 * @param P type of the page
 * @property page class of the page the link points to
 * @property pageParams parameters to add to the link
 * @property form [Form] that the link is submitting
 * @param markupId optional unique id to use in the associated markup
 * @param outputMarkupId whether to include an HTML id for the component in the markup
 * @param outputMarkupPlaceholderTag whether to include a placeholder tag for the component in the markup when the
 * component is not visible
 * @param isVisible whether the component is initially visible
 * @param isEnabled whether the component is initially enabled
 * @param isVisibilityAllowed whether the component is allowed to be visible
 * @param escapeModelStrings whether model strings should be escaped
 * @param renderBodyOnly whether the tag associated with the component should be included in the markup
 * @param behavior optional [Behavior] to add to the component
 * @param behaviors optional List of [Behavior]s to add to the component
 * @param stateless whether to include a hint that the component is stateless
 * @param onConfig optional lambda to execute in the onConfigure lifecycle method
 * @param postInit optional lambda to execute after the component has been created
 */
class BookmarkablePageLinkConfig<T, P: Page>(
    model: IModel<T>? = null,
    override var page: KClass<P>? = null,
    override var pageParams: PageParameters? = null,
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
    onConfig: (BookmarkablePageLink<T>.() -> Unit)? = null,
    postInit: (BookmarkablePageLink<T>.() -> Unit)? = null
) : IBookmarkablePageLinkConfig<T, P>,
    AbstractLinkConfig<BookmarkablePageLink<T>, T>(
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