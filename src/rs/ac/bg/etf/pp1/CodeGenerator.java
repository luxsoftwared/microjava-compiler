package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

import java.util.ArrayList;
import java.util.HashMap;

import rs.ac.bg.etf.pp1.CounterVisitor.*;

public class CodeGenerator extends VisitorAdaptor {
	private int mainPc;
	
	private int printWidth=0;
	public int getMainPc() {
		return mainPc;
	}
	

	
	private void printOneElementW(Struct s, int width) {
		Code.loadConst(width);
		if( s == TabExtended.intType ) {
			Code.put(Code.print);
		}else if( s == TabExtended.charType ) {
			Code.put(Code.bprint);
		}else if( s == TabExtended.boolType ){
			Code.put(Code.print);
		}
	}
	
	private void printOneElementW(Struct s) {
		if( s == TabExtended.intType ) {
			Code.loadConst(4);
			Code.put(Code.print);
		}else if( s == TabExtended.charType ) {
			Code.loadConst(1);
			Code.put(Code.bprint);
		}else if( s == TabExtended.boolType ){
			Code.loadConst(1);
			Code.put(Code.print);
		}
	}
	
	private void printOneElem(Struct s) {
		if(printWidth == 0)
			printOneElementW( s );
		else 
			printOneElementW( s, printWidth );
	}
	
	
	private void printArray(Struct elemType) {
		
		
		// ...adr
		Code.put(Code.dup);
		Code.put(Code.dup); // ...adr,adr,adr
		Code.loadConst(-1); // ...adr,adr,adr,indx
		
		int loopStart = Code.pc;// ...adr,adr,adr,indx
		Code.loadConst(1);						
		Code.put(Code.add);		//...adr,adr,adr,indx++
		Code.put(Code.dup_x2); // ...adr,indx, adr,adr,indx
		Code.put(Code.dup_x2); // ...adr,indx,indx, adr,adr,indx

		if(elemType.getKind() == Struct.Char)
			Code.put(Code.baload);
		else
			Code.put(Code.aload);
		// ...adr,indx,indx,adr,elem
		printOneElem( elemType );
		//	...adr,indx,indx,adr
		Code.put(Code.dup_x2); 
		Code.put(Code.dup_x2); // ...adr,adr,adr, indx,indx,adr
		Code.put(Code.arraylength);	// ...adr,adr,adr, indx,indx,LEN
		Code.loadConst(1);	// ...adr,adr,adr, indx,indx,LEN, 1
		Code.put(Code.sub);	// ...adr,adr,adr, indx,indx,LEN-1
		Code.putFalseJump(Code.ge, loopStart); // if indx<len-1 jmp to adr
		// ...adr,adr,adr, indx
		Code.put(Code.pop);
		Code.put(Code.pop);
		Code.put(Code.pop);
		Code.put(Code.pop);
		
	}
	
	public void visit(PrintStmt printStmt) { //TODO PrintStmtNumConstParamOptional
		if( printStmt.getExpr().struct.getKind() == Struct.Array )
			printArray(printStmt.getExpr().struct.getElemType() );
		else 
			printOneElem( printStmt.getExpr().struct );
			
	}
	
	public void visit(PrintStmtConstParam printArg) {
		/*Obj tmp = new Obj(Obj.Con, "<tmp>", TabExtended.intType);
		tmp.setAdr(printArg.getN1());
		Code.load(tmp);
		*/
		printWidth = printArg.getN1();
	}
	
	public void visit(PrintStmtNoParam printArg) {
		printWidth = 0;
	}
	
	
	
	public void visit(ReadStmt rdStmt) {
		if ( rdStmt.getDesignator().obj.getType() == TabExtended.charType )
			Code.put(Code.bread);
		else
			Code.put(Code.read);
		Code.store(rdStmt.getDesignator().obj);
	}
	
	public void visit(AnyConstFactor factor) {
		Obj obj = new Obj(Obj.Con,"$",factor.struct);
		obj.setLevel(0);
		obj.setAdr(factor.getAnyConst().obj.getAdr());
		
		Code.load(obj);
	}
	
