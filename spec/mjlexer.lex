
package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;

%%

%{

	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type) {
		return new Symbol(type, yyline+1, yycolumn);
	}
	
	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn, value);
	}

%}

%cup
%line
%column

%xstate COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}

%%


" " 	{ }
"\b" 	{ }
"\t" 	{ }
"\r\n" 	{ }
"\f" 	{ }
"\s"	{ }

"program"   { return new_symbol(sym.PROG, yytext());}
"print" 	{ return new_symbol(sym.PRINT, yytext()); }
"namespace"	{ return new_symbol(sym.NAMESPACE, yytext()); }
"read"		{ return new_symbol(sym.READ, yytext()); }
"const"		{ return new_symbol(sym.CONST, yytext()); }
"return" 	{ return new_symbol(sym.RETURN, yytext()); }
"void" 		{ return new_symbol(sym.VOID, yytext()); }
"static"	{ return new_symbol(sym.STATIC, yytext()); }
"new"		{ return new_symbol(sym.NEW, yytext()); }
"range"		{ return new_symbol(sym.RANGE, yytext()); }
"max"		{ return new_symbol(sym.MAX, yytext()); }
"goto"		{ return new_symbol(sym.GOTO, yytext()); }

"+" 		{ return new_symbol(sym.PLUS, yytext()); }
"-"			{ return new_symbol(sym.MINUS, yytext()); }
"=" 		{ return new_symbol(sym.EQUAL, yytext()); }
"*"			{ return new_symbol(sym.MULTIPLY, yytext()); }
"/"			{ return new_symbol(sym.DIVIDE, yytext()); }
"%"			{ return new_symbol(sym.MODUO, yytext()); }

"++"		{ return new_symbol(sym.INCREMENT, yytext()); }
"--"		{ return new_symbol(sym.DECREMENT, yytext()); }

":"			{ return new_symbol(sym.COLON, yytext()); }
";" 		{ return new_symbol(sym.SEMI, yytext()); }
"," 		{ return new_symbol(sym.COMMA, yytext()); }
"." 		{ return new_symbol(sym.PERIOD, yytext()); }

"(" 		{ return new_symbol(sym.LPAREN, yytext()); }
")" 		{ return new_symbol(sym.RPAREN, yytext()); }
"["			{ return new_symbol(sym.LBRACKET, yytext()); }
"]"			{ return new_symbol(sym.RBRACKET, yytext()); }
"{" 		{ return new_symbol(sym.LBRACE, yytext()); }
"}"			{ return new_symbol(sym.RBRACE, yytext()); }

"//" {yybegin(COMMENT);}
<COMMENT> . {yybegin(COMMENT);}
<COMMENT> "\r\n" { yybegin(YYINITIAL); }


"true"				{ return new_symbol(sym.BOOL_CONST, true); }
"false"				{ return new_symbol(sym.BOOL_CONST, false); }
[0-9]+  			{ return new_symbol(sym.NUMBER, Integer.parseInt(yytext())); }
"'"[\x20-\x7E]"'"				{ return new_symbol(sym.CHAR, Character.valueOf(yytext().charAt(1))); }
[a-zA-Z][a-zA-Z0-9_]* 	{return new_symbol(sym.IDENT, yytext()); }

. { System.err.println("Leksicka greska ("+yytext()+") u liniji "+(yyline+1)+", redu "+(yycolumn) ); }










