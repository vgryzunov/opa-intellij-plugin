package com.github.vgryzunov.opaintellijplugin.ide.highlight

import com.github.vgryzunov.opaintellijplugin.lang.lexer.RegoLexerAdapter
import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import com.github.vgryzunov.opaintellijplugin.ide.colors.RegoColor.*
import com.github.vgryzunov.opaintellijplugin.lang.psi.RegoTypes
import com.github.vgryzunov.opaintellijplugin.lang.psi.REGO_KEYWORDS
import com.github.vgryzunov.opaintellijplugin.lang.psi.REGO_OPERATOR

class RegoHighlighter: SyntaxHighlighterBase() {
    override fun getHighlightingLexer(): Lexer = RegoLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> = pack(tokenToColorMap[tokenType])

    val tokenToColorMap: Map<IElementType, TextAttributesKey> = HashMap()

    init {
        fillMap(tokenToColorMap, LINE_COMMENT.textAttributesKey, RegoTypes.COMMENT)

        fillMap(tokenToColorMap, STRING.textAttributesKey, RegoTypes.STRING_TOKEN)
        fillMap(tokenToColorMap, RAW_STRING.textAttributesKey, RegoTypes.RAW_STRING)

        fillMap(tokenToColorMap, BRACES.textAttributesKey, RegoTypes.RBRACE)
        fillMap(tokenToColorMap, BRACES.textAttributesKey, RegoTypes.LBRACE)

        fillMap(tokenToColorMap, BRACKETS.textAttributesKey, RegoTypes.RBRACK)
        fillMap(tokenToColorMap, BRACKETS.textAttributesKey, RegoTypes.LBRACK)

        fillMap(tokenToColorMap, PARENTHESES.textAttributesKey, RegoTypes.RPAREN)
        fillMap(tokenToColorMap, PARENTHESES.textAttributesKey, RegoTypes.LPAREN)

        fillMap(tokenToColorMap, COMMA.textAttributesKey, RegoTypes.COMMA)
        fillMap(tokenToColorMap, SEMICOLON.textAttributesKey, RegoTypes.SEMICOLON)
        fillMap(tokenToColorMap, DOT.textAttributesKey, RegoTypes.DOT)

        fillMap(tokenToColorMap, NUMBER.textAttributesKey, RegoTypes.NUMBER)
        fillMap(tokenToColorMap, BOOLEAN.textAttributesKey, RegoTypes.TRUE)
        fillMap(tokenToColorMap, BOOLEAN.textAttributesKey, RegoTypes.FALSE)

        fillMap(tokenToColorMap, REGO_OPERATOR, OPERATOR.textAttributesKey)
        fillMap(tokenToColorMap, REGO_KEYWORDS, KEYWORD.textAttributesKey)

    }
}
