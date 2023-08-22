/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */
package com.github.vgryzunov.opaintellijplugin.ide.highlight

import com.github.vgryzunov.opaintellijplugin.ide.colors.RegoColor
import com.github.vgryzunov.opaintellijplugin.lang.psi.RegoEmptySet
import com.github.vgryzunov.opaintellijplugin.lang.psi.RegoExprCall
import com.github.vgryzunov.opaintellijplugin.lang.psi.RegoRuleHead
import com.github.vgryzunov.opaintellijplugin.lang.psi.RegoVar
import com.github.vgryzunov.opaintellijplugin.lang.psi.RegoRuleHeadComp
import com.github.vgryzunov.opaintellijplugin.openapiext.isUnitTestMode
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement

class RegoHighlighterAnnotator : AnnotatorBase() {
    // visibility for Testing
    val usedColors = listOf(RegoColor.RULE_HEAD.textAttributesKey, RegoColor.CALL.textAttributesKey)

    override fun annotateInternal(element: PsiElement, holder: AnnotationHolder) {
        val (style, range) = when (element) {
            is RegoVar -> Pair(RegoColor.RULE_HEAD, element.textRange)


            is RegoEmptySet -> Pair(RegoColor.CALL, element.textRange)

            is RegoExprCall -> {
                val varList = element.refArgDotList
                val textRange = if (varList.size >= 1) varList[varList.size - 1].`var`.textRange else element.`var`.textRange
                Pair(RegoColor.CALL, textRange)
            }

            else -> null
        } ?: return

        val severity = if (isUnitTestMode) style.testSeverity else HighlightSeverity.INFORMATION

        holder.newSilentAnnotation(severity)
            .range(range)
            .textAttributes(style.textAttributesKey)
            .create()
    }
}
