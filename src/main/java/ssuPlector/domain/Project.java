package ssuPlector.domain;

import jakarta.persistence.*;
import lombok.*;
import ssuPlector.domain.category.Category;
import ssuPlector.domain.category.DevLanguage;
import ssuPlector.domain.category.DevTools;
import ssuPlector.domain.category.TechStack;

import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id", columnDefinition = "bigint")
    private Long id;
    @Column(columnDefinition = "varchar(30)")
    private String name;
    @OneToMany(mappedBy = "project")
    private List<Image> imageList;
    @OneToMany(mappedBy = "project")
    private List<ProjectUser> projectUserList;
    @Column(columnDefinition = "varchar(101)")
    private String shortIntro;
    @Column(columnDefinition = "text")
    private String longIntro;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(columnDefinition = "bigint")
    private Long hits;
    private String infoPageLink;
    private String webLink;
    private String appLink;
    @Enumerated(EnumType.STRING)
    private List<DevLanguage> languageList;
    @Enumerated(EnumType.STRING)
    private List<DevTools> devToolList;
    @Enumerated(EnumType.STRING)
    private List<TechStack> techStackList;


    //==연관관계 메서드==//
    public void addProjectUser(ProjectUser projectUser) {
        projectUser.setProject(this);
        this.projectUserList.add(projectUser);
    }

    public void addImage(Image image) {
        image.setProject(this);
        this.imageList.add(image);
    }
}
