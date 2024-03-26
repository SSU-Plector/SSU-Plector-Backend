package ssuPlector.converter;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ssuPlector.domain.User;
import ssuPlector.dto.response.DeveloperDTO.DeveloperDetailDTO;

@Component
public class UserConverter {

    public static DeveloperDetailDTO toDeveloperDetailDTO(User user) {
        return DeveloperDetailDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .shortIntro(user.getShortIntro())
                .university(user.getUniversity())
                .major(user.getMajor())
                .studentNumber(user.getStudentNumber())
                .email(user.getEmail())
                .hits(user.getHits())
                .kakaoId(user.getKakaoId())
                .githubLink(user.getGithubLink())
                .isDeveloper(user.isDeveloper())
                .linkList(user.getLinkList())
                .imageList(user.getImageList())
                .languageList(user.getLanguageList())
                .devToolList(user.getDevToolList())
                .techStackList(user.getTechStackList())
                .projectList(
                        user.getProjectUserList().stream()
                                .map(ProjectConverter::toProjectPreviewDTO)
                                .collect(Collectors.toList()))
                .build();
    }
}
