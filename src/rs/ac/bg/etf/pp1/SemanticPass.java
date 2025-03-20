
package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
//import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;
import rs.ac.bg.etf.pp1.TabExtended;

import java.util.ArrayList;
import java.util.HashMap;



public class SemanticPass extends VisitorAdaptor {
	
	int printCallCount = 0;
	int varDeclCount = 0;
	int constDeclCount= 0;
	int nVars;
	boolean errorDetected = false;
	
	Logger log = Logger.getLogger(getClass());
	private Struct DeclType = TabExtended.noType;
	Obj currentMethod = null;
	
	
	private String dataType(Struct s) {
		switch(s.getKind()) {
		case Struct.None: return "None";
		case Struct.Int: return "Int";
		case Struct.Char: return "Char";
		case Struct.Array: return "Array["+ dataType(s.getElemType()) +"]";
		case Struct.Class: return "Class";
		case Struct.Bool: return "Bool";
		case Struct.Enum: return "Enum";
		case Struct.Interface: return "Interface";
		default: return "";
		}
	}
	
	
	
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
		log.error("\n");
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	public void report_trace(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.trace(msg.toString());
	}
	
	

	//public void visit(VarOrArrayDecl varDecl){
		//varDeclCount++;
		//Obj varNode = TabExtended.insert(Obj.Var, varDecl.getVarOrArray().getVarName(), varDecl.getType().struct);
	//}
	
    
    public void visit(ProgName progName) {
		progName.obj = TabExtended.insert(Obj.Prog, progName.getProgName(), TabExtended.noType);
		TabExtended.openScope();
	}
	
    public void visit(Program program) {
    	nVars = TabExtended.currentScope.getnVars();
		TabExtended.chainLocalSymbols(program.getProgName().obj);
		TabExtended.closeScope();
	}
	
   //!!!Constant declarations----------------------------------------------------------------------------------
    private boolean checkIfNameDeclared(String name) {
    	Obj node = TabExtended.find(name);
    	if( node != TabExtended.noObj ) return true;
    	else return false;
    }
    private void declareConstant(String constName, int constValue, Struct constType, SyntaxNode info) {
    	if(checkIfNameDeclared(constName)) {
    		report_error("Simbol " + constName + " vec postoji u tabeli simbola!", info);
    		return;
    	}
    	if(constType.assignableTo(DeclType)) {
    		Obj node = TabExtended.insert(Obj.Con, constName, DeclType);
    		node.setAdr(constValue);
    		node.setLevel(0);
    		report_info("Definisana constanta "+ constName + "=" + constValue, info);
    	}else {
    		report_error("Pokusaj dodele pogresnog tipa:" + dataType(DeclType) + " " + constName + " <= " + dataType(constType), info);
    	}
    }
    
    
    public void visit(ConstDecl constDecl) {
    	DeclType = null;
    }
    
    public void visit(ConstDeclStart constDeclType) {
    	DeclType = constDeclType.getType().struct;
		report_trace("Trenutni decl tip je "+ dataType(constDeclType.getType().struct), constDeclType);
    }
    
    public void visit(ConstAssignInt constAssign) {  
    	declareConstant(constAssign.getConstName(), constAssign.getIntValue(), TabExtended.intType, constAssign);
    }
    
    public void visit(ConstAssignChar constAssign) {  
    	declareConstant(constAssign.getConstName(), constAssign.getCharValue(), TabExtended.charType, constAssign);
    }
    
    public void visit(ConstAssignBool constAssign) {  
    	declareConstant(constAssign.getConstName(), constAssign.getBoolValue() ? 1 : 0 , TabExtended.boolType, constAssign);
    }
    
    
    
    

	
	
	
	
	
    
    //!!! Variable declarations ---------------------------------------------------------------------------------
	private boolean checkVarNameAvailability(String name, SyntaxNode node) {
		if(checkIfNameDeclared(name) ) {
    		if(TabExtended.currentScope.findSymbol(name) != null) { 
    			report_error("Promenljiva sa imenom "+ name + " je vec deklarisana u ovom opsegu!", node);
    			return false;
    		}
    		//else: postoji u nekom okruzujucem opsegu, smemo da definisemo i u ovom
    	}
		return true;
	}
	
