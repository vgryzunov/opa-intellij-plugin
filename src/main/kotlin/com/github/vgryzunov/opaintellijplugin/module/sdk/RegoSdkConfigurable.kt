package com.github.vgryzunov.opaintellijplugin.module.sdk

import com.intellij.openapi.projectRoots.AdditionalDataConfigurable
import com.intellij.openapi.projectRoots.Sdk
import com.intellij.openapi.projectRoots.SdkModel
import com.intellij.openapi.projectRoots.SdkModificator
import javax.swing.JComponent

class RegoSdkConfigurable(sdkModel: SdkModel, sdkModificator: SdkModificator): AdditionalDataConfigurable {
    private val mySdkModel = sdkModel
    private val myForm = RegoSdkConfigurableForm(mySdkModel)

    override fun createComponent(): JComponent? {
        TODO("Not yet implemented")
    }

    override fun isModified(): Boolean {
        TODO("Not yet implemented")
    }

    override fun apply() {
        TODO("Not yet implemented")
    }

    override fun setSdk(sdk: Sdk?) {
        TODO("Not yet implemented")
    }
}
