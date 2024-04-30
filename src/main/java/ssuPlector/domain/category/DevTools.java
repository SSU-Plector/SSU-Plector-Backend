package ssuPlector.domain.category;

import com.fasterxml.jackson.annotation.JsonCreator;

import ssuPlector.global.exception.GlobalException;
import ssuPlector.global.response.code.GlobalErrorCode;

public enum DevTools {
    NOTION,
    GITHUB,
    SLACK,
    JIRA,
    MS_TEAMS,
    JANDI,
    ZOOM,
    GOOGLE_MEET,
    GOOGLE_WORKSPACE,
    TRELLO,
    FIGMA,
    NAVER_WORKS,
    FLOW,
    YAMMER,
    AZIT,
    COLLABY,
    CUP,
    DROPBOX_BUSINESS,
    COLLABORATOR,
    STUDIO_3T,
    SQL_SENTRY,
    DB_SCHEMA,
    VS_CODE,
    INTELLIJ,
    ECLIPSE,
    VISUAL_STUDIO,
    PYCHARM,
    GOOGLE_SPREADSHEET,
    ASANA,
    GOOGLE_DRIVE,
    DROPBOX,
    RESCUETIME,
    WORKPLACE;

    @JsonCreator
    public static DevTools fromDevTools(String devToolsString) {
        for (DevTools devTools : DevTools.values()) {
            if (devTools.name().equals(devToolsString)) {
                return devTools;
            }
        }
        throw new GlobalException(GlobalErrorCode.DEV_TOOLS_NOT_FOUND);
    }
}
