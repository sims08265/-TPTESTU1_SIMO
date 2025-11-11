package com.stjean.operation;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;



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
	void testFactoriel() throws IllegalParamISIException {
		System.out.println("Je teste la methode factoriel.");
		
        assertEquals(1, op.factoriel(1), "Factoriel de 1 doit être 1");
        assertEquals(2, op.factoriel(2), "Factoriel de 2 doit être 2");
        assertEquals(6, op.factoriel(3), "Factoriel de 3 doit être 6");
        assertEquals(120, op.factoriel(5), "Factoriel de 5 doit être 120");
       
        
		assertThrows(IllegalParamISIException.class, ()->op.factoriel(-3));
		
	}
	
	static Stream<org.junit.jupiter.params.provider.Arguments> fournirTableaux() {
        return Stream.of(
            org.junit.jupiter.params.provider.Arguments.of(new int[]{5, 2, 1}, new int[]{1, 2, 5}),
            org.junit.jupiter.params.provider.Arguments.of(new int[]{3, 3, 1}, new int[]{1, 3, 3}),
            org.junit.jupiter.params.provider.Arguments.of(new int[]{9, 7, 8}, new int[]{7, 8, 9})
        );
    }


    @ParameterizedTest
    @MethodSource("fournirTableaux")
    void testTrier(int[] input, int[] expected) {
        assertArrayEquals(expected, op.trier(input));
    }
    
    
    static Stream<org.junit.jupiter.params.provider.Arguments> fournirTableaux1() {
        return Stream.of(
            org.junit.jupiter.params.provider.Arguments.of(new double[]{1, 2, 3, 4, 5}, 5),
            org.junit.jupiter.params.provider.Arguments.of(new double[]{10, 7, 9}, 10),
            org.junit.jupiter.params.provider.Arguments.of(new double[]{-3, -7, -1, -5}, -1),
            org.junit.jupiter.params.provider.Arguments.of(new double[]{42}, 42)
        );
    }


    @ParameterizedTest
    @MethodSource("fournirTableaux1")
    void testTrouverMax(double[] input, double attendu) {
        assertEquals(attendu, op.maxNumba(input));
    }
}
