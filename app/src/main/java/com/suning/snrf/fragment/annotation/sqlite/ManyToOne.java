package com.suning.snrf.fragment.annotation.sqlite;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * public class Parent{
 *   	private int id;
 *      @OneToMany(manyColumn = "parentId")
 *	    private OneToManyLazyLoader<Parent ,Child> children;
 *	}
 *	
 *	public class Child{
 *	    private int id;
 *	    private String text;
 *	    @ManyToOne(column = "parentId")
 *	    private  Parent  parent;
 *	}
 *	
 * @author 13081405
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ManyToOne {
	String column() default "";
}
