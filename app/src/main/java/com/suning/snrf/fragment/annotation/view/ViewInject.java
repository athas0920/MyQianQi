package com.suning.snrf.fragment.annotation.view;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewInject {
	int id();
	String click() default "";
	String longClick() default "";
	String itemClick() default "";
	String itemLongClick() default "";
	Select select() default @Select(selected="");
}
