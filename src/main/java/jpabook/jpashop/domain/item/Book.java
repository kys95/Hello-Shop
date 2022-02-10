package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")    //single-table이므로 db에 저장할때 구분하기 위함, 안해두면 Book으로 들어감
@Getter @Setter
public class Book extends Item {

    private String author;
    private String isbn;

}
