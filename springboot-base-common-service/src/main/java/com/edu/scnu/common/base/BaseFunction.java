package com.edu.scnu.common.base;

import java.io.Serializable;
import java.util.function.Function;

/**
 * 实现了序列化的Function，可以用它将lamba和实体字段的对应
 * @author  Autom
 */
public interface BaseFunction<R, T> extends Function<R, T>, Serializable {
}
