package org.kwicket.http

import org.apache.wicket.protocol.http.WebApplication
import org.apache.wicket.protocol.http.WicketFilter

/**
 * [WicketFilter] with named parameters.
 *
 * @param webApp Wicket [WebApplication] connected to the filter
 * @param filterPath optional URL path to the application (defaults to `/`)
 */
open class KWicketFilter(webApp: WebApplication, filterPath: String = "/") : WicketFilter(webApp) {

    init {
        this.filterPath = filterPath
    }

}