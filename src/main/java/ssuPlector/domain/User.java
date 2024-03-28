package ssuPlector.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import lombok.*;
import ssuPlector.domain.category.DevLanguage;
import ssuPlector.domain.category.DevTools;
import ssuPlector.domain.category.TechStack;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(columnDefinition = "varchar(20)")
    private String name;

    @Column(columnDefinition = "varchar(101)")
    private String shortIntro;

    @Column(columnDefinition = "varchar(30)")
    private String university;

    @Column(columnDefinition = "varchar(30)")
    private String major;

    @Column(columnDefinition = "varchar(20)")
    private String studentNumber;

    @Column(columnDefinition = "varchar(50)")
    private String email;

    private long hits;

    @Column(columnDefinition = "varchar(20)")
    private String kakaoId;

    private String githubLink;

    @Column(columnDefinition = "tinyint(1)")
    private Boolean isDeveloper;

    @ElementCollection private List<String> linkList;

    @OneToMany(mappedBy = "user")
    private List<Image> imageList;

    @OneToMany(mappedBy = "user")
    private List<ProjectUser> projectUserList;

    @Enumerated(EnumType.STRING)
    private List<DevLanguage> languageList;

    @Enumerated(EnumType.STRING)
    private List<DevTools> devToolList;

    @Enumerated(EnumType.STRING)
    private List<TechStack> techStackList;

    // ==연관관계 메서드==//
    public void addProjectUser(ProjectUser projectUser) {
        projectUser.setUser(this);
        if (this.projectUserList == null) this.projectUserList = new ArrayList<>();
        this.projectUserList.add(projectUser);
    }

    public void addImage(Image image) {
        image.setUser(this);
        if (this.imageList == null) this.imageList = new ArrayList<>();
        this.imageList.add(image);
    }
}
