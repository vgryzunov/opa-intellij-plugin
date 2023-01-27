package com.github.vgryzunov.opaintellijplugin.ide.runconfig.test

import com.github.vgryzunov.opaintellijplugin.ide.runconfig.OpaConfigurationFactory
import com.github.vgryzunov.opaintellijplugin.lang.RegoIcons
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.ConfigurationType
import com.intellij.execution.configurations.ConfigurationTypeUtil
import javax.swing.Icon

class OpaTestRunConfigurationType : ConfigurationType {
    override fun getDisplayName(): String = "Opa test"

    override fun getConfigurationTypeDescription(): String = "Opa test"

    override fun getIcon(): Icon = RegoIcons.OPA

    override fun getId(): String = "OPA_TEST_RUN_CONFIGURATION"

    override fun getConfigurationFactories(): Array<ConfigurationFactory> = arrayOf(OpaConfigurationFactory(this))

    companion object {
        fun getInstance(): OpaTestRunConfigurationType =
            ConfigurationTypeUtil.findConfigurationType(OpaTestRunConfigurationType::class.java)
    }
}
