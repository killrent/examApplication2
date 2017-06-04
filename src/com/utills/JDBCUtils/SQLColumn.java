package com.utills.JDBCUtils;

import java.lang.annotation.*;

/**
 * Created by Tnecesoc on 2017/5/28.
 */
@Target(ElementType.FIELD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLColumn {

    String value();

}
