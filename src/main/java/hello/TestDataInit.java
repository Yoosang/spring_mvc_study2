package hello;

import hello.itemservice.domain.beanvalidationitem.BeanValidationItem;
import hello.itemservice.domain.beanvalidationitem.BeanValidationItemRepository;
import hello.itemservice.domain.beanvalidationitem.BeanValidationItemRepositoryV4;
import hello.itemservice.domain.beanvalidationitem.BeanValidationItemV4;
import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import hello.itemservice.domain.loginitem.LoginItem;
import hello.itemservice.domain.loginitem.LoginItemRepository;
import hello.itemservice.domain.member.Member;
import hello.itemservice.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final BeanValidationItemRepository beanValidationItemRepository;
    private final ItemRepository itemRepository;
    private final BeanValidationItemRepositoryV4 beanValidationItemRepositoryV4;
    private final LoginItemRepository loginItemRepository;
    private final MemberRepository memberRepository;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));

        beanValidationItemRepository.save(new BeanValidationItem("itemA", 10000, 10));
        beanValidationItemRepository.save(new BeanValidationItem("itemB", 20000, 20));

        beanValidationItemRepositoryV4.save(new BeanValidationItemV4("itemA", 10000, 10));
        beanValidationItemRepositoryV4.save(new BeanValidationItemV4("itemB", 20000, 20));

        loginItemRepository.save(new LoginItem("login itemA", 10000, 10));
        loginItemRepository.save(new LoginItem("login itemB", 20000, 20));

        Member member = new Member();
        member.setLoginId("test");
        member.setPassword("test!");
        member.setName("테스터");
        memberRepository.save(member);

    }

}
