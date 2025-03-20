# microjava-compiler
This project is an implementation of a compiler for the MicroJava language, a simple programming language based on Java, with additional functionality as required by the Programming Translators 1 course at the School of Electrical Engineering, University of Belgrade. The project expands the MicroJava compiler by adding support for the range function. Detailed project text can be found in Serbian in ```pp1_2023_2024_jul.pdf```, and specification of MicroJava language in ```mikrojava_2023_2024_jan.pdf```.

## Implementation Overview  

The compiler consists of three main components:  

### 1. Lexical Analysis  
The **lexical analyzer (scanner)** is responsible for reading the source code and converting it into a sequence of tokens. This phase:  
- Uses regular expressions to define valid tokens in MicroJava.  
- Recognizes keywords, identifiers, operators, and literals.  
- Handles whitespace and comments, discarding unnecessary characters.  
- Reports lexical errors for invalid symbols.  

### 2. Parsing  
The **parser** processes the token stream to build a syntax tree according to the **MicroJava grammar**. This phase:  
- Implements a **recursive descent parser** to analyze program structure.  
- Verifies syntactic correctness by ensuring statements and expressions follow defined rules.  
- Extends the original MicroJava grammar to support new language constructs.  
- Reports syntax errors with clear messages indicating the location of the issue.  

### 3. Code Generation  
The **code generator** translates the parsed program into an intermediate or executable representation. This phase:  
- Performs **semantic analysis** to ensure variables, types, and expressions are correctly used.  
- Generates **bytecode** or a low-level representation suitable for execution.  
- Implements additional processing for new language features introduced in the project.  
