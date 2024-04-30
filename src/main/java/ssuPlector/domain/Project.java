package ssuPlector.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import lombok.*;
import ssuPlector.domain.category.Category;
import ssuPlector.domain.category.DevLanguage;
import ssuPlector.domain.category.DevTools;
import ssuPlector.domain.category.TechStack;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long id;

    @Column(columnDefinition = "varchar(30)")
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Image> imageList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "project")
    private List<ProjectDeveloper> projectDeveloperList = new ArrayList<>();

    @Column(columnDefinition = "varchar(101)")
    private String shortIntro;

    @Column(columnDefinition = "text")
    private String longIntro;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Builder.Default
    @Column(name = "hits", columnDefinition = "BIGINT DEFAULT 0")
    private long hits = 0;

    private String githubLink;
    private String infoPageLink;
    private String webLink;
    private String appLink;

    @Enumerated(EnumType.STRING)
    private List<DevLanguage> languageList;

    @Enumerated(EnumType.STRING)
    private List<DevTools> devToolList;

    @Enumerated(EnumType.STRING)
    private List<TechStack> techStackList;

    // ==연관관계 메서드==//
    public void addProjectDeveloper(ProjectDeveloper projectDeveloper) {
        projectDeveloper.setProject(this);
        if (this.projectDeveloperList == null) this.projectDeveloperList = new ArrayList<>();
        this.projectDeveloperList.add(projectDeveloper);
    }

    public void addImage(Image image) {
        image.setProject(this);
        if (this.imageList == null) this.imageList = new ArrayList<>();
        this.imageList.add(image);
    }
}
