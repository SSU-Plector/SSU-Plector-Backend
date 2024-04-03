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
public class Developer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "developer_id")
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

    @Builder.Default
    private long hits = 0;

    @Column(columnDefinition = "varchar(20)")
    private String kakaoId;

    private String githubLink;

    @Column(columnDefinition = "tinyint(1)")
    private boolean isDeveloper;

    @ElementCollection private List<String> linkList;

    @Builder.Default
    @OneToMany(mappedBy = "developer", cascade = CascadeType.ALL)
    private List<Image> imageList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "developer")
    private List<ProjectDeveloper> projectDeveloperList = new ArrayList<>();

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private List<DevLanguage> languageList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private List<DevTools> devToolList;

    @Enumerated(EnumType.STRING)
    private List<TechStack> techStackList;

    // ==연관관계 메서드==//
    public void addProjectDeveloper(ProjectDeveloper projectDeveloper) {
        projectDeveloper.setDeveloper(this);
        if (this.projectDeveloperList == null) this.projectDeveloperList = new ArrayList<>();
        this.projectDeveloperList.add(projectDeveloper);
    }

    public void addImage(Image image) {
        image.setDeveloper(this);
        if (this.imageList == null) this.imageList = new ArrayList<>();
        this.imageList.add(image);
    }

    public boolean getIsDeveloper() {
        return this.isDeveloper;
    }
}
