package com.github.vgryzunov.opaintellijplugin.lang.parser

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import com.intellij.psi.TokenType

import com.github.vgryzunov.opaintellijplugin.lang.lexer.RegoLexerAdapter
import com.github.vgryzunov.opaintellijplugin.lang.RegoLanguage
import com.github.vgryzunov.opaintellijplugin.lang.psi.RegoFile
import com.github.vgryzunov.opaintellijplugin.lang.psi.RegoTypes

class RegoParserDefinition: ParserDefinition {
    companion object {
        val WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE)
        val COMMENTS = TokenSet.create(RegoTypes.COMMENT)
        val FILE = IFileElementType(RegoLanguage)
    }

    override fun createLexer(project: Project?): Lexer = RegoLexerAdapter()

    override fun createParser(project: Project?): PsiParser = RegoParser()

    override fun getFileNodeType(): IFileElementType = FILE

    override fun getCommentTokens(): TokenSet = COMMENTS

    override fun getStringLiteralElements(): TokenSet = TokenSet.EMPTY

    override fun createElement(node: ASTNode?): PsiElement = RegoTypes.Factory.createElement(node)

    override fun createFile(viewProvider: FileViewProvider): PsiFile = RegoFile(viewProvider)
}
