package com.shanghai.templateapp.injections.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author chensong
 * @date 2019/4/19 15:42
 */

@Qualifier
@Documented
@Retention(RUNTIME)
public @interface DecodeURL {
}
