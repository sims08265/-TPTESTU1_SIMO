package com.stjean.operation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestOperationMathematique {

	private final OperationMathematique op = new OperationMathematique();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testEstPositif() {
		System.out.println("Je teste la methode estPositif.");
		
		assertTrue(op.estPositif(2),"Ce nombre n'est pas pair");


	}

	@Test
	void testFactoriel() throws FactorielInvalidException {
		System.out.println("Je teste la methode factoriel.");
		
        assertEquals(1, op.factoriel(1), "Factoriel de 1 doit être 1");
        assertEquals(2, op.factoriel(2), "Factoriel de 2 doit être 2");
        assertEquals(6, op.factoriel(3), "Factoriel de 3 doit être 6");
        assertEquals(120, op.factoriel(5), "Factoriel de 5 doit être 120");
       
        
		assertThrows(FactorielInvalidException.class, ()->op.factoriel(-1));
		
	}

}
