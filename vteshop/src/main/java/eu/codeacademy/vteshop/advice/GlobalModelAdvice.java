package eu.codeacademy.vteshop.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import static eu.codeacademy.vteshop.helper.OperationConstants.*;

@ControllerAdvice
public class GlobalModelAdvice {

    @ModelAttribute("currency")
    public char currency() {
        return '€';
    }


}
