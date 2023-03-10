/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package com.github.vgryzunov.opaintellijplugin.facet

import com.intellij.facet.Facet
import com.intellij.openapi.module.Module
import com.intellij.openapi.util.NlsSafe

class RegoFacet(
    facetType: RegoFacetType,
    module: Module,
    name: @NlsSafe String,
    configuration: RegoFacetConfiguration,
    underlyingFacet: Facet<*>?
) : Facet<RegoFacetConfiguration>(facetType, module, name, configuration, underlyingFacet)
