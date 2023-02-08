package com.github.vgryzunov.opaintellijplugin.facet


/**
 * Class to store state of [RegoFacet].
 */
class RegoFacetState internal constructor() {
    private var myPathToSdk: String? = null

    var regoFacetState: String
        get() = myPathToSdk!!
        set(newPath) {
            myPathToSdk = newPath
        }
    init {
        this.regoFacetState = DEMO_FACET_INIT_PATH
    }

    companion object {
        const val DEMO_FACET_INIT_PATH = ""
    }
}
