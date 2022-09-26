package hello.itemservice.domain.beanvalidationitem;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BeanValidationDeliveryCode {

    private String code;
    private String displayName;

}
