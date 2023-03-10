/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package com.github.vgryzunov.opaintellijplugin.ide.runconfig

import com.github.vgryzunov.opaintellijplugin.ide.runconfig.eval.OpaEvalRunConfiguration
import com.github.vgryzunov.opaintellijplugin.ide.runconfig.eval.OpaEvalRunConfigurationType
import com.github.vgryzunov.opaintellijplugin.ide.runconfig.test.OpaTestRunConfiguration
import com.github.vgryzunov.opaintellijplugin.ide.runconfig.test.OpaTestRunConfigurationType
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.ConfigurationType
import com.intellij.openapi.project.Project

/**
 * Create RunConfiguration from ConfigurationType
 *
 * @link https://www.jetbrains.org/intellij/sdk/docs/basics/run_configurations/run_configuration_management.html#configuration-factory
 */
class OpaConfigurationFactory(type: ConfigurationType) : ConfigurationFactory(type) {
    override fun createTemplateConfiguration(project: Project) = when (type) {
        is OpaEvalRunConfigurationType -> OpaEvalRunConfiguration(project, this, "Opa eval")
        is OpaTestRunConfigurationType -> OpaTestRunConfiguration(project, this, "Opa test")
        else -> throw IllegalArgumentException("No Opa run configuration type, but ${type.id} was received instead.")
    }

    override fun getName(): String {
        return "Opa configuration factory"
    }

    /**
     * Returns the id of the run configuration that is used for serialization. For compatibility reason the default
     * implementation calls the method {@link #getName()} and this may cause problems if {@link #getName} returns
     * localized value.
     *
     * So we override it to avoid any localization problem and keep the same value to avoid compatibility issue
     */
    override fun getId(): String {
        return "Opa configuration factory"
    }
}
