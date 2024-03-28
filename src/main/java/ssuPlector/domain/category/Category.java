package ssuPlector.domain.category;

import com.fasterxml.jackson.annotation.JsonCreator;

import ssuPlector.global.exception.GlobalException;
import ssuPlector.global.response.code.GlobalErrorCode;

public enum Category {
    SERVICE,
    SECURITY,
    FINANCE,
    PLATFORM,
    GAME,
    MOBILE_APP,
    WEB_APPLICATION,
    DATA_ANALYTICS,
    AI,
    ML,
    IOT,
    CLOUD_COMPUTING,
    BLOCKCHAIN,
    E_COMMERCE,
    HEALTHCARE_IT,
    ED_TECH,
    SOCIAL_MEDIA,
    CRM,
    ERP,
    BI,
    CPS,
    AR,
    VR,
    AUTOMATION,
    ROBOTICS,
    OTHER;

    @JsonCreator
    public static Category fromCategory(String categoryString) {
        for (Category category : Category.values()) {
            if (category.name().equals(categoryString)) {
                return category;
            }
        }
        throw new GlobalException(GlobalErrorCode.CATEGORY_NOT_FOUND);
    }
}
