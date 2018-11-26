package org.kwicket.resource

import org.apache.wicket.markup.head.CssHeaderItem
import org.apache.wicket.request.cycle.RequestCycle
import org.apache.wicket.request.handler.resource.ResourceReferenceRequestHandler
import org.apache.wicket.request.resource.PackageResourceReference
import org.apache.wicket.request.resource.ResourceReference
import kotlin.reflect.KClass

fun KClass<*>.resRef(name: String): PackageResourceReference = PackageResourceReference(java, name)

fun ResourceReference.cssHeaderItem() = CssHeaderItem.forReference(this)

fun ResourceReference.toUrl() = RequestCycle.get()
    .urlFor(ResourceReferenceRequestHandler(this)).toString()