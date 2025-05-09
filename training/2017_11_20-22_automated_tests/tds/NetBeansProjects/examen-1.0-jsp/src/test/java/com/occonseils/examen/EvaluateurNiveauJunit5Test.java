/*
 * LIBRE DE DROIT
 */
package com.occonseils.examen;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author olivier
 */
public class EvaluateurNiveauJunit5Test {
    
    
    
    public EvaluateurNiveauJunit5Test() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test(expected = Exception.class)
    public void evaluerNiveau_GIVEN_Param_Cours_NonNumeric_EXPECTED_ExceptionBadFormat() throws Exception {
        EvaluateurNiveau.evaluerNiveau("AAA", "100");
    }
     
    @Test(expected = Exception.class)
    public void evaluerNiveau_GIVEN_Param_Examen_NonNumeric_EXPECTED_ExceptionBadFormat() throws Exception {
        EvaluateurNiveau.evaluerNiveau("10", "sdrkjghkjsdn");
    }
 
    @Test(expected = Exception.class)
    public void evaluerNiveau_GIVEN_Param_Cours_min1_Examen_0_EXPECTED_ExceptionNotInRange() throws Exception {
        EvaluateurNiveau.evaluerNiveau("-1", "0");
    }
    
    @Test(expected = Exception.class)
    public void evaluerNiveau_GIVEN_Param_Cours_0_Examen_min1_EXPECTED_ExceptionNotInRange() throws Exception {
        EvaluateurNiveau.evaluerNiveau("0", "-1");
    }

    @Test(expected = Exception.class)
    public void evaluerNiveau_GIVEN_Param_Cours_26_Examen_0_EXPECTED_ExceptionNotInRange() throws Exception {
        EvaluateurNiveau.evaluerNiveau("26", "0");
    }

    @Test(expected = Exception.class)
    public void evaluerNiveau_GIVEN_Param_Cours_25_Examen_101_EXPECTED_ExceptionNotInRange() throws Exception {
        EvaluateurNiveau.evaluerNiveau("25", "101");
    }
    
    @Test
    public void evaluerNiveau_GIVEN_Param_Cours_0_Examen_0_EXPECTED_Niveau_D_Sum_0() throws Exception {
        assertThat(EvaluateurNiveau.evaluerNiveau("0", "0")).isEqualTo("D");
    }
    
    @Test
    public void evaluerNiveau_GIVEN_Param_Cours_10_Examen_19_EXPECTED_Niveau_D_Sum_29() throws Exception {
        assertThat(EvaluateurNiveau.evaluerNiveau("10", "19")).isEqualTo("D");
    }
    
    @Test
    public void evaluerNiveau_GIVEN_Param_Cours_11_Examen_19_EXPECTED_Niveau_C_Sum_30() throws Exception {
        assertThat(EvaluateurNiveau.evaluerNiveau("11", "19")).isEqualTo("C");
    }
    
    @Test
    public void evaluerNiveau_GIVEN_Param_Cours_11_Examen_19_EXPECTED_Niveau_C_Sum_49() throws Exception {
        assertThat(EvaluateurNiveau.evaluerNiveau("11", "19")).isEqualTo("C");
    }

    @Test
    public void evaluerNiveau_GIVEN_Param_Cours_21_Examen_29_EXPECTED_Niveau_B_Sum_50() throws Exception {
        assertThat(EvaluateurNiveau.evaluerNiveau("21", "29")).isEqualTo("B");
    }

    @Test
    public void evaluerNiveau_GIVEN_Param_Cours_20_Examen_49_EXPECTED_Niveau_B_Sum_69() throws Exception {
        assertThat(EvaluateurNiveau.evaluerNiveau("20", "49")).isEqualTo("B");
    }

    @Test
    public void evaluerNiveau_GIVEN_Param_Cours_20_Examen_50_EXPECTED_Niveau_A_Sum_70() throws Exception {
        assertThat(EvaluateurNiveau.evaluerNiveau("20", "50")).isEqualTo("A");
    }
    
    @Test
    public void evaluerNiveau_GIVEN_Param_Cours_25_Examen_75_EXPECTED_Niveau_A_Sum_70() throws Exception {
        assertThat(EvaluateurNiveau.evaluerNiveau("25", "75")).isEqualTo("A");
    }
    
}
