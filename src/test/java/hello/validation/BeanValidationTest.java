package hello.validation;

import hello.itemservice.domain.beanvalidationitem.BeanValidationItem;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class BeanValidationTest {

    @Test
    void beanValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        BeanValidationItem item = new BeanValidationItem();

        item.setItemName(" "); //공백
        item.setPrice(0);
        item.setQuantity(10000);
        Set<ConstraintViolation<BeanValidationItem>> violations = validator.validate(item);
        for (ConstraintViolation<BeanValidationItem> violation : violations) {
            System.out.println("violation=" + violation);
            System.out.println("violation.message=" + violation.getMessage());

        }
    }
}
