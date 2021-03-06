package org.kwicket.core.component

import org.apache.wicket.markup.html.form.IChoiceRenderer
import org.apache.wicket.model.IModel
import org.kwicket.core.model.obj

class KChoiceRenderer<T>(
    // FIXME: getting a NPE in the first lambda -- it's almost like there isn't a this obj to call toId on
    private val toObj: String.(IModel<out List<T>>) -> T = {
        it.obj.withIndex().first { (index, obj ) -> this == toId.invoke(obj, index) }.value
    },
    private val toDisplayObj: (T) -> Any = { it.toString() },
    private val toId: T.(Int) -> String = { it.toString() }
) : IChoiceRenderer<T> {
    override fun getObject(id: String, choices: IModel<out MutableList<out T>>): T = toObj(id, choices)
    override fun getDisplayValue(obj: T): Any = toDisplayObj(obj)
    override fun getIdValue(obj: T, index: Int): String = toId(obj, index)
}