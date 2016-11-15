package com.james.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.Aspects;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import com.jcabi.aspects.Loggable;

 @Aspect
public class MethodLogger {
	 
     private static Foo obj;
     
     @Pointcut("call(* com.james..*(int)) && args(i) && if()") // must qualify
     public static boolean logOperation( int i, JoinPoint jp, JoinPoint.StaticPart staticPart, JoinPoint.EnclosingStaticPart esjp ) {
    	 String logStr = " message from PointCut!! ";
    	 System.out.println(logStr + ":" + i);
    	 return i > 0;
     }
 
     
     @Before("logOperation(anInt, jp, staticPart, enc)")
     public void callFromFoo(int anInt, JoinPoint  jp,
    		 				 JoinPoint.StaticPart staticPart,
             				 JoinPoint.EnclosingStaticPart  enc) {
    	 System.out.println(" Called at @Before('logOperation()') ");
         System.out.println("thisJoinPoint : " +  jp.getArgs().toString()
       		  + " staticPartt : "  +  staticPart
        		  + " thisEnclosingJoinPointStaticPart : "  +  enc);
         System.out.println(getClass().getName());
         Method [] methods = getClass().getDeclaredMethods();  // .getMethods(); 
         
         obj = Aspects.aspectOf(Foo.class, MethodLogger.class );
         
         for( Method method : methods) {
        // if( method.getName().startsWith("po") ) {
        		System.out.println("Method name = " +  method.getName());
        //	}
            if( method.getName().startsWith("aspect") ) {
            	System.out.println("Class name = " +  method.getClass().getName()
            			+ " : " + method.getClass().getClass().getClass().getName());
         	}
         }
         
         
         
     }	     
     
     
     
	 /*
   //  @Pointcut("execution(* power(..))") // must qualify
	 @Pointcut("call(* com.james..*(..))")
     public void logOperation(){}
  
     @Before("logOperation()")
     public void callFromFoo(JoinPoint jPoint,
             				 JoinPoint.StaticPart thisJoinPointStaticPart,
             				 JoinPoint.EnclosingStaticPart thisEnclosingJoinPointStaticPart) {
    	 System.out.println(" Called at @Before('logOperation()') ");
         System.out.println("thisJoinPoint : " + jPoint.getArgs().toString()
        		 + " thisJoinPointStaticPart : "  + thisJoinPointStaticPart
        		  + " thisEnclosingJoinPointStaticPart : "  + thisEnclosingJoinPointStaticPart);
    	 
     }	 
	   */      
     
     
	 /*
		@Around("execution(* *(..)) && @annotation(Loggable)")
		public Object around(ProceedingJoinPoint point) {
			long start = System.currentTimeMillis();

			Object result = null;
			try {
				result = point.proceed();
			} catch (Throwable e) {
				e.printStackTrace();
			}
			String logStr = String.format( "#%s(%s): %s in %d milisecs",
		    	      MethodSignature.class.cast(point.getSignature()).getMethod().getName(),
		    	      point.getArgs().toString(),
		    	      result,
		    	      System.currentTimeMillis() - start);
	
		    System.out.println(logStr);
			return result;
		}
		*/
	 
	 
	 
}
