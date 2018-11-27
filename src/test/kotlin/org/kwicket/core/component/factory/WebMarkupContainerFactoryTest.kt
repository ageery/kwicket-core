//package org.kwicket.core.component.factory
//
//import org.apache.wicket.behavior.Behavior
//import org.apache.wicket.markup.html.WebMarkupContainer
//import org.apache.wicket.model.IModel
//import org.junit.jupiter.api.Test
//import org.kwicket.core.component.TestPanel
//import org.kwicket.core.component.config.WebMarkupContainerConfig
//import org.kwicket.core.component.q
//import org.kwicket.core.component.render
//import org.kwicket.core.model.model
//
//class WebMarkupContainerNullableModelFactoryTest : AbstractFactoryTest<WebMarkupContainer, String?>() {
//
//    override val model: IModel<String?> = "test container".model()
//
//    override fun factoryCreate(
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
//        onConfig: (WebMarkupContainer.() -> Unit)?,
//        postInit: (WebMarkupContainer.() -> Unit)?
//    ) = webMarkupContainerFactory(
//        id = id,
//        config = WebMarkupContainerConfig(
//            model = model,
//            markupId = markupId,
//            outputMarkupId = outputMarkupId,
//            outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
//            isVisible = visible,
//            isEnabled = enabled,
//            isVisibilityAllowed = visibilityAllowed,
//            escapeModelStrings = escapeModelStrings,
//            renderBodyOnly = renderBodyOnly,
//            behavior = behavior,
//            behaviors = behaviors,
//            onConfig = onConfig,
//            postInit = postInit
//        )
//    )
//
//    @Test
//    fun `no model test`() {
//        val panel = TestPanel(id = panelWicketId, markup = componentsTestMarkup(id = "comp")) {
//            q(webMarkupContainerFactory<Unit>(id = "comp"))
//        }
//        tester.render(panel) {
//            assertModelValue("panel:comp", null)
//        }
//    }
//
//}