package org.kwicket.component.factory

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.model.IModel
import org.junit.jupiter.api.Test
import org.kwicket.component.TestPanel
import org.kwicket.component.q
import org.kwicket.component.render
import org.kwicket.model.ldm
import org.kwicket.model.model

class WebMarkupContainerNullableModelFactoryTest : AbstractFactoryTest<WebMarkupContainer, String?>() {

    override val model: IModel<String?> = "test container".model()

    override fun factoryCreate(
        id: String,
        model: IModel<String?>?,
        markupId: String?,
        outputMarkupId: Boolean?,
        outputMarkupPlaceholderTag: Boolean?,
        visible: Boolean?,
        visibilityAllowed: Boolean?,
        enabled: Boolean?,
        escapeModelStrings: Boolean?,
        renderBodyOnly: Boolean?,
        behavior: Behavior?,
        behaviors: List<Behavior>?,
        onConfig: (WebMarkupContainer.() -> Unit)?,
        postInit: (WebMarkupContainer.() -> Unit)?
    ) = webMarkupContainerFactory(
        id = id,
        model = model,
        markupId = markupId,
        outputMarkupId = outputMarkupId,
        outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
        visible = visible,
        enabled = enabled,
        visibilityAllowed = visibilityAllowed,
        escapeModelStrings = escapeModelStrings,
        renderBodyOnly = renderBodyOnly,
        behavior = behavior,
        behaviors = behaviors,
        onConfig = onConfig,
        postInit = postInit
    )

    @Test
    fun `no model test`() {
        val panel = TestPanel(id = panelWicketId, markup = componentsTestMarkup(id = "comp")) { //}, body = {
            q(webMarkupContainerFactory(id = "comp"))
        } //)
        tester.render(panel) {
            assertModelValue("panel:comp", null)
        }
    }

}

class WebMarkupContainerUnitModelFactoryTest : AbstractFactoryTest<WebMarkupContainer, Unit>() {

    override val model: IModel<Unit> = { Unit }.ldm()

    override fun factoryCreate(
        id: String,
        model: IModel<Unit>?,
        markupId: String?,
        outputMarkupId: Boolean?,
        outputMarkupPlaceholderTag: Boolean?,
        visible: Boolean?,
        visibilityAllowed: Boolean?,
        enabled: Boolean?,
        escapeModelStrings: Boolean?,
        renderBodyOnly: Boolean?,
        behavior: Behavior?,
        behaviors: List<Behavior>?,
        onConfig: (WebMarkupContainer.() -> Unit)?,
        postInit: (WebMarkupContainer.() -> Unit)?
    ) = webMarkupContainerFactory(
        id = id,
        model = model,
        markupId = markupId,
        outputMarkupId = outputMarkupId,
        outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
        visible = visible,
        enabled = enabled,
        visibilityAllowed = visibilityAllowed,
        escapeModelStrings = escapeModelStrings,
        renderBodyOnly = renderBodyOnly,
        behavior = behavior,
        behaviors = behaviors,
        onConfig = onConfig,
        postInit = postInit
    )

}