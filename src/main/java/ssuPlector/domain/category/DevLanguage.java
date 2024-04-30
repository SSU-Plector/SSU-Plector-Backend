package ssuPlector.domain.category;

import com.fasterxml.jackson.annotation.JsonCreator;

import ssuPlector.global.exception.GlobalException;
import ssuPlector.global.response.code.GlobalErrorCode;

public enum DevLanguage {
    ADA,
    ABAP,
    ACTIONSCRIPT,
    APL,
    AWK,
    BASIC,
    C,
    CPP,
    CS,
    COFFEESCRIPT,
    COBOL,
    D,
    DART,
    ELIXIR,
    ERLANG,
    FORTH,
    FORTRAN,
    GO,
    GROOVY,
    HASKELL,
    JAVA,
    JS,
    TS,
    JULIA,
    KOTLIN,
    LISP,
    LUA,
    MATLAB,
    ML,
    OCAML,
    FSHARP,
    PERL,
    PHP,
    PROLOG,
    PYTHON,
    PASCAL,
    R,
    RUBY,
    RUST,
    SCALA,
    SWIFT,
    VB,
    VBDOTNET,
    SQL,
    HTML,
    CSS,
    SCSS,
    XML;

    @JsonCreator
    public static DevLanguage fromDevLanguage(String devLanguageString) {
        for (DevLanguage devLanguage : DevLanguage.values()) {
            if (devLanguage.name().equals(devLanguageString)) {
                return devLanguage;
            }
        }
        throw new GlobalException(GlobalErrorCode.DEV_LANGUAGE_NOT_FOUND);
    }
}
