package ssuPlector.domain;

import java.util.List;

import jakarta.persistence.*;

import lombok.Getter;
import ssuPlector.domain.category.Category;
import ssuPlector.domain.category.DevLanguage;
import ssuPlector.domain.category.DevTools;
import ssuPlector.domain.category.TechStack;

@Entity
@Getter
public class Project {
    @Id
    @GeneratedValue
    @Column(name = "project_id")
    private Long id;

    private String name;
    private String mainImage;
    private List<String> imageList;

    @OneToMany(mappedBy = "project")
    private List<ProjectUser> projectUserList;

    private String shortIntro;
    private String longIntro;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String infoPageLink;
    private String webLink;
    private String appLink;

    @Enumerated(EnumType.STRING)
    private List<DevLanguage> languageList;

    @Enumerated(EnumType.STRING)
    private List<DevTools> devToolList;

    @Enumerated(EnumType.STRING)
    private List<TechStack> techStackList;
}
