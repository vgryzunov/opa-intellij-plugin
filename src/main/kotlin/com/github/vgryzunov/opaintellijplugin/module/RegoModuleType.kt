/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package com.github.vgryzunov.opaintellijplugin.module

import com.intellij.openapi.module.ModuleType
import com.intellij.openapi.module.ModuleTypeManager
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
        return "Rego module"
    }

    override fun getNodeIcon(isOpened: Boolean): Icon {
        return RegoIcons.OPA
    }

    companion object {
        private const val ID = "REGO_MODULE"
        val INSTANCE: RegoModuleType by lazy {
            ModuleTypeManager.getInstance().findByID(
                ID
            ) as RegoModuleType
        }
    }


}
