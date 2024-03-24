package ssuPlector.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Builder
public class Image {
    @Id
    @GeneratedValue
    @Column(name = "image_id", columnDefinition = "bigint")
    private Long id;
    private String imagePath;
    @Column(columnDefinition = "tinyint(1)")
    private boolean isMainImage;
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

}
