package com.github.vgryzunov.opaintellijplugin.lang.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.FileViewProvider
import com.github.vgryzunov.opaintellijplugin.lang.RegoFileType
import com.github.vgryzunov.opaintellijplugin.lang.RegoLanguage

class RegoFile(fileViewProvider: FileViewProvider) : PsiFileBase(fileViewProvider, RegoLanguage) {
    override fun getFileType(): FileType = RegoFileType
    override fun toString(): String = "Rego File"

}

val VirtualFile.isNotRegoFile: Boolean get() = !isRegoFile
val VirtualFile.isRegoFile: Boolean get() = fileType == RegoFileType
