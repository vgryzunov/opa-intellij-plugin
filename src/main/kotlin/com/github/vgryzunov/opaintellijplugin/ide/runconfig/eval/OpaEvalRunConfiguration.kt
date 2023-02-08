/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package com.github.vgryzunov.opaintellijplugin.ide.runconfig.eval

import com.github.vgryzunov.opaintellijplugin.ide.runconfig.ui.OpaEvalRunCommandEditor
import com.github.vgryzunov.opaintellijplugin.openapiext.readPath
import com.github.vgryzunov.opaintellijplugin.openapiext.readString
import com.github.vgryzunov.opaintellijplugin.openapiext.writePath
import com.github.vgryzunov.opaintellijplugin.openapiext.writeString
import com.intellij.execution.Executor
import com.intellij.execution.configuration.EnvironmentVariablesData
import com.intellij.execution.configurations.*
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.project.Project
import com.intellij.util.execution.ParametersListUtil
import com.intellij.util.io.isFile
import org.jdom.Element
import java.nio.file.Path

class OpaEvalRunConfiguration(
    project: Project,
    factory: ConfigurationFactory,
    name: String
) : LocatableConfigurationBase<OpaEvalRunProfileState>(project, factory, name) {
    /**
     * the query to evaluate
     */
    var query: String? = null

    /**
     * the input to pass to opa eval  (ie option --input <path>)
     */
    var input: Path? = null

    /**
     * the bundle directory to pass to opa eval (ie option -b ) maybe empty if [additionalArgs] contains
     * --data option
     */
    var bundleDir: Path? = null

    /**
     * others arguments to pass to opa eval command (eg -f pretty)
     */
    var additionalArgs: String? = null
    var env: EnvironmentVariablesData = EnvironmentVariablesData.DEFAULT

    override fun suggestedName(): String? {
        return query
    }

    override fun getConfigurationEditor(): SettingsEditor<out RunConfiguration?> = OpaEvalRunCommandEditor(project)

    override fun checkConfiguration() {
        checkConfig()
    }

    private fun checkConfig() {
        // TODO implement more intelligent test when we additionalArgs component will add a real parser (allow auto completion and real parsing of args)
        if (query.isNullOrBlank()) {
            throw RuntimeConfigurationError("Query can not be empty")
        }

        if (input == null || !input!!.isFile()) {
            throw RuntimeConfigurationError("Input must be a path to a file")
        }

        val args = ParametersListUtil.parse(additionalArgs ?: "")


        val noDataArgs = !(args.contains("--data") || args.contains("-d"))
        val noBundleDir = bundleDir == null || bundleDir!!.toString().isEmpty()

        if (noDataArgs && noBundleDir) {
            throw RuntimeConfigurationError("You must either defined a bundle directory or data through Additional args (option -d <path> or --data <path>)")
        }

        bundleDir?.let {
            if (it.isFile()) {
                throw RuntimeConfigurationError("Bundle directory must be a directory")
            }
        }

    }

    override fun getState(executor: Executor, executionEnvironment: ExecutionEnvironment): RunProfileState {
        checkConfig()
        return OpaEvalRunProfileState(executionEnvironment, this)
    }

    /**
     * Handle the deserialization of the run configuration (ie read it from .idea/workspace.xml when IDE start or when
     * user want to edit it )
     */
    override fun readExternal(element: Element) {
        super.readExternal(element)

        query = element.readString("query")
        input = element.readPath("input")
        bundleDir = element.readPath("bundledir")
        additionalArgs = element.readString("additionalargs")
        env = EnvironmentVariablesData.readExternal(element)
    }

    /**
     * Handle the serialization of the run configuration (ie save it to .idea/workspace.xml when user modify it and
     * click to apply)
     */
    override fun writeExternal(element: Element) {
        super.writeExternal(element)

        element.writeString("query", query)
        element.writePath("input", input)
        element.writePath("bundledir", bundleDir)
        element.writeString("additionalargs", additionalArgs)
        env.writeExternal(element)

    }
}
