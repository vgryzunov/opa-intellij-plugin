/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package com.github.vgryzunov.opaintellijplugin.ide.typing


import com.github.vgryzunov.opaintellijplugin.lang.psi.RegoTypes
import com.intellij.codeInsight.editorActions.SimpleTokenSetQuoteHandler

class RegoQuoteHandler: SimpleTokenSetQuoteHandler(RegoTypes.STRING_TOKEN, RegoTypes.RAW_STRING){
}
