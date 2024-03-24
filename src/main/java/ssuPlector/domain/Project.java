package ssuPlector.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import ssuPlector.domain.category.Category;
import ssuPlector.domain.category.DevLanguage;
import ssuPlector.domain.category.DevTools;
import ssuPlector.domain.category.TechStack;

import java.util.List;

@Entity
@Getter
@Builder
public class Project {
    @Id
    @GeneratedValue
    @Column(name = "project_id")
    private Long id;
    private String name;
    @OneToMany(mappedBy = "project")
    private List<Image> imageList;
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
