package ssuPlector.domain.category;

import com.fasterxml.jackson.annotation.JsonCreator;

import ssuPlector.global.exception.GlobalException;
import ssuPlector.global.response.code.GlobalErrorCode;

public enum TechStack {
    DJANGO,
    RUBY_ON_RAILS,
    FLASK,
    SPRING,
    ANGULAR,
    REACT,
    VUE,
    EMBER,
    LARAVEL,
    SYMFONY,
    EXPRESS,
    NODE_JS,
    DOT_NET,
    XAMARIN,
    IONIC,
    GRAFANA,
    ASP_NET_CORE,
    SERVERLESS,
    MICROSERVICES,
    KUBERNETES,
    DOCKER,
    AWS,
    AZURE,
    GOOGLE_CLOUD,
    FIREBASE,
    HEROKU,
    DIGITALOCEAN,
    VAGRANT,
    ANSIBLE,
    TERRAFORM,
    JENKINS,
    TRAVIS_CI,
    CIRCLECI,
    GITLAB_CI;

    @JsonCreator
    public static TechStack fromTechStack(String techStackString) {
        for (TechStack techStack : TechStack.values()) {
            if (techStack.name().equals(techStackString)) {
                return techStack;
            }
        }
        throw new GlobalException(GlobalErrorCode.TECH_STACK_NOT_FOUND);
    }
}
