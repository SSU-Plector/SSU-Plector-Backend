package ssuPlector.domain;

import java.util.List;

import jakarta.persistence.*;

import lombok.*;
import ssuPlector.domain.category.Part;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectDeveloper extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_developer_id")
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "developer_id")
    private Developer developer;

    @Column(columnDefinition = "varchar(20)")
    private String name;

    @Enumerated(EnumType.STRING)
    private List<Part> partList; // 개발 참여 분야

    @Column(columnDefinition = "tinyint(1)")
    private boolean isTeamLeader;

    @Column(columnDefinition = "varchar(20)")
    private String kakaoId;

    public boolean getIsTeamLeader() {
        return this.isTeamLeader;
    }

    public void update(Developer developer) {
        this.developer = developer;
    }
}
