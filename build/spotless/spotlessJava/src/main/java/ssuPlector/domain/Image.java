package ssuPlector.domain;

import jakarta.persistence.*;

import lombok.Getter;

@Entity
@Getter
public class Image {
    @Id
    @GeneratedValue
    @Column(name = "image_id")
    private Long id;

    private String imagePath;
    private boolean isMainImage;
    //    @ManyToOne(fetch = FetchType.LAZY)
    //    @JoinColumn(name = "user_id")
    //    private User user;
    //    @ManyToOne(fetch = FetchType.LAZY)
    //    @JoinColumn(name = "project_id")
    //    private Project project;

}