	public void visit(VoidMethodTypeAndName methodTypeName) {
		
		if("main".equalsIgnoreCase(methodTypeName.getName()) ) {
			mainPc = Code.pc;
		}
		methodTypeName.obj.setAdr(Code.pc);
		// Collect arguments and local variables
		SyntaxNode methodNode = methodTypeName.getParent();
	
		VarCounter varCnt = new VarCounter();
		methodNode.traverseTopDown(varCnt);
		
		FormParamCounter fpCnt = new FormParamCounter();
		methodNode.traverseTopDown(fpCnt);
		
		// Generate the entry
		Code.put(Code.enter);
		Code.put(fpCnt.getCount());
		Code.put(fpCnt.getCount() + varCnt.getCount());
	}
	
	public void visit(NonVoidMethodTypeAndName methodTypeName) {
	

	}
	
	public void visit(MethodDecl methodDecl){
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	
	//Dodela vrednosti
	public void visit(DesignStmtAssign designAssign){
		Code.store(designAssign.getDesignator().obj);
	}
/*	
	public void visit(DesignatorVarIdent designator){
		SyntaxNode parent = designator.getParent();
		
		if(DesignStmtAssign.class != parent.getClass() ){
			Code.load(designator.obj);
		}
	}
*/		
	public void visit(DesignatorArrayIdentName designator){
		Code.load(designator.obj);
	}

	public void visit(DesignatorFactor designatorFact){
		Code.load(designatorFact.getDesignator().obj);
	}
	
	
	public void visit(AddopListTerm addOpTerm){
		if( addOpTerm.getAddop().getClass() == PlusOp.class ) {
			Code.put(Code.add);
		}else {
			Code.put(Code.sub);
		}
	}
	
	public void visit(NegativeExpr negExpr){
		Code.put(Code.neg);
	}
	
	public void visit(MullopList mulOpList){
		if( mulOpList.getMullop().getClass() == Multiply.class ) {
			Code.put(Code.mul);
		}else if( mulOpList.getMullop().getClass() == Divide.class ) {
			Code.put(Code.div);
		}else {
			Code.put(Code.rem);
		}
	}
	
	public void visit( DesignStmtIncrement incStmt) {
		if (incStmt.getDesignator().obj.getKind() == Obj.Elem)
			Code.put(Code.dup2);
		Code.load(incStmt.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(incStmt.getDesignator().obj);
	}
	
	public void visit( DesignStmtDecrement decStmt) {
		if (decStmt.getDesignator().obj.getKind() == Obj.Elem) //astore prima 3 param
			Code.put(Code.dup2);
		Code.load(decStmt.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(decStmt.getDesignator().obj);
	}
	
	
	
	//New Array
	public void visit(NewFactor newArr) {
		Code.put(Code.newarray);
		if (newArr.getType().struct.equals(TabExtended.charType))
			Code.put(0);
		else
			Code.put(1);
	}
	
	public void visit(RangeFactor factor) {
		// ...len
		Code.put(Code.newarray);	
		Code.put(1);	// newarray b=1
		// ..adr
		Code.put(Code.dup);		// ...adr,adr
		Code.loadConst(-1);		// ...adr,adr, -1
		
		int loopStart=Code.pc;	// ...adr,adr, inx
		Code.loadConst(1);	// ...adr,adr, inx, 1
		Code.put(Code.add);	// ...adr,adr, inx++
		Code.put(Code.dup_x2);
		Code.put(Code.dup_x2);	// ...inx,inx, adr,adr,inx
		Code.put(Code.dup);		// ...inx,inx, adr,adr,inx, inx
		Code.loadConst(1);
		Code.put(Code.add);	// ...inx,inx, adr,adr,inx, inx+1
		Code.put(Code.astore); // ...inx,inx, adr
		
		Code.put(Code.dup_x2);
		Code.put(Code.dup_x2);	// ...adr,adr, inx,inx,adr
		Code.put(Code.arraylength);	// ...adr,adr, inx,inx,len
		Code.loadConst(1);
		Code.put(Code.sub);	// ...adr,adr, inx,inx,len-1
		
		Code.putFalseJump(Code.ge, loopStart); // if indx<len-1 jmp to adr
		// ...adr,adr, inx
		Code.put(Code.pop);
		Code.put(Code.pop);
		// ...adr  -> kao return value
		
		
		
		
		
	}
	
	private void findMax() {
		//..arr
		Code.loadConst(0);
		Code.loadConst(0); // arr,max,ind
		
		int loopStart= Code.pc;
		
		Code.put(Code.dup_x2);
		Code.put(Code.pop);
		Code.put(Code.dup_x2);
		Code.put(Code.pop); //...max,ind,arr

		Code.put(Code.dup_x2);
		Code.put(Code.dup_x1);
		Code.put(Code.pop); //.....arr, max,arr,ind
		Code.put(Code.dup_x2);//.....arr,ind max,arr,ind
		
		Code.put(Code.aload); //.....arr,ind max,val
		Code.put(Code.dup2);//.....arr,ind max,val, max, val
		
		//DOVDE SAM POSLAO
		int NotfoundNewMax = Code.pc+1;
		Code.putFalseJump(Code.lt, 0);	// ..arr,ind max,val  if max>=val
		//foundNewMax
		Code.put(Code.dup_x1);
		Code.put(Code.pop);	// ..arr,ind ,val,max
		//NotFoundNewMax
		Code.fixup(NotfoundNewMax);
		Code.put(Code.pop); // ..arr,ind max
		
		Code.put(Code.dup_x2);	
		Code.put(Code.pop);		// ..max,arr,ind
		Code.put(Code.dup_x1);
		Code.put(Code.pop);	// ..max,ind,arr
		Code.put(Code.dup_x2);	// ..arr, max,ind,arr
		Code.put(Code.arraylength); // ..arr, max,ind,len
		Code.put(Code.dup_x1);
		Code.put(Code.pop);	// ..arr, max,len,ind
		Code.loadConst(1);
		Code.put(Code.add); 	// ..arr, max,len,ind+1
		Code.put(Code.dup_x1);// ..arr, max,ind+1, len,ind+1
		Code.putFalseJump(Code.le, loopStart);	// ..arr, max,ind+1			if len > ind
		// ..arr, max,ind+1	
		Code.put(Code.pop);
		//..arr,max
		printOneElem(TabExtended.intType);
		Code.put(Code.pop);
		//..
	}
	
/*
..adr


Start:
.......arr,max,ind
....max,ind,arr
......arr,max,ind,arr
.....arr, max,arr,ind
.....arr,ind max,arr,ind


........arr,ind,max,adr,ind 


..max,adr,ind 
ALOAD
..max,val

..max,val,max,val
|JMP gt if gt 
|..max,val => ..val
||JMP endIf
||__gt:
 |	..max,val => ..max
 |__Endif:
..max
................arr....arr,ind,max
.................arr....max,len,ind
..len,ind 
PUT(1),ADD
..len,ind+1
X1
..ind+1,len,ind+1
jmp Start if gt 
...................arr...max,ind+1
 */
	
	
	public void visit(MaxStmt maxStmt) {
		Code.load(maxStmt.getDesignator().obj);
		findMax();
	}
	HashMap<String,Integer> labelAdress= new HashMap<String,Integer>();	
	HashMap<String,ArrayList<Integer>> labelFix= new HashMap<String,ArrayList<Integer>>();	
	
	public void visit(GoToStmt gotoStmt) {
		String labName = gotoStmt.getLabelName();
		if( labelAdress.containsKey(labName)) {
			Code.putJump(labelAdress.get(labName));
			System.out.println("Goto "+labName);
		}else {
			if( !labelFix.containsKey(labName) )
				labelFix.put(labName, new ArrayList<Integer>());
			labelFix.get(labName).add(Code.pc+1);
			Code.putJump(0);
		}
		
	}

	public void visit(LabelNameStmt labelStmt) {
		String labName = labelStmt.getLabelName();
		if( !labelAdress.containsKey(labName) ) {
			labelAdress.put(labName, Code.pc);
			for(int adr:labelFix.get(labName)) {
				Code.fixup(adr);
			}
		}
			
	}
	
	
}
