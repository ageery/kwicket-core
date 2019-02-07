package org.kwicket.core.template

import org.apache.wicket.util.template.PackageTextTemplate
import kotlin.reflect.KClass

fun KClass<*>.packageTextTemplate(fileName: String) = PackageTextTemplate(this.java, fileName)
fun KClass<*>.renderText(fileName: String, vars: Map<String, *>) = this.packageTextTemplate(fileName).asString(vars)