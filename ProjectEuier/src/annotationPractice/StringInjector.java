package annotationPractice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Custom Annotation 인터페이스 지정

@Target(ElementType.FIELD) //필드(멤버 변수)에 선언
@Retention(RetentionPolicy.RUNTIME) //컴파일 이후에도 JVM에 의해 참조가 가능함
public @interface StringInjector {
	String value() default "This is StringInjector";  // default Method를 사용해서 interface 내용 선입력
}
