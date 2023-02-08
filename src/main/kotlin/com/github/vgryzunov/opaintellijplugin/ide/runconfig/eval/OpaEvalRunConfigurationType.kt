package com.github.vgryzunov.opaintellijplugin.ide.runconfig.eval

import com.github.vgryzunov.opaintellijplugin.ide.runconfig.OpaConfigurationFactory
import com.github.vgryzunov.opaintellijplugin.lang.RegoIcons
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.ConfigurationType
import com.intellij.execution.configurations.ConfigurationTypeUtil
import javax.swing.Icon

class OpaEvalRunConfigurationType : ConfigurationType {

    companion object {
        fun getInstance(): OpaEvalRunConfigurationType =
            ConfigurationTypeUtil.findConfigurationType(OpaEvalRunConfigurationType::class.java)
    }

    override fun getDisplayName(): String = "Opa eval"

    override fun getConfigurationTypeDescription(): String = "Opa eval"

    override fun getIcon(): Icon = RegoIcons.OPA

    override fun getId(): String = "OPA_RUN_CONFIGURATION"

    override fun getConfigurationFactories(): Array<ConfigurationFactory> = arrayOf(OpaConfigurationFactory(this))

}
