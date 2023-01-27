/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package com.github.vgryzunov.opaintellijplugin.ide.runconfig

import com.intellij.execution.configurations.CommandLineState
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.runners.ExecutionEnvironment

class OpaEvalRunProfileState(
    env: ExecutionEnvironment,
    private val runConfiguration: OpaEvalRunConfiguration
) : CommandLineState(env)  {
    override fun startProcess(): ProcessHandler {
        TODO("Not yet implemented")
    }
}
