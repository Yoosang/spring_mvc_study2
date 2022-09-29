package hello.itemservice.domain.beanvalidationitem;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class BeanValidationItemV4 {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public BeanValidationItemV4() {
    }

    public BeanValidationItemV4(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