    public void visit(VarDeclProper varDecl) {
		DeclType = null;
	}
    
    public void visit(VarDeclTypeOk varDeclType) {
		DeclType = varDeclType.getType().struct;
		report_trace("Trenutni decl tip je "+ dataType(varDeclType.getType().struct) ,varDeclType);
		
	}
    
    public void visit(VarDeclared varDecl) {
    	if( !checkVarNameAvailability(varDecl.getVarName(), varDecl) ) return;
    	
    	Obj varNode = TabExtended.insert(Obj.Var, varDecl.getVarName(), DeclType);
    	report_info("Deklarisana promenljva "+varDecl.getVarName()+" tipa "+ dataType(varNode.getType()), varDecl);
    	varDeclCount++;
	}
    
    public void visit(ArrayDeclared varDecl) {
    	if( !checkVarNameAvailability(varDecl.getVarName(), varDecl) ) return;
    	
    	Obj varNode = TabExtended.insert(Obj.Var, varDecl.getVarName(), new Struct(Struct.Array, DeclType) );
    	report_info("Deklarisana nizovska promenljva "+varDecl.getVarName()+" tipa "+ dataType(varNode.getType()), varDecl);
    	varDeclCount++;
	}
    
    
    
    
    
    
    public void visit(TypeSingle type) {
    	Obj typeNode = TabExtended.find(type.getTypeName());
    	
    	if(typeNode == TabExtended.noObj) {
    		report_error("Nije pronadjen tip "+type.getTypeName()+" u tabeli simbola", null);
    		type.struct = TabExtended.noType;
    	}else {
    		if(Obj.Type == typeNode.getKind()) {
    			report_trace("Pronadjen tip "+type.getTypeName()+","+typeNode.getName(), type);
    			type.struct = typeNode.getType();
    		}else {
    			report_error("Greska: Ime "+ type.getTypeName()+" ne predstavlja tip", type);
    			type.struct = TabExtended.noType;
    		}
    	}
    }
    
    
    
    
    
    // !!! Metode ---------------------------------------------------------------------------------------
    public void visit(NonVoidMethodTypeAndName methodTypeName) {
    	
    	currentMethod = TabExtended.insert(Obj.Meth, methodTypeName.getName(), methodTypeName.getType().struct);
    	methodTypeName.obj = currentMethod;
    	TabExtended.openScope();
    	report_info("Obrada funkcije "+methodTypeName.getName(),methodTypeName);
    }
    
    public void visit(VoidMethodTypeAndName methodTypeName) {
    	
    	currentMethod = TabExtended.insert(Obj.Meth, methodTypeName.getName(), TabExtended.noType);
    	methodTypeName.obj = currentMethod;
    	TabExtended.openScope();
    	report_info("Obrada funkcije "+methodTypeName.getName(), methodTypeName);
    }
    
    public void visit(MethodDecl methodDecl) {
    	TabExtended.chainLocalSymbols(currentMethod);
    	TabExtended.closeScope();
    	
    	currentMethod = null;
    }
    
    
    
 //!!! Designator -----------------------------------------------------------------------------------------------------------------------------------
    /**
     * Context check: Provera da li je simbol prethodno deklarisan, i da li je var/const tipa
     */
    public void visit(DesignatorVarIdent designatorIdent) {
    	Obj obj = TabExtended.find(designatorIdent.getName());
    	if(obj == TabExtended.noObj){
			report_error("Greska na liniji " + designatorIdent.getLine()+ " : ime "+designatorIdent.getName()+" nije deklarisano! ", null);
    	}else if(obj.getKind() == Obj.Con ) {
    		DumpSymbolTableVisitorBool stv = new DumpSymbolTableVisitorBool();
			obj.accept(stv);
    		report_info(stv.getOutput()+"; Konstanta " + designatorIdent.getName()+" tipa "+ dataType(obj.getType())  +" se koristi ",designatorIdent);
    	}else if(obj.getKind() == Obj.Var ){
    		DumpSymbolTableVisitorBool stv = new DumpSymbolTableVisitorBool();
			obj.accept(stv);
			report_info(stv.getOutput()+"; Promenljiva "+designatorIdent.getName()+" tipa "+ dataType(obj.getType())  +" se koristi ",designatorIdent);
    	}else {
    		report_error("Greska na liniji " + designatorIdent.getLine()+ " : ime "+designatorIdent.getName()+" nije deklarisano kao promenljiva ili konstanta! ", null);
    	}
    	designatorIdent.obj = obj;
    }
    
