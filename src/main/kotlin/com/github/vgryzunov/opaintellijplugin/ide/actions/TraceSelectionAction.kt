/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */
package com.github.vgryzunov.opaintellijplugin.ide.actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.DumbAwareAction
import com.github.vgryzunov.opaintellijplugin.opa.tool.OpaActions


class TraceSelectionAction : DumbAwareAction() {
    override fun update(e: AnActionEvent) {
        super.update(e)
        e.presentation.isEnabledAndVisible = getProjectAndDocument(e) != null
    }

    override fun actionPerformed(e: AnActionEvent) {
        val (project, document) = getProjectAndDocument(e) ?: return
        val editor = getEditor(e) ?: return
        OpaActions().traceSelection(project, document, editor)
    }
}
