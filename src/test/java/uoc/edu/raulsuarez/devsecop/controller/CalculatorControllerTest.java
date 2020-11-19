package uoc.edu.raulsuarez.devsecop.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import uoc.edu.raulsuarez.devsecop.dto.Calcul;
import uoc.edu.raulsuarez.devsecop.dto.Operations;
import uoc.edu.raulsuarez.devsecop.service.CalculatorService;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CalculatorControllerTest {

    @Autowired
    CalculatorController controller;

    @Mock
    Model model;

    @Test
    public void indexTest() {
        assertThat(controller.index(model)).isEqualTo("index");
    }

    @Test
    public void calculateTest() throws Exception {
        Calcul calcul = new Calcul();
        calcul.setOperation(Operations.MUL);
        calcul.setItems(new ArrayList<Integer>() {{ add(1); add(3); }});
        assertThat(controller.calculate(calcul, model)).isEqualTo("index");
    }
}
