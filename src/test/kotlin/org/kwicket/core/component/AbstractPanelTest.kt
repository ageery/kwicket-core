package org.kwicket.core.component

import org.apache.wicket.Component
import org.apache.wicket.behavior.AttributeAppender
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.model.IModel
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

abstract class AbstractPanelTest<C : Component, M> : AbstractWicketTest() {

    protected val panelWicketId = "panel"
    protected val compWicketId = "comp"
    private val compPath = "$panelWicketId:$compWicketId"
    private val labelNeedingEscapingText = "<1"
    private val escapedLabelText = "&lt;1"
    private val explicitMarkupId = "kwicketTestId"

    private val attr1Name = "data-kwicket-test"
    private val attr1Value = "attr1-testing"
    private val attr2Value = "attr2-testing"

    private val behavior1 = AttributeAppender(attr1Name, attr1Value, " ")
    private val behavior2 = AttributeAppender(attr1Name, attr2Value, " ")

    protected open val tagName: String = "span"
    protected open fun componentsTestMarkup(id: String) = """<$tagName wicket:id="$id">[COMPONENT]</$tagName>"""

    protected open fun Panel.queueAdditional() {}
    protected abstract val model: IModel<M>
    protected open val baseBehaviorCount: Int = 0

    private fun createTestPanel(type: ComponentTestType): Panel =
        TestPanel(id = panelWicketId, markup = componentsTestMarkup(id = compWicketId), body = {
                queueAdditional()
                val c = when (type) {
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
                        id = compWicketId, model = model, outputMarkupPlaceholderTag = true, visible = false
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
                    ComponentTestType.OnConfigInvisible -> createComponent(id = compWicketId, model = model) {
                        isVisible = false
                    }
                    ComponentTestType.VisibilityAllowed -> createComponent(id = compWicketId, model = model, visibilityAllowed = true)
                    ComponentTestType.VisibilityNotAllowed -> createComponent(id = compWicketId, model = model, visibilityAllowed = false)
                    ComponentTestType.InvisibleByBlock -> createComponent(id = compWicketId, model = model, postInit = { isVisible = false })
                }
                queueComponent(c)
            })

    private fun check(type: ComponentTestType, comp: Component) {
        when (type) {
            ComponentTestType.Disabled -> Assertions.assertFalse(comp.isEnabled)
            ComponentTestType.Enabled -> Assertions.assertTrue(comp.isEnabled)
            ComponentTestType.Invisible -> Assertions.assertFalse(comp.isVisible)
            ComponentTestType.Visible -> Assertions.assertTrue(comp.isVisible)
            ComponentTestType.NonEscapedModelStrings -> Assertions.assertFalse(comp.escapeModelStrings)
            ComponentTestType.EscapedModelStrings -> Assertions.assertTrue(comp.escapeModelStrings)
            ComponentTestType.RenderBodyOnly -> Assertions.assertTrue(comp.renderBodyOnly)
            ComponentTestType.RenderEnclosingTag -> Assertions.assertFalse(comp.renderBodyOnly)
            ComponentTestType.ExplicitMarkupId -> Assertions.assertEquals(explicitMarkupId, comp.markupId)
            ComponentTestType.ImplicitMarkupId -> Assertions.assertTrue(comp.outputMarkupId)
            ComponentTestType.NoMarkupId -> Assertions.assertFalse(comp.outputMarkupId)
            ComponentTestType.PlaceholderTag -> Assertions.assertTrue(comp.outputMarkupPlaceholderTag)
            ComponentTestType.InvisiblePlaceholderTag -> {
                Assertions.assertTrue(comp.outputMarkupPlaceholderTag)
                Assertions.assertFalse(comp.isVisible)
            }
            ComponentTestType.SingleBehavior -> {
                Assertions.assertEquals(baseBehaviorCount + 1, comp.behaviors.size)
                Assertions.assertEquals(behavior1, comp.behaviors[0])
            }
            ComponentTestType.OneBehaviors -> {
                Assertions.assertEquals(baseBehaviorCount + 1, comp.behaviors.size)
                Assertions.assertEquals(behavior1, comp.behaviors[0])
            }
            ComponentTestType.MultiBehaviors -> {
                Assertions.assertEquals(baseBehaviorCount + 2, comp.behaviors.size)
                Assertions.assertEquals(behavior1, comp.behaviors[0])
                Assertions.assertEquals(behavior2, comp.behaviors[1])
            }
            ComponentTestType.BehaviorWithBehaviors -> {
                Assertions.assertEquals(baseBehaviorCount + 2, comp.behaviors.size)
                Assertions.assertEquals(behavior1, comp.behaviors[0])
                Assertions.assertEquals(behavior2, comp.behaviors[1])
            }
            ComponentTestType.OnConfigInvisible -> tester.assertInvisible("panel:$pathToComponent")
            ComponentTestType.VisibilityAllowed -> {
                tester.assertVisible("panel:$pathToComponent")
                Assertions.assertTrue(comp.isVisibilityAllowed)
            }
            ComponentTestType.VisibilityNotAllowed -> {
                tester.assertInvisible("panel:$pathToComponent")
                Assertions.assertFalse(comp.isVisibleInHierarchy)
                Assertions.assertFalse(comp.isVisibilityAllowed)
            }
            ComponentTestType.InvisibleByBlock -> Assertions.assertFalse(comp.isVisible)
        }
    }

    abstract fun Panel.createComponent(
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
        postInit: (C.() -> Unit)? = null
    ): C

    abstract fun Panel.queueComponent(comp: C)

    open val pathToComponent: String = compWicketId

    protected open val isNoMarkupIdValid: Boolean = true

    @ParameterizedTest
    @EnumSource(ComponentTestType::class)
    fun testComponent(type: ComponentTestType) {
        if (type != ComponentTestType.NoMarkupId || isNoMarkupIdValid) {
            val panel = createTestPanel(type)
            tester.render(panel) {
                val comp = panel.get(pathToComponent)
                check(type, comp)
            }
        }
    }

}