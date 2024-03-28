package ssuPlector.domain.category;

import com.fasterxml.jackson.annotation.JsonCreator;

import ssuPlector.global.exception.GlobalException;
import ssuPlector.global.response.code.GlobalErrorCode;

public enum Part {
    FRONTEND,
    BACKEND,
    FULLSTACK,
    PM,
    PO,
    BUSINESS_ANALYST,
    SYSTEMS_ANALYST,
    SOFTWARE_ARCHITECT,
    QUALITY_ASSURANCE_ENGINEER,
    DEVOPS_ENGINEER,
    TECHNICAL_WRITER,
    DATABASE_ADMINISTRATOR,
    NETWORK_ENGINEER,
    SECURITY_ANALYST;

    @JsonCreator
    public static Part fromPart(String partString) {
        for (Part part : Part.values()) {
            if (part.name().equals(partString)) {
                return part;
            }
        }
        throw new GlobalException(GlobalErrorCode.PART_NOT_FOUND);
    }
}
