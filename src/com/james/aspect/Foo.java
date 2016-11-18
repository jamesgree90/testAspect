package com.james.aspect;

import com.jcabi.aspects.Loggable;

// public class Foo implements FooInterface {
public class Foo  {
	 //  @Loggable
	  public int power(int x, int p) {
	    return (int) Math.pow(x, p);
	  }
	
	  public int power(int x) {
		    return (int) Math.pow(x, x);
	  }
	  
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Foo foo = new Foo();
		
		foo.power(3, 7);
		foo.power(4);	
		foo.power(11); // Nov. 18. 2016 
	}

}
