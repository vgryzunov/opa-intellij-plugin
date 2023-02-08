/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package com.github.vgryzunov.opaintellijplugin.module

import com.intellij.ide.util.projectWizard.ModuleWizardStep
import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.openapi.module.ModuleType
import com.intellij.openapi.module.ModuleTypeManager
import com.intellij.openapi.roots.ui.configuration.ModulesProvider
import com.github.vgryzunov.opaintellijplugin.lang.RegoIcons
import javax.swing.Icon

class RegoModuleType : ModuleType<RegoModuleBuilder>("REGO_MODULE") {
    override fun createModuleBuilder(): RegoModuleBuilder {
        return RegoModuleBuilder()
    }

    override fun getName(): String {
        return "Rego"
    }

    override fun getDescription(): String {
        return "Rego Module"
    }

    override fun getNodeIcon(isOpened: Boolean): Icon {
        return RegoIcons.OPA
    }

    override fun createWizardSteps(wizardContext: WizardContext, moduleBuilder: RegoModuleBuilder, modulesProvider: ModulesProvider): Array<ModuleWizardStep> {
        return super.createWizardSteps(wizardContext, moduleBuilder, modulesProvider)
    }

    companion object {
        private const val ID = "REGO_MODULE"
        val INSTANCE: RegoModuleType by lazy { ModuleTypeManager.getInstance().findByID(
            ID
        ) as RegoModuleType
        }
    }


}
