package hello.itemservice.domain.beanvalidationitem;

public enum BeanValidationItemType {

    BOOK("도서"), FOOD("음식"), ETC("기타");

    private final String description;

    BeanValidationItemType(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

}
