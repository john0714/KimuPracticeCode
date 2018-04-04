package com.higasi.booksystem;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
//@EnableAspectJAutoProxy
public class MethodStartLoggingAspect {
	@Before("execution(* *..*HomeController.*(..))")
	public void strLog(JoinPoint jp) {
		System.out.println("メソッド始まる前" + jp.getThis());
	}
	
	@AfterReturning(value = "execution(* *..*HomeController.*(..))", returning = "homeeeee")//return値の名前を作る
	public void endLog(JoinPoint jp, String homeeeee) {
		System.out.println("メソッド終わった後" + jp.getSignature() + " 戻り値=" + homeeeee);
	}
	
	@AfterThrowing(value = "execution(* *..*HomeController.*(..))", throwing="e")//throw値の名前を作る
	public void ExceptionLog(JoinPoint jp, RuntimeException e) {
		System.out.println("メソッド終わった後" + jp.getSignature());
		e.getStackTrace();
	}
	
	@After("execution(* *..*HomeController.*(..))")
	public void endLog(JoinPoint jp) {//Overloading
		System.out.println("メソッド終わった後" + jp.getSignature());
	}
	
	@Around("execution(* *..*HomeController.*(..))")
	public Object Log(ProceedingJoinPoint jp) throws Throwable{//Overloading
		System.out.println("メソッド開始" + jp.getSignature());
		try {
			Object result = jp.proceed();//return
			System.out.println("メソッド正常終了:" + jp.getSignature() + " 戻り値=" + result);
			return result;//戻り値を再び戻るのもできます。
		} catch (Exception e ) {
			System.out.println("メソッド異常終了:" + jp.getSignature());
			e.printStackTrace();
			throw e;
		}
	}
}
