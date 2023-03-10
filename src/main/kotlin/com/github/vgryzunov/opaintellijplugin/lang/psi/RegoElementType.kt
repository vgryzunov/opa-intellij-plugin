/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package com.github.vgryzunov.opaintellijplugin.lang.psi

import com.intellij.psi.tree.IElementType
import com.github.vgryzunov.opaintellijplugin.lang.RegoLanguage

class RegoElementType(debugName: String): IElementType(debugName, RegoLanguage) {
    override fun toString(): String = "RegoElementType." + super.toString()
}
