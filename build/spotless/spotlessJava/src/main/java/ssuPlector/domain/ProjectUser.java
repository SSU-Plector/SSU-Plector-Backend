package ssuPlector.domain;

import jakarta.persistence.*;

import lombok.Getter;
import ssuPlector.domain.category.Part;

@Entity
@Getter
public class ProjectUser {
    @Id
    @GeneratedValue
    @Column(name = "project_user_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private Part part1;

    @Enumerated(EnumType.STRING)
    private Part part2;

    private boolean isTeamLeader;
}
