/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package com.github.vgryzunov.opaintellijplugin.facet

import com.intellij.facet.FacetConfiguration
import com.intellij.facet.ui.FacetEditorContext
import com.intellij.facet.ui.FacetEditorTab
import com.intellij.facet.ui.FacetValidatorsManager

class RegoFacetConfiguration : FacetConfiguration {

    private var myFacetState: RegoFacetState = RegoFacetState()

    fun getState(): RegoFacetState? {
        return myFacetState
    }

    fun loadState(state: RegoFacetState) {
        myFacetState = state
    }

    override fun createEditorTabs(
        context: FacetEditorContext,
        manager: FacetValidatorsManager
    ): Array<FacetEditorTab> {
        return arrayOf(
            RegoFacetEditorTab(myFacetState, context, manager)
        )
    }
}
