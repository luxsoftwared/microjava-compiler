/* The following code was generated by JFlex 1.4.3 on 6/27/24, 9:34 PM */


package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 6/27/24, 9:34 PM from the specification file
 * <tt>spec/mjlexer.lex</tt>
 */
class Yylex implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int COMMENT = 2;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1, 1
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\10\0\1\2\1\2\1\4\1\0\1\2\1\3\22\0\1\1\4\52"+
    "\1\33\1\52\1\51\1\40\1\41\1\31\1\26\1\36\1\27\1\37"+
    "\1\32\12\50\1\34\1\35\1\52\1\30\3\52\32\53\1\42\1\52"+
    "\1\43\1\52\1\54\1\52\1\12\1\53\1\20\1\21\1\17\1\46"+
    "\1\11\1\53\1\14\2\53\1\47\1\13\1\15\1\10\1\6\1\53"+
    "\1\7\1\5\1\16\1\22\1\23\1\24\1\25\2\53\1\44\1\52"+
    "\1\45\1\52\uff81\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\1\1\1\2\1\1\1\2\11\3\1\4\1\5"+
    "\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15"+
    "\1\16\1\17\1\20\1\21\1\22\1\23\1\3\1\24"+
    "\1\1\2\25\13\3\1\26\1\27\1\3\1\0\1\30"+
    "\7\3\1\31\1\3\1\32\4\3\1\33\4\3\1\34"+
    "\1\3\1\35\1\3\1\36\1\3\1\37\3\3\1\40"+
    "\1\41\2\3\1\42\1\43\1\44\1\3\1\45\1\3"+
    "\1\46\2\3\1\47";

  private static int [] zzUnpackAction() {
    int [] result = new int[95];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\55\0\132\0\132\0\207\0\264\0\341\0\u010e"+
    "\0\u013b\0\u0168\0\u0195\0\u01c2\0\u01ef\0\u021c\0\u0249\0\u0276"+
    "\0\u02a3\0\132\0\132\0\u02d0\0\132\0\132\0\132\0\132"+
    "\0\132\0\132\0\132\0\132\0\132\0\132\0\132\0\u02fd"+
    "\0\u032a\0\u0357\0\132\0\u0384\0\u03b1\0\u03de\0\u040b\0\u0438"+
    "\0\u0465\0\u0492\0\u04bf\0\u04ec\0\u0519\0\u0546\0\u0573\0\132"+
    "\0\132\0\u05a0\0\u05cd\0\132\0\u05fa\0\u0627\0\u0654\0\u0681"+
    "\0\u06ae\0\u06db\0\u0708\0\u013b\0\u0735\0\u013b\0\u0762\0\u078f"+
    "\0\u07bc\0\u07e9\0\132\0\u0816\0\u0843\0\u0870\0\u089d\0\u013b"+
    "\0\u08ca\0\u013b\0\u08f7\0\u013b\0\u0924\0\u013b\0\u0951\0\u097e"+
    "\0\u09ab\0\u013b\0\u013b\0\u09d8\0\u0a05\0\u013b\0\u013b\0\u013b"+
    "\0\u0a32\0\u013b\0\u0a5f\0\u013b\0\u0a8c\0\u0ab9\0\u013b";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[95];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\3\2\4\1\5\1\0\1\6\1\7\1\10\1\11"+
    "\1\12\1\11\1\13\1\11\1\14\1\15\1\11\1\16"+
    "\2\11\1\17\2\11\1\20\1\21\1\22\1\23\1\24"+
    "\1\25\1\26\1\27\1\30\1\31\1\32\1\33\1\34"+
    "\1\35\1\36\1\37\1\40\1\11\1\41\1\42\1\3"+
    "\1\11\1\3\3\43\1\44\1\0\50\43\61\0\1\4"+
    "\55\0\11\11\1\45\7\11\20\0\3\11\2\0\2\11"+
    "\5\0\2\11\1\46\16\11\20\0\3\11\2\0\2\11"+
    "\5\0\5\11\1\47\4\11\1\50\6\11\20\0\3\11"+
    "\2\0\2\11\5\0\21\11\20\0\3\11\2\0\2\11"+
    "\5\0\3\11\1\51\15\11\20\0\3\11\2\0\2\11"+
    "\5\0\5\11\1\52\13\11\20\0\3\11\2\0\2\11"+
    "\5\0\5\11\1\53\4\11\1\54\6\11\20\0\3\11"+
    "\2\0\2\11\5\0\2\11\1\55\16\11\20\0\3\11"+
    "\2\0\2\11\5\0\3\11\1\56\15\11\20\0\3\11"+
    "\2\0\2\11\5\0\3\11\1\57\15\11\20\0\3\11"+
    "\2\0\2\11\26\0\1\60\55\0\1\61\57\0\1\43"+
    "\27\0\5\11\1\62\13\11\20\0\3\11\2\0\2\11"+
    "\50\0\1\41\5\0\1\63\3\0\50\63\4\0\1\64"+
    "\55\0\5\11\1\65\13\11\20\0\3\11\2\0\2\11"+
    "\5\0\3\11\1\66\3\11\1\67\11\11\20\0\3\11"+
    "\2\0\2\11\5\0\10\11\1\70\10\11\20\0\3\11"+
    "\2\0\2\11\5\0\5\11\1\71\3\11\1\72\7\11"+
    "\20\0\3\11\2\0\2\11\5\0\11\11\1\73\7\11"+
    "\20\0\3\11\2\0\2\11\5\0\20\11\1\74\20\0"+
    "\3\11\2\0\2\11\5\0\6\11\1\75\12\11\20\0"+
    "\3\11\2\0\2\11\5\0\17\11\1\76\1\11\20\0"+
    "\3\11\2\0\2\11\5\0\15\11\1\77\3\11\20\0"+
    "\3\11\2\0\2\11\5\0\10\11\1\100\10\11\20\0"+
    "\3\11\2\0\2\11\5\0\7\11\1\101\11\11\20\0"+
    "\3\11\2\0\2\11\5\0\21\11\20\0\1\11\1\102"+
    "\1\11\2\0\2\11\51\0\1\103\10\0\11\11\1\104"+
    "\7\11\20\0\3\11\2\0\2\11\5\0\4\11\1\105"+
    "\14\11\20\0\3\11\2\0\2\11\5\0\10\11\1\106"+
    "\10\11\20\0\3\11\2\0\2\11\5\0\4\11\1\107"+
    "\14\11\20\0\3\11\2\0\2\11\5\0\14\11\1\110"+
    "\4\11\20\0\3\11\2\0\2\11\5\0\15\11\1\111"+
    "\3\11\20\0\3\11\2\0\2\11\5\0\3\11\1\112"+
    "\15\11\20\0\3\11\2\0\2\11\5\0\12\11\1\113"+
    "\6\11\20\0\3\11\2\0\2\11\5\0\12\11\1\114"+
    "\6\11\20\0\3\11\2\0\2\11\5\0\1\115\20\11"+
    "\20\0\3\11\2\0\2\11\5\0\14\11\1\116\4\11"+
    "\20\0\3\11\2\0\2\11\5\0\1\117\20\11\20\0"+
    "\3\11\2\0\2\11\5\0\7\11\1\120\11\11\20\0"+
    "\3\11\2\0\2\11\5\0\2\11\1\121\16\11\20\0"+
    "\3\11\2\0\2\11\5\0\11\11\1\122\7\11\20\0"+
    "\3\11\2\0\2\11\5\0\12\11\1\123\6\11\20\0"+
    "\3\11\2\0\2\11\5\0\2\11\1\124\16\11\20\0"+
    "\3\11\2\0\2\11\5\0\1\125\20\11\20\0\3\11"+
    "\2\0\2\11\5\0\11\11\1\126\7\11\20\0\3\11"+
    "\2\0\2\11\5\0\12\11\1\127\6\11\20\0\3\11"+
    "\2\0\2\11\5\0\13\11\1\130\5\11\20\0\3\11"+
    "\2\0\2\11\5\0\5\11\1\131\13\11\20\0\3\11"+
    "\2\0\2\11\5\0\10\11\1\132\10\11\20\0\3\11"+
    "\2\0\2\11\5\0\1\11\1\133\17\11\20\0\3\11"+
    "\2\0\2\11\5\0\6\11\1\134\12\11\20\0\3\11"+
    "\2\0\2\11\5\0\5\11\1\135\13\11\20\0\3\11"+
    "\2\0\2\11\5\0\13\11\1\136\5\11\20\0\3\11"+
    "\2\0\2\11\5\0\12\11\1\137\6\11\20\0\3\11"+
    "\2\0\2\11";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2790];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\2\0\2\11\15\1\2\11\1\1\13\11\3\1\1\11"+
    "\14\1\2\11\1\1\1\0\1\11\16\1\1\11\34\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[95];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */

	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type) {
		return new Symbol(type, yyline+1, yycolumn);
	}
	
	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn, value);
	}



  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Yylex(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  Yylex(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 126) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn++;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 10: 
          { return new_symbol(sym.COLON, yytext());
          }
        case 40: break;
        case 4: 
          { return new_symbol(sym.PLUS, yytext());
          }
        case 41: break;
        case 15: 
          { return new_symbol(sym.RPAREN, yytext());
          }
        case 42: break;
        case 36: 
          { return new_symbol(sym.STATIC, yytext());
          }
        case 43: break;
        case 12: 
          { return new_symbol(sym.COMMA, yytext());
          }
        case 44: break;
        case 7: 
          { return new_symbol(sym.MULTIPLY, yytext());
          }
        case 45: break;
        case 9: 
          { return new_symbol(sym.MODUO, yytext());
          }
        case 46: break;
        case 26: 
          { return new_symbol(sym.NEW, yytext());
          }
        case 47: break;
        case 19: 
          { return new_symbol(sym.RBRACE, yytext());
          }
        case 48: break;
        case 5: 
          { return new_symbol(sym.MINUS, yytext());
          }
        case 49: break;
        case 25: 
          { return new_symbol(sym.MAX, yytext());
          }
        case 50: break;
        case 27: 
          { return new_symbol(sym.CHAR, Character.valueOf(yytext().charAt(1)));
          }
        case 51: break;
        case 31: 
          { return new_symbol(sym.VOID, yytext());
          }
        case 52: break;
        case 24: 
          { yybegin(YYINITIAL);
          }
        case 53: break;
        case 3: 
          { return new_symbol(sym.IDENT, yytext());
          }
        case 54: break;
        case 34: 
          { return new_symbol(sym.CONST, yytext());
          }
        case 55: break;
        case 14: 
          { return new_symbol(sym.LPAREN, yytext());
          }
        case 56: break;
        case 6: 
          { return new_symbol(sym.EQUAL, yytext());
          }
        case 57: break;
        case 21: 
          { yybegin(COMMENT);
          }
        case 58: break;
        case 16: 
          { return new_symbol(sym.LBRACKET, yytext());
          }
        case 59: break;
        case 33: 
          { return new_symbol(sym.RANGE, yytext());
          }
        case 60: break;
        case 18: 
          { return new_symbol(sym.LBRACE, yytext());
          }
        case 61: break;
        case 20: 
          { return new_symbol(sym.NUMBER, Integer.parseInt(yytext()));
          }
        case 62: break;
        case 28: 
          { return new_symbol(sym.READ, yytext());
          }
        case 63: break;
        case 13: 
          { return new_symbol(sym.PERIOD, yytext());
          }
        case 64: break;
        case 22: 
          { return new_symbol(sym.INCREMENT, yytext());
          }
        case 65: break;
        case 29: 
          { return new_symbol(sym.GOTO, yytext());
          }
        case 66: break;
        case 1: 
          { System.err.println("Leksicka greska ("+yytext()+") u liniji "+(yyline+1)+", redu "+(yycolumn) );
          }
        case 67: break;
        case 11: 
          { return new_symbol(sym.SEMI, yytext());
          }
        case 68: break;
        case 32: 
          { return new_symbol(sym.PRINT, yytext());
          }
        case 69: break;
        case 35: 
          { return new_symbol(sym.BOOL_CONST, false);
          }
        case 70: break;
        case 17: 
          { return new_symbol(sym.RBRACKET, yytext());
          }
        case 71: break;
        case 39: 
          { return new_symbol(sym.NAMESPACE, yytext());
          }
        case 72: break;
        case 23: 
          { return new_symbol(sym.DECREMENT, yytext());
          }
        case 73: break;
        case 38: 
          { return new_symbol(sym.PROG, yytext());
          }
        case 74: break;
        case 30: 
          { return new_symbol(sym.BOOL_CONST, true);
          }
        case 75: break;
        case 37: 
          { return new_symbol(sym.RETURN, yytext());
          }
        case 76: break;
        case 2: 
          { 
          }
        case 77: break;
        case 8: 
          { return new_symbol(sym.DIVIDE, yytext());
          }
        case 78: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              { 	return new_symbol(sym.EOF);
 }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
