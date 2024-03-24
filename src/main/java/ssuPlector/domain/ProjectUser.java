package ssuPlector.domain;

import jakarta.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ssuPlector.domain.category.Part;

@Entity
@Getter
@Builder
public class ProjectUser {
    @Id
    @GeneratedValue
    @Column(name = "project_user_id", columnDefinition = "bigint")
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private Part part1; //개발 참여 분야 1

    @Enumerated(EnumType.STRING)
    private Part part2; //개발 참여 분야 2

    @Column(columnDefinition = "tinyint(1)")
    private boolean isTeamLeader;
}