    /**
     * Context check: Provera da li je simbol prethodno deklarisan, i da li je nizovskog tipa
     */
    public void visit(DesignatorArrayIdent designatorIdent) {
    	
    	
    	
    	// designatorIdent.getExpr() da je tipa int
    	if( designatorIdent.getExpr().struct != TabExtended.intType ){
    		report_error(designatorIdent.getDesignatorArrayIdentName().getName() + "[Expr]: Expr nije tipa int!",designatorIdent);
    		designatorIdent.obj = TabExtended.noObj;
    		return;
    	}
    	Obj obj = designatorIdent.getDesignatorArrayIdentName().obj;
    	if(obj == TabExtended.noObj){
			report_error("Greska na liniji " + designatorIdent.getLine()+ " : ime "+designatorIdent.getDesignatorArrayIdentName().getName()+" nije deklarisano! ", null);
			obj = TabExtended.noObj;
    	}else if( obj.getType().getKind() == Struct.Array ){
    		DumpSymbolTableVisitorBool stv = new DumpSymbolTableVisitorBool();
			obj.accept(stv);
			report_info(stv.getOutput()+"; Promenljiva "+designatorIdent.getDesignatorArrayIdentName().getName()+" tipa "+ dataType(obj.getType())  +" se koristi ",designatorIdent);
			
			//Ovo zanci da je sve ok, pravim obj cvor elementa
			obj = new Obj(Obj.Elem, "Elem of " + designatorIdent.getDesignatorArrayIdentName().getName(), obj.getType().getElemType() );
			
    	}
    	else {
    		report_error("Greska na liniji " + designatorIdent.getLine()+ " : ime "+designatorIdent.getDesignatorArrayIdentName().getName()+" nije deklarisano kao nizovska promenljiva! ", null);
    		obj = TabExtended.noObj;
    	}
    	
    	designatorIdent.obj = obj;
    }
    
    public void visit(DesignatorArrayIdentName arrayName) {
    	arrayName.obj = TabExtended.noObj;
    	Obj obj = TabExtended.find(arrayName.getName());
    	
    	if(obj == TabExtended.noObj){
			report_error("Greska na liniji " + arrayName.getLine()+ " : ime "+arrayName.getName()+" nije deklarisano! ", null);
			obj = TabExtended.noObj;
    	}else if( obj.getType().getKind() != Struct.Array ){
    		report_error("Greska na liniji " + arrayName.getLine()+ " : ime "+arrayName.getName()+" nije deklarisano kao nizovska promenljiva! ", null);
    		obj = TabExtended.noObj;
    	}
    	
    	arrayName.obj = obj;
    	
    }
    
    /**
     * 
     * Func: Check if struct is the same type or array with elements of same type
     */
	private boolean checkIfType(Struct s, Struct type, boolean acceptArray ) {
		if( s == type || acceptArray && s.getKind()==Struct.Array && s.getElemType() == type ) return true;
		else return false;
	}
	private boolean checkIfType(Struct s, Struct type ) {
		return checkIfType(s, type, true);
	}
    
    /**
     * Context check: Provera da li je Designator var ili element niza;
     * 		Provera da li je Expr kompatibilan pri dodeli sa Designator
     */
    public void visit(DesignStmtAssign designStmt) {
    	if( designStmt.getDesignator().obj == TabExtended.noObj ) { //onda nije prosao proveru u Designator(nije niz ni var ni const)
    		//vec je reportovan error u Designator
    		return;
    	}
    	
    	if( !designStmt.getExpr().struct.assignableTo( designStmt.getDesignator().obj.getType() ) ){
    		report_error("Expr sa desne jednakosti strane nije assignable levoj strani", designStmt);
    	}
    	
    	
    }
    
