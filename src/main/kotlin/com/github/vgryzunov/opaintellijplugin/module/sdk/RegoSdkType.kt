package com.github.vgryzunov.opaintellijplugin.module.sdk

import com.github.vgryzunov.opaintellijplugin.lang.RegoIcons
import com.intellij.execution.configurations.PathEnvironmentVariableUtil
import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.projectRoots.*
import com.intellij.openapi.util.SystemInfo
import com.intellij.openapi.vfs.VirtualFile
import org.jdom.Element

import kotlin.io.path.name

class RegoSdkType: SdkType("OPA") {
    companion object {
        const val sdkName = "OPA"
        val WINDOWS_EXECUTABLE_SUFFIXES = arrayOf("cmd", "exe", "bat", "com")
        fun getInstance(): SdkTypeId {
            return findInstance(RegoSdkType::class.java)
        }
    }
    override fun saveAdditionalData(additionalData: SdkAdditionalData, additional: Element) {
        // no additional data needed (yet)
    }

    override fun suggestHomePath(): String? {
        return null
    }

    override fun isValidSdkHome(path: String): Boolean {
        val baseName = kotlin.io.path.Path(path).fileName.name
        return (PathEnvironmentVariableUtil.findInPath(baseName) != null)
    }

    override fun suggestSdkName(currentSdkName: String?, sdkHome: String): String {
        // TODO: add version vo suggested name
        return sdkName
    }

    override fun createAdditionalDataConfigurable(
        sdkModel: SdkModel,
        sdkModificator: SdkModificator
    ): AdditionalDataConfigurable? {
        return null
    }

    override fun getPresentableName() = "OPA"

    override fun getIcon() = RegoIcons.OPA

    override fun getHomeChooserDescriptor(): FileChooserDescriptor {
        val isWindows = SystemInfo.isWindows
        val descriptor = object : FileChooserDescriptor(true, false, false, false, false, false) {
            @Throws(Exception::class)
            override fun validateSelectedFiles(files: Array<VirtualFile>) {
                if (files.isNotEmpty()) {
                    if (!isValidSdkHome(files[0].path)) {
                        throw Exception("Invalid OPA executable: " + files[0].name)
                    }
                }
            }

            override fun isFileVisible(file: VirtualFile, showHiddenFiles: Boolean): Boolean {
                // TODO: add a better, customizable filtering
                if (!file.isDirectory) {
                    if (isWindows) {
                        val path = file.path
                        var looksExecutable = false
                        for (ext in WINDOWS_EXECUTABLE_SUFFIXES) {
                            if (path.endsWith(ext)) {
                                looksExecutable = true
                                break
                            }
                        }
                        return looksExecutable && super.isFileVisible(file, showHiddenFiles)
                    }
                }
                return super.isFileVisible(file, showHiddenFiles)
            }
        }.withTitle("Select OPA Executable").withShowHiddenFiles(SystemInfo.isUnix)

        // XXX: Workaround for PY-21787 and PY-43507 since the native macOS dialog always follows symlinks
        if (SystemInfo.isMac) {
            descriptor.isForcedToUseIdeaFileChooser = true
        }
        return descriptor
    }


    override fun getVersionString(sdk: Sdk): String
    {
        // TODO: execute OPA binary to get version
        return "undefiled"
    }
}
