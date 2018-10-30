package org.kwicket

/**
 * Returns true if any of the [args] are not null.
 *
 * @param args array of elements
 * @return returns true if any of the [args] are not null null; otherwise, returns false
 */
internal fun hasNonNull(vararg args: Any?) = args.filterNotNull().isNotEmpty()
