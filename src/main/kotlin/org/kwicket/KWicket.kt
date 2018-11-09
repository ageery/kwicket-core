package org.kwicket

import org.apache.wicket.SharedResources
import org.apache.wicket.request.resource.IResource

internal const val wicketNamespacePrefix = "wicket"
internal const val wicketIdAttr = "$wicketNamespacePrefix:id"

operator fun SharedResources.set(name: String, resource: IResource) = this.add(name, resource)