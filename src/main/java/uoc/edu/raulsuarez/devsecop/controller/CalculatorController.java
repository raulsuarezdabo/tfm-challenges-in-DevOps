package uoc.edu.raulsuarez.devsecop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uoc.edu.raulsuarez.devsecop.dto.Calcul;
import uoc.edu.raulsuarez.devsecop.service.CalculatorService;

import java.util.HashMap;
import java.util.Map;


@Controller
public class CalculatorController {

    private static final Logger logger = LoggerFactory.getLogger(CalculatorController.class);

    private CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @RequestMapping(value = {"/", "/index"}, method = {RequestMethod.GET})
    public String index(Model model) {
        logger.info("main page access");
        model.addAttribute("params", new Calcul());
        model.addAttribute("operations", getOperationsAvailable());
        return "index";
    }

    @RequestMapping(value = {"/index"}, method = {RequestMethod.POST})
    public String calculate(@ModelAttribute("params") Calcul params, Model model) throws Exception {
        model.addAttribute("operations", getOperationsAvailable());
        Integer result = this.calculatorService.calculate(params);
        model.addAttribute("result", result);
        logger.info("rendering the page with operation '{}' with arguments {} and result: {}",
                params.getOperation(), params.getItems().toString(), result);
        return "index";
    }

    private Map<String, String> getOperationsAvailable() {
        Map<String,String> operations = new HashMap<>();
        operations.put("addition", "+");
        operations.put("subtraction", "-");
        operations.put("multiplication", "x");
        operations.put("division", "/");
        return operations;
    }
}
