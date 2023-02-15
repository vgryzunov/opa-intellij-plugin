/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package com.github.vgryzunov.opaintellijplugin.module.sdk

import com.intellij.openapi.options.ConfigurationException
import com.intellij.openapi.projectRoots.ValidatableSdkAdditionalData
import com.intellij.openapi.projectRoots.SdkModel

class RegoSdkAdditionalData: ValidatableSdkAdditionalData {
    override fun checkValid(sdkModel: SdkModel) {
        throw  ConfigurationException("Please configure OPA binary")
    }

    var sdk: RegoSdk? = null

}
