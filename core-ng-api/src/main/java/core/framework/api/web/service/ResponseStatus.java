package core.framework.api.web.service;

import core.framework.api.http.HTTPStatus;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author neo
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface ResponseStatus {
    HTTPStatus value() default HTTPStatus.OK;
}
