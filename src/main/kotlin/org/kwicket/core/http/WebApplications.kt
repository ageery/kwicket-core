package org.kwicket.core.http

import org.apache.wicket.Page
import org.apache.wicket.core.request.mapper.MountedMapper
import org.apache.wicket.protocol.http.WebApplication
import org.apache.wicket.spring.injection.annot.SpringComponentInjector
import kotlin.reflect.KClass

/**
 * Extension method method for mounting Wicket pages using the Kotlin [KClass] rather than [Class].
 *
 * @param P type of the [page]
 * @receiver Wicket [WebApplication] in which the page is to be mounted
 * @param path path portion of the URL at which the page is available
 * @param page class of the page to be mounted
 * @return [MountedMapper] encapsulating the mounted page
 */
fun <P : Page> WebApplication.mountPage(path: String, page: KClass<P>): MountedMapper = mountPage(path, page.java)

fun <A : WebApplication> A.enableSpringIoC(): A = this.also {
    componentInstantiationListeners.add(SpringComponentInjector(it))
}
