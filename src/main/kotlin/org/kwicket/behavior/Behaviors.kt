package org.kwicket.behavior

import org.apache.wicket.Component
import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.FormComponentUpdatingBehavior

// FIXME: can we use more descriptive names for these methods?

fun onFormComponentUpdate(isStatelessHint: Boolean? = null, block: FormComponentUpdatingBehavior.() -> Unit) = object : FormComponentUpdatingBehavior() {

    override fun onUpdate() {
        block.invoke(this)
    }

    override fun getStatelessHint(component: Component): Boolean = isStatelessHint ?: super.getStatelessHint(component)

}

fun onAjaxFormComponentUpdate(event: String, isStatelessHint: Boolean? = null, block: AjaxFormComponentUpdatingBehavior.(AjaxRequestTarget) -> Unit) = object : AjaxFormComponentUpdatingBehavior(event) {

    override fun onUpdate(target: AjaxRequestTarget) {
        block.invoke(this, target)
    }

    override fun getStatelessHint(component: Component): Boolean = isStatelessHint ?: super.getStatelessHint(component)

}

val hideWhenEmpty: Behavior = object : Behavior() {
    override fun onConfigure(component: Component) {
        if (component.isVisible) {
            val v = component.defaultModelObject
            component.isVisible = when (v) {
                is Collection<*> -> v.isNotEmpty()
                else -> v == null || component.defaultModelObjectAsString.isNullOrEmpty()
            }
        }
    }
}