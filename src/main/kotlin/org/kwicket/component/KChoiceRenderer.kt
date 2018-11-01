package org.kwicket.component

import org.apache.wicket.markup.html.form.IChoiceRenderer
import org.apache.wicket.model.IModel

class KChoiceRenderer<T>(
    private val toObj: (String, IModel<out List<T>>) -> T,
    private val toDisplayObj: (T) -> Any = { it.toString() },
    private val toId: (T, Int) -> String
) : IChoiceRenderer<T> {
    override fun getObject(id: String, choices: IModel<out MutableList<out T>>): T = toObj(id, choices)
    override fun getDisplayValue(obj: T): Any = toDisplayObj(obj)
    override fun getIdValue(obj: T, index: Int): String = toId(obj, index)
}