package ssuPlector.domain;

import java.util.List;

import jakarta.persistence.*;

import lombok.Getter;
import ssuPlector.domain.category.DevLanguage;
import ssuPlector.domain.category.DevTools;
import ssuPlector.domain.category.TechStack;

@Entity
@Getter
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String name;
    private String mainImage;
    private List<String> imageList;
    private String shortIntro;
    private String university;
    private String major;
    private String studentNumber;
    private String email;
    private String kakaoId;
    private String githubLink;
    private boolean isDeveloper;
    private List<String> linkList;

    @OneToMany(mappedBy = "user")
    private List<ProjectUser> projectUserList;

    @Enumerated(EnumType.STRING)
    private List<DevLanguage> languageList;

    @Enumerated(EnumType.STRING)
    private List<DevTools> devToolList;

    @Enumerated(EnumType.STRING)
    private List<TechStack> techStackList;
}
