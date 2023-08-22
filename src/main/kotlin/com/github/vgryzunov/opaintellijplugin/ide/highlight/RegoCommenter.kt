package com.github.vgryzunov.opaintellijplugin.ide.highlight

import com.intellij.lang.Commenter

class RegoCommenter : Commenter {
    override fun getLineCommentPrefix(): String? = "#"

    override fun getBlockCommentPrefix(): String? = null

    override fun getBlockCommentSuffix(): String? = null

    override fun getCommentedBlockCommentPrefix(): String? = null

    override fun getCommentedBlockCommentSuffix() = null
}
