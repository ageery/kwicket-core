package org.kwicket.model

import org.apache.wicket.model.IModel
import org.apache.wicket.model.Model
import org.apache.wicket.model.PropertyModel
import org.apache.wicket.model.util.ListModel
import java.io.Serializable
import kotlin.reflect.KProperty1

/**
 * Returns the [Serializable] object wrapped in a model.
 *
 * To create a model with a nullable object, specify the type the variable definition:
 *
 * ```
 * val nameModel: IModel<String?> = null.model()
 * ```
 *
 * @param T type of the @receiver
 * @receiver nullable [Serializable] object to wrap in a model
 */
fun <T : Serializable?> T.model(): IModel<T> = Model.of(this)

/**
 * Returns the [List] wrapped in a model.
 *
 * @param T type of the items in the list
 * @param L type of the list
 * @receiver [List] of type [T]
 * @return model of the @receiver [List]
 */
fun <T, L: List<T>> L.listModel(): IModel<List<T>> = ListModel(this)

/**
 * Read/Write extension property alias for [IModel.getObject].
 *
 * @param T type of the model
 * @receiver model of type [T]
 */
var <T> IModel<T>.obj: T
    get() = `object`
    set(value) {
        `object` = value
    }

/**
 * [PropertyModel] created from a Kotlin property reference where the object of the `parent` model is _nullable_.
 *
 * @param C type of the (nullable) object of the `parent` model and the type of the class the property is associated with
 * @param T type of the property
 * @param parent the model whose object the property is being resolved against
 * @param property the property reference containing the name of the property
 */
private class KPropertyModelObjNullable<C, T>(parent: IModel<C?>, property: KProperty1<C, T>) :
    PropertyModel<T?>(parent, property.name)

/**
 * [PropertyModel] created from a Kotlin property reference where the object of the `parent` model is _not_ nullable.
 *
 * @param C type of the (not nullable) object of the `parent` model and the type of the class the property is associated with
 * @param T type of the property
 * @param parent the model whose object the property is being resolved against
 * @param property the property reference containing the name of the property
 */
private class KPropertyModel<C, T>(parent: IModel<C>, property: KProperty1<C, T>) :
    PropertyModel<T>(parent, property.name)

/**
 * Returns a PropertyModel<T?> constructed from the model with the [property] applied to it.
 *
 * @param C type of the (nullable) model object of the @receiver and the type of the class the property is associated with
 * @param T type of the property
 * @param property the property reference containing the name of the property
 * @receiver the model the [property] is being applied to
 * @return PropertyModel<T?> property model constructed from the @receiver and the [property] info
 */
operator fun <C, T> IModel<C?>.plus(property: KProperty1<C, T>): PropertyModel<T?> =
    KPropertyModelObjNullable(this, property)

/**
 * Returns an IModel<T> constructed from the model with the [property] applied to it.
 *
 * @param C type of the (non nullable) model object of the @receiver and the type of the class the property is associated with
 * @param T type of the property
 * @param property the property reference containing the name of the property
 * @receiver the model the [property] is being applied to
 * @return IModel<T> constructed from the @receiver and the [property] info
 */
operator fun <C, T> IModel<C>.plus(property: KProperty1<C, T>): IModel<T> =
    KPropertyModel(this, property)