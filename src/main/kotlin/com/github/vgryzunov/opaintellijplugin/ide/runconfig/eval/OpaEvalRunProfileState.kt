/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package com.github.vgryzunov.opaintellijplugin.ide.runconfig.eval

import com.intellij.execution.configurations.CommandLineState
import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.process.ProcessHandlerFactory
import com.intellij.execution.process.ProcessTerminatedListener
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.util.execution.ParametersListUtil
import com.github.vgryzunov.opaintellijplugin.opa.tool.OpaBaseTool
import java.nio.charset.StandardCharsets

class OpaEvalRunProfileState(
    env: ExecutionEnvironment,
    private val runConfiguration: OpaEvalRunConfiguration
) : CommandLineState(env)  {
    override fun startProcess(): ProcessHandler {
        val args = ParametersListUtil.parse(runConfiguration.additionalArgs ?: "")

        runConfiguration.bundleDir?.let {
            args.add("-b")
            args.add(runConfiguration.bundleDir.toString())
        }
        args.add("--input")
        args.add(runConfiguration.input.toString())
        args.add(runConfiguration.query)

        val cmd = GeneralCommandLine(OpaBaseTool.opaBinary)
            .withEnvironment(runConfiguration.env.envs)
            .withParameters("eval")
            .withParameters(args)
            .withCharset(StandardCharsets.UTF_8)

        val processHandler = ProcessHandlerFactory.getInstance().createColoredProcessHandler(cmd)
        ProcessTerminatedListener.attach(processHandler)

        return processHandler
    }
}
