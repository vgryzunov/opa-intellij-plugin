package com.github.vgryzunov.opaintellijplugin.module.sdk

import com.intellij.openapi.projectRoots.Sdk
import com.intellij.openapi.projectRoots.SdkAdditionalData
import com.intellij.openapi.projectRoots.SdkModel
import com.intellij.ui.SimpleListCellRenderer
import javax.swing.DefaultComboBoxModel
import javax.swing.JComboBox
import javax.swing.JPanel

class RegoSdkConfigurableForm (private val mySdkModel: SdkModel) {
    private val myInternalOPAComboBox: JComboBox<Sdk>? = null
    private val mySdksModel: DefaultComboBoxModel<Sdk> = DefaultComboBoxModel<Sdk>()
    private val myContentPanel: JPanel? = null

    init {
        myInternalOPAComboBox!!.setModel(mySdksModel);
        val renderer = SimpleListCellRenderer.create(
            ""
        ) { sdk: Sdk -> sdk.name }

        myInternalOPAComboBox.setRenderer(renderer)
    }

    fun addOPASdk(sdk: Sdk?) {
        mySdksModel.addElement(sdk)
    }

    fun removeAPSSdk(sdk: Sdk?) {
        mySdksModel.removeElement(sdk)
    }
    val selectedSdk: Sdk?
        get() = myInternalOPAComboBox!!.selectedItem as Sdk

    val contentPanel: JPanel
        get() = myContentPanel!!

    fun updateSdks(sdk: Sdk?, previousName: String?) {
        val sdks = mySdkModel.sdks
        for (currentSdk in sdks) {
            when(currentSdk) {
                is RegoSdk -> {
                    when(val data: SdkAdditionalData? = currentSdk.sdkAdditionalData) {
                        is RegoSdkAdditionalData -> {
                            val opa : RegoSdk? = data.sdk
                            if (opa != null && opa.name == previousName) {
                                data.sdk = opa
                            }
                        }
                    }
                }
            }

        }
        //updateJdks()
    }
}
