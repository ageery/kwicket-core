package org.kwicket.core

import org.apache.wicket.SharedResources
import org.apache.wicket.request.IRequestParameters
import org.apache.wicket.request.mapper.parameter.PageParameters
import org.apache.wicket.request.resource.IResource
import org.apache.wicket.util.lang.Bytes
import org.apache.wicket.util.string.StringValue

internal const val wicketNamespacePrefix = "wicket"
internal const val wicketIdAttr = "$wicketNamespacePrefix:id"

operator fun SharedResources.set(name: String, resource: IResource) = this.add(name, resource)

interface FileSize {
    val value: Long
    val bytes: Bytes
}

inline class Kilobyte(override val value: Long) : FileSize {
    override val bytes
        get() = Bytes.kilobytes(value)
}

fun Map<String, *>.toParams() = PageParameters().apply {
    this@toParams.entries.forEach { add(it.key, it.value) }
}

@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
operator fun PageParameters.get(key: String): StringValue = get(key)

operator fun PageParameters.get(key: String, defaultValue: String? = null): String? = get(key).toString(defaultValue)

operator fun IRequestParameters.get(name: String): StringValue =
    getParameterValue(name)

operator fun IRequestParameters.get(name: String, defaultValue: String? = null): String? =
    this[name].toString(defaultValue)
