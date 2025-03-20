package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import java_cup.runtime.Symbol;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.etf.pp1.mj.runtime.Code;
import rs.ac.bg.etf.pp1.TabExtended;

public class MJParserTest {

	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}
	
	public static void main(String[] args) throws Exception {
		
		Logger log = Logger.getLogger(MJParserTest.class);
		
		Reader br = null;
		try {
			File sourceCode = null;
			if( args.length > 0 )	sourceCode = new File(args[0]);
			else sourceCode = new File("test/gotoTest.mj");
			
			log.info("Compiling source file: " + sourceCode.getAbsolutePath());
			
			br = new BufferedReader(new FileReader(sourceCode));
			Yylex lexer = new Yylex(br);
			
			MJParser p = new MJParser(lexer);
			
			System.out.println("=============================Syntax analysis=============================");
	        Symbol s = p.parse();  //pocetak parsiranja
	        
	        Program prog = (Program)(s.value);
	        TabExtended.init();
			// ispis sintaksnog stabla
	        //System.out.println("=============================Syntax tree=============================");
			//log.info(prog.toString(""));
	        System.out.println("=============================Semantic analysis=============================");

			// ispis prepoznatih programskih konstrukcija
			SemanticPass v = new SemanticPass();
			prog.traverseBottomUp(v); 
	      
			log.info(" Print count calls = " + v.printCallCount);

			log.info(" Deklarisanih promenljivih ima = " + v.varDeclCount);
			
			//log.info("===================================");
			TabExtended.dump(new DumpSymbolTableVisitorBool());
			
			if(!v.errorDetected) {
				log.info("Parsiranje uspesno zavrseno");
				File objFile = new File("test/program.obj");
				if(objFile.exists()) objFile.delete();
				
				CodeGenerator codeGen = new CodeGenerator();
				prog.traverseBottomUp(codeGen);
				Code.dataSize = v.nVars;
				Code.mainPc = codeGen.getMainPc();
				Code.write(new FileOutputStream(objFile) );
			}
			
		} 
		finally {
			if (br != null) try { br.close(); } catch (IOException e1) { log.error(e1.getMessage(), e1); }
		}

	}
	
	
}
