package uoc.edu.raulsuarez.devsecop.service;

import org.springframework.validation.annotation.Validated;
import uoc.edu.raulsuarez.devsecop.dto.Calcul;

/**
 * @author Raul Suarez
 * Service interface responsible to apply the selected operation
 */
public interface CalculatorService {

    /**
     * Method that allow to add two or more arguments.
     * @param x     first argument of the operation
     * @param yza   collection of arguments to attach to the operation
     * @return      operations result
     */
    public Integer addition(Integer x, Integer... yza);

    /**
     * Method that allow to subtract two or more arguments.
     * @param x     first argument of the operation
     * @param yza   collection of arguments to attach to the operation
     * @return      operations result
     */
    public Integer subtraction(Integer x, Integer... yza);

    /**
     * Method that allow to multiply two or more arguments.
     * @param x     first argument of the operation
     * @param yza   collection of arguments to attach to the operation
     * @return      operations result
     */
    public Integer multiplication(Integer x, Integer... yza);

    /**
     * Method that allow to divide two or more arguments.
     * @param x     first argument of the operation
     * @param yza   collection of arguments to attach to the operation
     * @return      operations result
     */
    public Integer division(Integer x, Integer... yza);

    /**
     * Method to match the appropriate operation.
     * @param   calcul dto contains the query
     * @return  result of the operation
     */
    public Integer calculate(Calcul calcul) throws Exception;

}
