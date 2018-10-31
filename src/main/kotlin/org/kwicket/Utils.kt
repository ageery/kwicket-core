package org.kwicket

import kotlin.reflect.KClass

/**
 * Returns true if any of the [args] are not null.
 *
 * @param args array of elements
 * @return returns true if any of the [args] are not null null; otherwise, returns false
 */
internal fun hasNonNull(vararg args: Any?) = args.filterNotNull().isNotEmpty()

internal fun <T: Any> KClass<T>?.toJavaType(isRequired: Boolean?) =
    this?.let { if (isRequired != null && isRequired) it.java else it.javaObjectType }
