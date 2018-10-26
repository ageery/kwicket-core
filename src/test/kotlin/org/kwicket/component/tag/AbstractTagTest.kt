package org.kwicket.component.tag

import org.junit.jupiter.api.Test
import org.kwicket.component.AbstractWicketTest
import org.kwicket.component.render
import org.kwicket.model.model

class AbstractTagTest : AbstractWicketTest() {

    @Test
    fun f() {
        val panel = wicket().panel {
            label(model = "test".model())
        }.build("panel")
        tester.render(panel) {
            assertContains("test")
        }
    }

}