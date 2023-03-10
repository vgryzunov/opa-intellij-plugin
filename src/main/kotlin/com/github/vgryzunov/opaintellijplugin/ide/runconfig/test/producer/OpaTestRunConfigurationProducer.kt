/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */


package com.github.vgryzunov.opaintellijplugin.ide.runconfig.test.producer

import com.intellij.execution.actions.ConfigurationContext
import com.intellij.execution.actions.LazyRunConfigurationProducer
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.openapi.util.Ref
import com.intellij.psi.PsiElement
import com.github.vgryzunov.opaintellijplugin.ide.runconfig.OpaConfigurationFactory
import com.github.vgryzunov.opaintellijplugin.ide.runconfig.test.OpaTestRunConfiguration
import com.github.vgryzunov.opaintellijplugin.ide.runconfig.test.OpaTestRunConfigurationType
import com.github.vgryzunov.opaintellijplugin.lang.psi.*
import com.github.vgryzunov.opaintellijplugin.lang.utils.REGO_TEST_RULE_PREFIX

class OpaTestRunConfigurationProducer : LazyRunConfigurationProducer<OpaTestRunConfiguration>() {
    override fun getConfigurationFactory(): ConfigurationFactory {
        return OpaConfigurationFactory(OpaTestRunConfigurationType.getInstance())
    }

    override fun isConfigurationFromContext(
        configuration: OpaTestRunConfiguration,
        context: ConfigurationContext
    ): Boolean {
        val element = context.psiLocation
        val regoPackage = element?.getRegoPackage() ?: return false

        return configuration.additionalArgs?.matches(Regex(".*${getRunOption(element, regoPackage)}( .*|\$)")) ?: false
    }

    override fun setupConfigurationFromContext(
        configuration: OpaTestRunConfiguration,
        context: ConfigurationContext,
        sourceElement: Ref<PsiElement>
    ): Boolean {
        val element = sourceElement.get()
        if (element.parent.parent is RegoRuleHead && ! element.text.startsWith(REGO_TEST_RULE_PREFIX)) return false
        val regoPackage = element.getRegoPackage() ?: return false


        configuration.additionalArgs = "-f pretty  ${getRunOption(element, regoPackage)}"
        configuration.name = "test ${element.text}"
        return true
    }


    /**
     * generate the run option to pass to opa eval. This option allow to filter the test to execute by passing a regex
     * matching the test names.
     */
    private fun getRunOption(element: PsiElement, regoPackage: RegoPackage?): String {
        if (element.parent.parent is RegoRuleHead) {
            return "-r data.${regoPackage?.ref?.text}.${element.text}"
        }
        return "-r data.${regoPackage?.ref?.text}"
    }
}
