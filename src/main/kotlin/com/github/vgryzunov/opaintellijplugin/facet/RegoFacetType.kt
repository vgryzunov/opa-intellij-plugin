package com.github.vgryzunov.opaintellijplugin.facet

import com.github.vgryzunov.opaintellijplugin.lang.RegoIcons
import com.intellij.facet.Facet
import com.intellij.facet.FacetType
import com.intellij.facet.FacetTypeId
import com.intellij.openapi.module.Module
import com.intellij.openapi.module.ModuleType
import javax.swing.Icon

class RegoFacetType : FacetType<RegoFacet?, RegoFacetConfiguration?>(REGO_FACET_TYPE_ID, FACET_ID, FACET_NAME) {
    companion object {
        const val FACET_ID = "REGO_FACET_ID"
        const val FACET_NAME = "OPA"
        val REGO_FACET_TYPE_ID = FacetTypeId<RegoFacet?>(FACET_ID)
    }
    override fun createDefaultConfiguration(): RegoFacetConfiguration {
        return RegoFacetConfiguration()
    }

    override fun createFacet(
        module: Module,
        s: String,
        configuration: RegoFacetConfiguration,
        facet: Facet<*>?
    ): RegoFacet {
        return RegoFacet(this, module, s, configuration, facet)
    }

    override fun isSuitableModuleType(type: ModuleType<*>?): Boolean {
        return true
    }

    override fun getIcon(): Icon? {
        return RegoIcons.OPA
    }


}
