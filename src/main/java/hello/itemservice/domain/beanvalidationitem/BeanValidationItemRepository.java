package hello.itemservice.domain.beanvalidationitem;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BeanValidationItemRepository {

    private static final Map<Long, BeanValidationItem> store = new HashMap<>(); //static
    private static long sequence = 0L; //static

    public BeanValidationItem save(BeanValidationItem item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public BeanValidationItem findById(Long id) {
        return store.get(id);
    }

    public List<BeanValidationItem> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, BeanValidationItem updateParam) {
        BeanValidationItem findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }

}
