package org.kwicket.core.http

import org.apache.wicket.Page
import org.apache.wicket.bean.validation.BeanValidationConfiguration
import org.apache.wicket.bean.validation.IViolationTranslator
import org.apache.wicket.core.request.mapper.MountedMapper
import org.apache.wicket.core.request.mapper.PackageMapper
import org.apache.wicket.guice.GuiceComponentInjector
import org.apache.wicket.protocol.http.WebApplication
import org.apache.wicket.spring.injection.annot.SpringComponentInjector
import javax.validation.Validator
import kotlin.reflect.KClass

/**
 * Extension method method for mounting Wicket pages using the Kotlin [KClass] rather than [Class].
 *
 * @receiver Wicket [WebApplication] in which the page is to be mounted
 * @param path path portion of the URL at which the page is available
 * @param page class of the page to be mounted
 * @return [MountedMapper] encapsulating the mounted page
 */
fun WebApplication.mountPage(path: String, page: KClass<out Page>): MountedMapper = mountPage(path, page.java)

/**
 * Extension method method for mounting all of the classes in a package using the Kotlin [KClass] rather than [Class].
 *
 * @receiver Wicket [WebApplication] in which the page is to be mounted
 * @param path path portion of the URL at which the page is available
 * @param page class of the page in the package to be mounted
 * @return [PackageMapper] encapsulating the mounted package
 */
fun WebApplication.mountPackage(path: String, page: KClass<out Page>): PackageMapper = mountPackage(path, page.java)

fun <A : WebApplication> A.enableSpringIoC(): A = this.also {
    componentInstantiationListeners.add(SpringComponentInjector(it))
}

fun <A : WebApplication> A.enableGuiceIoc(): A = this.also {
    componentInstantiationListeners.add(GuiceComponentInjector(it))
}

fun <A : WebApplication> A.enableBeanValidation(
    violationTranslator: IViolationTranslator? = null,
    validationProvider: (() -> Validator)? = null
): A = this.also {
    val conf = BeanValidationConfiguration()
    violationTranslator?.let { conf.violationTranslator = it }
    validationProvider?.let { conf.setValidatorProvider(it) }
    conf.configure(this)
}
