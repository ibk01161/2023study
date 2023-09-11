package hello.hellospring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity                         // JPA가 관리하는 엔티티
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)         // PK 매핑
    private Long id;            // 시스템이 저장하는 id (시퀀스)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
