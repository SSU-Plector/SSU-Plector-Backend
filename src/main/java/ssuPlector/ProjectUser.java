package ssuPlector;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

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
    @ElementCollection(targetClass = Part.class)
    @CollectionTable(name = "part_list", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private List<Part> partList;
    private boolean isTeamLeader;
}
