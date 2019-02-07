package org.kwicket.core.resource

import org.apache.wicket.markup.head.CssHeaderItem
import org.apache.wicket.markup.head.HeaderItem
import org.apache.wicket.markup.head.JavaScriptHeaderItem
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem
import org.apache.wicket.protocol.ws.api.WicketWebSocketJQueryResourceReference
import org.apache.wicket.request.Url
import org.apache.wicket.request.cycle.RequestCycle
import org.apache.wicket.request.handler.resource.ResourceReferenceRequestHandler
import org.apache.wicket.request.resource.JavaScriptResourceReference
import org.apache.wicket.request.resource.PackageResourceReference
import org.apache.wicket.request.resource.ResourceReference
import org.apache.wicket.request.resource.UrlResourceReference
import kotlin.reflect.KClass

fun KClass<*>.resRef(name: String): PackageResourceReference = PackageResourceReference(java, name)

fun KClass<*>.jsResRef(name: String, deps: List<HeaderItem>?): JavaScriptResourceReference =
    if (deps.isNullOrEmpty()) JavaScriptResourceReference(java, name)
    else object : JavaScriptResourceReference(java, name) {
        override fun getDependencies(): List<HeaderItem> = deps.toList()
    }

fun KClass<*>.jsHeaderItem(name: String, deps: List<HeaderItem>): JavaScriptReferenceHeaderItem =
    JavaScriptHeaderItem.forReference(this.jsResRef(name = name, deps = deps))

fun ResourceReference.cssHeaderItem() = CssHeaderItem.forReference(this)

fun <T: ResourceReference> T.jsHeaderItem() = JavaScriptHeaderItem.forReference(this)

fun ResourceReference.toUrl() = RequestCycle.get()
    .urlFor(ResourceReferenceRequestHandler(this)).toString()

val wsJQueryResRef: WicketWebSocketJQueryResourceReference
    get() = WicketWebSocketJQueryResourceReference.get()

fun wsJQueryHeaderItem() = JavaScriptHeaderItem.forReference(wsJQueryResRef)

fun String.urlResRef() = UrlResourceReference(Url.parse(this))

fun String.urlJsHeaderItem() = JavaScriptHeaderItem.forReference(this.urlResRef())

fun String.scriptHeaderItem(id: String) = JavaScriptHeaderItem.forScript(this, id)