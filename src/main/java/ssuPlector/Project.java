package ssuPlector;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
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
    private String techStack;
    @ElementCollection(targetClass = Category.class)
    @CollectionTable(name = "category_list", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private List<Category> categoryList;
    private String websiteLink;
    private String appLink;
    private String landingPageLink;
    private String infoPageLink;
}
