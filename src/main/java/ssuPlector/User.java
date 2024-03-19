package ssuPlector;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    private String name;
    private String email;
    private String password;
    private boolean isDeveloper;
    private String phoneNumber;
    private String kakaoId;
    private String githubId;
    private List<String> linkList;

    @ElementCollection(targetClass = Part.class)
    @CollectionTable(name = "part_list", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private List<Part> partList;
}
