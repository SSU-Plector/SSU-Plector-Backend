package ssuPlector.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import org.hibernate.annotations.SQLRestriction;

import lombok.*;
import ssuPlector.domain.category.DevLanguage;
import ssuPlector.domain.category.DevTools;
import ssuPlector.domain.category.Part;
import ssuPlector.domain.category.SocialType;
import ssuPlector.domain.category.TechStack;
import ssuPlector.dto.request.DeveloperDTO.DeveloperRequestDTO;
import ssuPlector.dto.request.DeveloperDTO.DeveloperUpdateRequestDTO;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLRestriction("is_deleted = false")
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

    @Builder.Default private long hits = 0;

    @Column(columnDefinition = "varchar(20)")
    private String kakaoId;

    private String githubLink;

    @Builder.Default private boolean isDeveloper = true;

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

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    private Part part1;

    @Enumerated(EnumType.STRING)
    private Part part2;

    @Column(columnDefinition = "tinyint(1)")
    @Builder.Default
    private boolean isDeleted = false;

    private LocalDateTime deletedAt;

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

    public void softDelete() {
        this.isDeleted = true;
        this.deletedAt = LocalDateTime.now();
    }

    public void setStartDeveloper(
            DeveloperRequestDTO requestDTO,
            ArrayList<DevLanguage> newLanguage,
            ArrayList<DevTools> newDevTool,
            ArrayList<TechStack> newTechStack) {
        this.shortIntro = requestDTO.getShortIntro();
        this.university = requestDTO.getUniversity();
        this.major = requestDTO.getMajor();
        this.studentNumber = requestDTO.getStudentNumber();
        this.kakaoId = requestDTO.getKakaoId();
        this.githubLink = requestDTO.getGithubLink();
        this.part1 = requestDTO.getPart1();
        this.part2 = requestDTO.getPart2();
        this.languageList = newLanguage;
        this.devToolList = newDevTool;
        this.techStackList = newTechStack;
    }

    public void updateDeveloper(
            DeveloperUpdateRequestDTO requestDTO,
            ArrayList<DevLanguage> newLanguage,
            ArrayList<DevTools> newDevTool,
            ArrayList<TechStack> newTechStack) {
        this.shortIntro = requestDTO.getShortIntro();
        this.university = requestDTO.getUniversity();
        this.major = requestDTO.getMajor();
        this.studentNumber = requestDTO.getStudentNumber();
        this.githubLink = requestDTO.getGithubLink();
        this.part1 = requestDTO.getPart1();
        this.part2 = requestDTO.getPart2();
        this.languageList = newLanguage;
        this.devToolList = newDevTool;
        this.techStackList = newTechStack;
    }

    public void recoverDeleted() {
        this.isDeleted = false;
    }
}
