/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package com.github.vgryzunov.opaintellijplugin.module

import com.github.vgryzunov.opaintellijplugin.module.sdk.RegoSdkType
import com.intellij.codeInsight.daemon.ProjectSdkSetupValidator
import com.intellij.openapi.project.Project
import com.intellij.openapi.projectRoots.ProjectJdkTable
import com.intellij.openapi.roots.ui.configuration.SdkPopupFactory
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.ui.EditorNotificationPanel

class RegoProjectSdkSetupValidator: ProjectSdkSetupValidator {
    override fun isApplicableFor(project: Project, file: VirtualFile): Boolean {
        return file.exists()
    }

    override fun getErrorMessage(project: Project, file: VirtualFile): String? {
        if (ProjectJdkTable.getInstance().getSdksOfType(RegoSdkType.getInstance()).isEmpty()) {
            return "OPA is not configured for this project!"
        }
        return null
    }

    override fun getFixHandler(project: Project, file: VirtualFile):
            EditorNotificationPanel.ActionHandler {
        return SdkPopupFactory.newBuilder()
            .withProject(project)
            .withSdkTypeFilter { it is RegoSdkType }
            .updateSdkForFile(file)
            .buildEditorNotificationPanelHandler()
    }
}
