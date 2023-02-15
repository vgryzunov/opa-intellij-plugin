package com.github.vgryzunov.opaintellijplugin.ide.runconfig.test

import com.intellij.execution.DefaultExecutionResult
import com.intellij.execution.ExecutionResult
import com.intellij.execution.Executor
import com.intellij.execution.configurations.CommandLineState
import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.configurations.RunConfiguration
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.process.ProcessHandlerFactory
import com.intellij.execution.process.ProcessTerminatedListener
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.execution.runners.ProgramRunner
import com.intellij.util.execution.ParametersListUtil
import com.github.vgryzunov.opaintellijplugin.opa.tool.OpaBaseTool
import java.nio.charset.StandardCharsets

class OpaTestRunProfileState(
    env: ExecutionEnvironment,
    private val runConfiguration: OpaTestRunConfiguration
) : CommandLineState(env) {

    init {
        consoleBuilder = OpaTestConsoleBuilder(environment.runProfile as RunConfiguration, environment.executor)
        // TODO add filter to console in order to rego file reference clickable
    }

    override fun startProcess(): ProcessHandler {
        val args = ParametersListUtil.parse(runConfiguration.additionalArgs ?: "")

        runConfiguration.bundleDir?.let {
            args.add("-b")
            args.add(runConfiguration.bundleDir.toString())
        }

        if(! args.contains("-v")){
            args.add("-v")
        }

        if(runConfiguration.getFormatOptionIndex(args) == -1){
            args.add("-f")
            args.add("pretty")
        }

        val cmd = GeneralCommandLine(OpaBaseTool.opaBinary)
            .withEnvironment(runConfiguration.env.envs)
            .withParameters("test")
            .withParameters(args)
            .withCharset(StandardCharsets.UTF_8)

        val processHandler = ProcessHandlerFactory.getInstance().createColoredProcessHandler(cmd)
        ProcessTerminatedListener.attach(processHandler)

        return processHandler
    }


    override fun execute(executor: Executor, runner: ProgramRunner<*>): ExecutionResult {
        val processHandler = startProcess()
        val console = createConsole(executor)
        console?.attachToProcess(processHandler)
        return DefaultExecutionResult(console, processHandler) //.apply { setRestartActions(ToggleAutoTestAction()) } // TODO CHECK THAT LATER
    }

}
