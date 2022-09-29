package hello.itemservice.domain.beanvalidationitem;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BeanValidationItemRepositoryV4 {

    private static final Map<Long, BeanValidationItemV4> store = new HashMap<>(); //static
    private static long sequence = 0L; //static

    public BeanValidationItemV4 save(BeanValidationItemV4 item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public BeanValidationItemV4 findById(Long id) {
        return store.get(id);
    }

    public List<BeanValidationItemV4> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, BeanValidationItemV4 updateParam) {
        BeanValidationItemV4 findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }

}
