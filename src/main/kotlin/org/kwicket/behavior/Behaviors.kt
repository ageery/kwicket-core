package org.kwicket.behavior

import org.apache.wicket.markup.html.form.FormComponentUpdatingBehavior

fun onFormComponentUpdate(block: FormComponentUpdatingBehavior.() -> Unit) = object : FormComponentUpdatingBehavior() {

    override fun onUpdate() {
        block.invoke(this)
    }

}