    /**
     * Context check: Provera da li je Designator var ili element niza;
     * 		Provera da li je Designator int
     */
    public void visit(DesignStmtIncrement designStmt) {
    	if( designStmt.getDesignator().obj == TabExtended.noObj ) { //onda nije prosao proveru u Designator(nije niz ni var ni const)
    		//vec je reportovan error u Designator
    		return;
    	}    	
    	if( !checkIfType(designStmt.getDesignator().obj.getType() , TabExtended.intType) )
    	{	report_error("Designator nije tipa Int!", designStmt);	}
    }
    
    /**
     * Context check: Provera da li je Designator var ili element niza;
     * 		Provera da li je Designator int
     */
	public void visit(DesignStmtDecrement designStmt) {
		if( designStmt.getDesignator().obj == TabExtended.noObj ) { //onda nije prosao proveru u Designator(nije niz ni var ni const)
    		//vec je reportovan error u Designator
    		return;
    	}
		if( !checkIfType(designStmt.getDesignator().obj.getType() , TabExtended.intType) )
    	{	report_error("Designator nije tipa Int!", designStmt);	}
	}
	

//Statements-----------------------------------------------------------------------------------------------------------------------
	/**
     * Context check: Provera da li je Designator var ili element niza;
     * 		Provera da li je Designator int ili cahr ili bool
     */
	public void visit(ReadStmt statement) {
		if( statement.getDesignator().obj == TabExtended.noObj ) {return;} //nije var/const/arr[elem] ; greska je vec prijavljena u designator_Ident
		
		
		if( !checkIfType(statement.getDesignator().obj.getType() , TabExtended.intType) &&
				!checkIfType(statement.getDesignator().obj.getType() , TabExtended.charType) &&
				!checkIfType(statement.getDesignator().obj.getType() , TabExtended.boolType)
			)
    	{	report_error("Designator nije tipa Int,Char ili Bool!", statement);	}
	}
	
	/**
     * Context check: Provera da li je Expr int char ili bool ili niz nekog od tih tipova
     */
	public void visit(PrintStmt statement) {
		if( !checkIfType(statement.getExpr().struct, TabExtended.intType) && !checkIfType(statement.getExpr().struct, TabExtended.boolType) && !checkIfType(statement.getExpr().struct, TabExtended.charType) ) {
			report_error("print(Expr): Expr nije tipa Int,Char ili Bool!", statement);
		}
		printCallCount++;
	}
	
	
//Expr----------------------------------------------------------------------------------------------------
	
	/**
     * Context check: Provera da li je Expr int
     */
	public void visit(NegativeExpr expr) {
		if( checkIfType(expr.getAddopTermList().struct, TabExtended.intType) ) {
			expr.struct = expr.getAddopTermList().struct; //propagira tip
		}else {
			report_error("[Expr=-Term]: Term nije tipa int", expr);
			expr.struct = TabExtended.noType;
		}
	}
	
	public void visit(NoSignExpr expr) {
		expr.struct = expr.getAddopTermList().struct; //propagira tip
	}
	
