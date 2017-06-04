package com.utills.JDBCUtils;

import java.lang.annotation.*;

/**
 * Created by Tnecesoc on 2017/5/29.
 */
@Target(ElementType.FIELD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLIgnore {
}
