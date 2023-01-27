package com.github.vgryzunov.opaintellijplugin.ide.runconfig.test

import com.intellij.execution.Executor
import com.intellij.execution.configurations.RunConfiguration
import com.intellij.execution.testframework.TestConsoleProperties
import com.intellij.execution.testframework.sm.SMCustomMessagesParsing
import com.intellij.execution.testframework.sm.runner.OutputToGeneralTestEventsConverter
import com.intellij.execution.testframework.sm.runner.SMTRunnerConsoleProperties

class OpaTestConsoleProperties(
    config: RunConfiguration,
    executor: Executor
) : SMTRunnerConsoleProperties(config, TEST_FRAMEWORK_NAME, executor), SMCustomMessagesParsing {

    init {
        isIdBasedTestTree = true
    }


    override fun createTestEventsConverter(
        testFrameworkName: String,
        consoleProperties: TestConsoleProperties
    ): OutputToGeneralTestEventsConverter = OpaTestEventsConverter(testFrameworkName, consoleProperties)

    companion object {
        const val TEST_FRAMEWORK_NAME: String = "OpaTest"
    }
}
