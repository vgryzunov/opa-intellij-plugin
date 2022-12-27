/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package com.github.vgryzunov.opaintellijplugin.ide.highlight

import com.github.vgryzunov.opaintellijplugin.openapiext.isUnitTestMode
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.openapi.Disposable
import com.intellij.openapi.util.Disposer
import com.intellij.psi.PsiElement
import com.intellij.util.containers.ContainerUtil
import org.jetbrains.annotations.TestOnly

/**
 * Abstract annotator that adds annotation only if
 *  * it not run in test mode
 *  * it runs in test mode and annotator is registered by testing class
 *
 * It simplifies the tests because only the class under test adds annotations.
 * So in test output, we get only the highlighting of the class under test.
 */
abstract class AnnotatorBase : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (!isUnitTestMode || javaClass in enabledAnnotators) {
            annotateInternal(element, holder)
        }
    }

    /**
     * Annotates the specified PSI element.
     * for more info see [com.intellij.lang.annotation.Annotator.annotate]
     */
    protected abstract fun annotateInternal(element: PsiElement, holder: AnnotationHolder)

    companion object {
        private val enabledAnnotators: MutableSet<Class<out AnnotatorBase>> = ContainerUtil.newConcurrentSet()

        @TestOnly
        fun enableAnnotator(annotatorClass: Class<out AnnotatorBase>, parentDisposable: Disposable) {
            enabledAnnotators += annotatorClass
            Disposer.register(parentDisposable, Disposable { enabledAnnotators -= annotatorClass })
        }
    }
}
