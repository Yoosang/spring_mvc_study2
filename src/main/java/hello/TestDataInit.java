package hello;

import hello.itemservice.domain.beanvalidationitem.BeanValidationItem;
import hello.itemservice.domain.beanvalidationitem.BeanValidationItemRepository;
import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final BeanValidationItemRepository beanValidationItemRepository;
    private final ItemRepository itemRepository;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
        beanValidationItemRepository.save(new BeanValidationItem("itemA", 10000, 10));
        beanValidationItemRepository.save(new BeanValidationItem("itemB", 20000, 20));
    }

}
