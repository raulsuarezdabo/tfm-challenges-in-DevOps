package uoc.edu.raulsuarez.devsecop.service;



import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uoc.edu.raulsuarez.devsecop.dto.Calcul;
import uoc.edu.raulsuarez.devsecop.dto.Operations;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CalculatorServiceTest {

    @Autowired
    private CalculatorService calculatorService;

    private static Integer[] integers;

    @BeforeAll
    public static void init() {
        integers = new Integer[] {1,2,3};
    }

    @Test
    public void contextLoads() throws Exception {
        assertThat(this.calculatorService).isNotNull();
    }

    @Test
    public void additionTestOK() {
        Integer result = calculatorService.addition(0,integers);
        assertThat(result).isEqualTo(Integer.valueOf(6));
    }

    @Test
    public void substractionTestOK() {
        Integer result = calculatorService.subtraction(0,integers);
        assertThat(result).isEqualTo(Integer.valueOf(-6));
    }

    @Test
    public void multiplicationTestOK() {
        Integer result = calculatorService.multiplication(1,integers);
        assertThat(result).isEqualTo(Integer.valueOf(6));
    }

    @Test
    public void divisionTestOK() {
        Integer result = calculatorService.division(1,integers);
        assertThat(result).isEqualTo(Integer.valueOf(0));
    }

    @Test
    public void calculateAddition() throws Exception {
        Calcul calcul = new Calcul();
        calcul.setOperation(Operations.ADD);
        calcul.setItems(new ArrayList<Integer>() {{ add(1); add(3); }});
        Integer result = calculatorService.calculate(calcul);
        assertThat(result).isEqualTo(Integer.valueOf(4));
    }

    @Test
    public void calculateSubtraction() throws Exception {
        Calcul calcul = new Calcul();
        calcul.setOperation(Operations.SUB);
        calcul.setItems(new ArrayList<Integer>() {{ add(1); add(3); }});
        Integer result = calculatorService.calculate(calcul);
        assertThat(result).isEqualTo(Integer.valueOf(-2));
    }

    @Test
    public void calculateMultiplication() throws Exception {
        Calcul calcul = new Calcul();
        calcul.setOperation(Operations.MUL);
        calcul.setItems(new ArrayList<Integer>() {{ add(1); add(3); }});
        Integer result = calculatorService.calculate(calcul);
        assertThat(result).isEqualTo(Integer.valueOf(3));
    }

    @Test
    public void calculateDivide() throws Exception {
        Calcul calcul = new Calcul();
        calcul.setOperation(Operations.DIV);
        calcul.setItems(new ArrayList<Integer>() {{ add(2); add(2); }});
        Integer result = calculatorService.calculate(calcul);
        assertThat(result).isEqualTo(Integer.valueOf(1));
    }

    @Test
    public void calculateError() throws Exception {
        Calcul calcul = new Calcul();
        calcul.setOperation("test");

        Exception exception = assertThrows(
                Exception.class, () -> calculatorService.calculate(calcul)
        );
        assertEquals(exception.getMessage(), "Undefined operation");
    }

}
