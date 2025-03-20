package rs.ac.bg.etf.pp1;

import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.visitors.DumpSymbolTableVisitor;

public class DumpSymbolTableVisitorBool extends DumpSymbolTableVisitor {

	@Override
	public void visitStructNode(Struct structToVisit) {
		if (structToVisit.getKind() == Struct.Bool
				|| structToVisit.getKind() == Struct.Array && structToVisit.getElemType().getKind() == Struct.Bool) {
			switch (structToVisit.getKind()) {
			case Struct.Bool:
				output.append("bool");
				break;
			case Struct.Array:
				output.append("Arr of bool");
			}
		} else
			super.visitStructNode(structToVisit);
	}	
	
	
}
