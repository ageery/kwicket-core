package org.kwicket.component.wrapper

import org.apache.wicket.Component
import org.apache.wicket.behavior.AttributeAppender
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.model.IModel
import org.apache.wicket.util.tester.WicketTester
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.kwicket.component.ComponentTestType
import org.kwicket.component.TestApp
import org.kwicket.component.TestPanel
import org.kwicket.component.q
import org.kwicket.component.render

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class AbstractWrapperTest<C : Component, M> {

    protected val panelWicketId = "panel"
    private val compWicketId = "comp"
    private val compPath = "$panelWicketId:$compWicketId"
    private val labelNeedingEscapingText = "<1"
    private val escapedLabelText = "&lt;1"
    private val explicitMarkupId = "kwicketTestId"

    private val attr1Name = "data-kwicket-test"
    private val attr1Value = "attr1-testing"
    private val attr2Value = "attr2-testing"

    private val behavior1 = AttributeAppender(attr1Name, attr1Value, " ")
    private val behavior2 = AttributeAppender(attr1Name, attr2Value, " ")

    protected val tester: WicketTester = WicketTester(TestApp())

    protected open val tagName: String = "span"
    protected open fun componentsTestMarkup(id: String) = """<$tagName wicket:id="$id">[COMPONENT]</$tagName>"""

    protected open fun Panel.queueAdditional() {}

    protected abstract val model: IModel<M>

    private fun createTestPanel(type: ComponentTestType): Panel =
        object : TestPanel(id = panelWicketId, markup = componentsTestMarkup(id = compWicketId)) {
            init {
                queueAdditional()
                q(when (type) {
                    ComponentTestType.Enabled -> createComponent(id = compWicketId, model = model, enabled = true)
                    ComponentTestType.Disabled -> createComponent(id = compWicketId, model = model, enabled = false)
                    ComponentTestType.Invisible -> createComponent(id = compWicketId, model = model, visible = false)
                    ComponentTestType.Visible -> createComponent(id = compWicketId, model = model, visible = true)
                    ComponentTestType.NonEscapedModelStrings -> createComponent(id = compWicketId, model = model, escapeModelStrings = false)
                    ComponentTestType.EscapedModelStrings -> createComponent(id = compWicketId, model = model, escapeModelStrings = true)
                    ComponentTestType.RenderBodyOnly -> createComponent(id = compWicketId, model = model, renderBodyOnly = true)
                    ComponentTestType.RenderEnclosingTag -> createComponent(id = compWicketId, model = model, renderBodyOnly = false)
                    ComponentTestType.ExplicitMarkupId -> createComponent(id = compWicketId, model = model, markupId = explicitMarkupId)
                    ComponentTestType.ImplicitMarkupId -> createComponent(id = compWicketId, model = model, outputMarkupId = true)
                    ComponentTestType.NoMarkupId -> createComponent(id = compWicketId, model = model, outputMarkupId = false)
                    ComponentTestType.PlaceholderTag -> createComponent(id = compWicketId, model = model, outputMarkupPlaceholderTag = true)
                    ComponentTestType.InvisiblePlaceholderTag -> createComponent(
                        id = compWicketId, model = model,
                        outputMarkupPlaceholderTag = true, visible = false
                    )
                    ComponentTestType.SingleBehavior -> createComponent(id = compWicketId, model = model, behavior = behavior1)
                    ComponentTestType.OneBehaviors -> createComponent(id = compWicketId, model = model, behaviors = listOf(behavior1))
                    ComponentTestType.MultiBehaviors -> createComponent(
                        id = compWicketId, model = model,
                        behaviors = listOf(behavior1, behavior2)
                    )
                    ComponentTestType.BehaviorWithBehaviors -> createComponent(
                        id = compWicketId, model = model,
                        behavior = behavior1,
                        behaviors = listOf(behavior2)
                    )
                    ComponentTestType.OnConfigInvisible -> createComponent(
                        id = compWicketId, model = model,
                        onConfig = { isVisible = false }
                    )
                    ComponentTestType.VisibilityAllowed -> createComponent(id = compWicketId, model = model, visibilityAllowed = true)
                    ComponentTestType.VisibilityNotAllowed -> createComponent(id = compWicketId, model = model, visibilityAllowed = false)
                    ComponentTestType.InvisibleByBlock -> createComponent(id = compWicketId, model = model, block = { isVisible = false })
                })
            }
        }

    private fun check(type: ComponentTestType, comp: Component) {
        when (type) {
            ComponentTestType.Disabled -> assertFalse(comp.isEnabled)
            ComponentTestType.Enabled -> assertTrue(comp.isEnabled)
            ComponentTestType.Invisible -> assertFalse(comp.isVisible)
            ComponentTestType.Visible -> assertTrue(comp.isVisible)
            ComponentTestType.NonEscapedModelStrings -> assertFalse(comp.escapeModelStrings)
            ComponentTestType.EscapedModelStrings -> assertTrue(comp.escapeModelStrings)
            ComponentTestType.RenderBodyOnly -> assertTrue(comp.renderBodyOnly)
            ComponentTestType.RenderEnclosingTag -> assertFalse(comp.renderBodyOnly)
            ComponentTestType.ExplicitMarkupId -> assertEquals(explicitMarkupId, comp.markupId)
            ComponentTestType.ImplicitMarkupId -> assertTrue(comp.outputMarkupId)
            ComponentTestType.NoMarkupId -> assertFalse(comp.outputMarkupId)
            ComponentTestType.PlaceholderTag -> assertTrue(comp.outputMarkupPlaceholderTag)
            ComponentTestType.InvisiblePlaceholderTag -> {
                assertTrue(comp.outputMarkupPlaceholderTag)
                assertFalse(comp.isVisible)
            }
            ComponentTestType.SingleBehavior -> {
                assertEquals(1, comp.behaviors.size)
                assertEquals(behavior1, comp.behaviors[0])
            }
            ComponentTestType.OneBehaviors -> {
                assertEquals(1, comp.behaviors.size)
                assertEquals(behavior1, comp.behaviors[0])
            }
            ComponentTestType.MultiBehaviors -> {
                assertEquals(2, comp.behaviors.size)
                assertEquals(behavior1, comp.behaviors[0])
                assertEquals(behavior2, comp.behaviors[1])
            }
            ComponentTestType.BehaviorWithBehaviors -> {
                assertEquals(2, comp.behaviors.size)
                assertEquals(behavior1, comp.behaviors[0])
                assertEquals(behavior2, comp.behaviors[1])
            }
            ComponentTestType.OnConfigInvisible -> tester.assertInvisible(compPath)
            ComponentTestType.VisibilityAllowed -> {
                tester.assertVisible(compPath)
                assertTrue(comp.isVisibilityAllowed)
            }
            ComponentTestType.VisibilityNotAllowed -> {
                tester.assertInvisible(compPath)
                assertFalse(comp.isVisibleInHierarchy)
                assertFalse(comp.isVisibilityAllowed)
            }
            ComponentTestType.InvisibleByBlock -> assertFalse(comp.isVisible)
        }
    }

    abstract fun createComponent(
        id: String,
        model: IModel<M>? = null,
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
        onConfig: (C.() -> Unit)? = null,
        block: (C.() -> Unit)? = null
    ): C

    open val pathToComponent: String = compWicketId

    @ParameterizedTest
    @EnumSource(ComponentTestType::class)
    fun testComponent(type: ComponentTestType) {
        val panel = createTestPanel(type)
        tester.render(panel) {
            val comp = panel.get(pathToComponent)
            check(type, comp)
        }
    }

}