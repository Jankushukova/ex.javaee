package utilities;

/**
 * @author Assylkhan
 * on 25.07.2019
 * @project E-CommerceSHO
 */
public class InputValidator {

    public static boolean validate(String input){
        return input != null && input.trim().length() > 0;
    }

}
