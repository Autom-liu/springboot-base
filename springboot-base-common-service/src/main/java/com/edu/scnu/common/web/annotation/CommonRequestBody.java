package com.edu.scnu.common.web.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 使用该注解标记的请求参数同时支持json和x-www-form-urlencoded 表单格式
 * @author Autom
 * @date 2020年2月1日
 * @version 0.1
 *
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CommonRequestBody {

}