	/** Context check Expr<=>Expr+Term:  Provera da li su Expr i Term tipa int    */
	public void visit(AddopListTerm expr) {
		expr.struct = TabExtended.noType; //ako se desi greska ostavlja noType
		if( expr.getAddopTermList().struct != TabExtended.intType ) report_error("Expr+Term: Expr nije tipa int", expr);
		else if( expr.getTerm().struct != TabExtended.intType ) report_error("Expr+Term: Term nije tipa int", expr);
		else expr.struct = TabExtended.intType; //propagira tip
	}
	//Expr<=>Term
	public void visit(SingleTerm expr) {
		expr.struct = expr.getTerm().struct; //propagira tip
	}
	//Term<=>Factor
	public void visit(NoMullop term) {
		term.struct = term.getFactor().struct; //propagira tip
	}
	/** Context check Term<=>Term*Factor:  Provera da li su Term i Factor tipa int    */
	public void visit(MullopList term) {
		term.struct = TabExtended.noType; //ako se desi greska ostavlja noType
		if( term.getTerm().struct != TabExtended.intType ) report_error("Term*Factor: Term nije tipa int", term);
		else if( term.getFactor().struct != TabExtended.intType ) report_error("Term*Factor: Factor nije tipa int", term);
		else term.struct = TabExtended.intType; //propagira tip
	}

//Factor------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	/** Context check Factor<=>new Type [Expr]:  Provera da li je Expr tipa int    */
	public void visit(NewFactor factor) {
		factor.struct = TabExtended.noType; //ako se desi greska ostavlja noType
		if( factor.getExpr().struct != TabExtended.intType ) report_error("Factor<=>new Type [Expr]: Expr nije tipa int", factor);
		else factor.struct = new Struct(Struct.Array, factor.getType().struct ); //propagira tip
	}
	
	/** Context check Factor<=>range(Expr): Provera da li je Expr tipa int    */
	public void visit(RangeFactor factor) {
		factor.struct = TabExtended.noType; //ako se desi greska ostavlja noType
		if( factor.getExpr().struct != TabExtended.intType ) report_error("Factor<=>range(Expr): Expr nije tipa int", factor);
		else factor.struct = new Struct(Struct.Array, TabExtended.intType ); //propagira tip
	}//TODO treba da vraca niz, a ja ovde propagiram tip kao da je jedan
	
	public void visit(DesignatorFactor factor) {
		factor.struct = factor.getDesignator().obj.getType(); //propagira tip
	}
	
	public void visit(AnyConstFactor factor) {
		factor.struct = factor.getAnyConst().obj.getType(); //propagira tip
	}
	
	public void visit(ParenthExprFactor factor) {
		factor.struct = factor.getExpr().struct; //propagira tip
	}

	
    public void visit(AnyConstBool boolLiteral) {
    	boolLiteral.obj = new Obj(Obj.Con,"literal",TabExtended.boolType);
    	boolLiteral.obj.setAdr( boolLiteral.getBool_cnst() ? 1 : 0 );
    	boolLiteral.obj.setLevel(0);
    	report_trace("Bool literal '" + boolLiteral.getBool_cnst() + "' ", boolLiteral);
	}

	public void visit(AnyConstNum numLiteral) {
		numLiteral.obj = new Obj(Obj.Con,"literal",TabExtended.intType);
		numLiteral.obj.setAdr( numLiteral.getNum() );
		numLiteral.obj.setLevel(0);
		report_trace("Numeric literal '" + numLiteral.getNum() + "'.", numLiteral);
	}

	public void visit(AnyConstChar charLiteral) {
		charLiteral.obj = new Obj(Obj.Con, "literal", TabExtended.charType);
		charLiteral.obj.setAdr( charLiteral.getChr() );
		charLiteral.obj.setLevel(0);
		report_trace("Character literal '" + charLiteral.getChr() + "'.", charLiteral);
	}
	
	
	
	
	
	// TODO NestedStatementList

	
	
	public void visit(MaxStmt maxStmt) {
		Struct s = maxStmt.getDesignator().obj.getType();
		if( s.getKind()!= Struct.Array && s.getElemType()!=TabExtended.intType ) {
			report_error("max(Designator): Designator nije tipa Array[Int], vec "+dataType(s) , maxStmt);
		}
		
	}
	
	
	public void visit(LabelNameStmt labelStmt) {
		Obj o = TabExtended.find(labelStmt.getLabelName());
		if(o!=TabExtended.noObj)
			report_error("Ime za labelu se vec koristi", labelStmt);
		else {
			o = TabExtended.insert(Obj.Con, labelStmt.getLabelName(),TabExtended.intType);
			o.setAdr(Code.pc);
			o.setLevel(0);
			
			report_info("Definisana labela "+labelStmt.getLabelName()+ ":"+Code.pc, labelStmt);
		}
	}
	
	

    
}

