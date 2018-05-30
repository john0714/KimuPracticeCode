package annotationPractice;

import java.lang.reflect.Field;

public class MyContextContainer {

	public MyContextContainer() {
		// init 
	}
	
	//Annotaion 내용 정의 (Class를 받아와서 T라는 이름으로 사용)
	private <T> T invokeAnnotations(T instance) throws IllegalAccessException {
		Field[] fields = instance.getClass().getDeclaredFields(); //받아온 인스턴스의 필드값을 가져옴(변수)
		for( Field field : fields ) {
			StringInjector annotaion = field.getAnnotation(StringInjector.class); // 가져온 필드값에서 Annoatation을 가져옴(StringInjector.class에서 지정된 Annotaion만!)
			if(annotaion != null && field.getType() == String.class) {  // Annotaion 값 체크(값이 null이 아니고 필드(멤버변수)의 타입이 String인가
				field.setAccessible(true);
				field.set(instance, annotaion.value());  // 값이 타당하다면 field에 annotation값을 입력(즉, Annotaion의 값을 Field(멤버 변수)에 넣어주는 작업이다.)
			}
		}
		// 이 작업은 Annotation이 없을 때 까지 반복된다.
		return instance; // instance를 리턴한다!!!! 즉, 낮은 복사를 이용하여 get,set작업으로 변수에 값을 넣은 후 그 instance를 리턴한 것! 그렇기에 그 값은 AnnotaionDemo.java의 코드로 돌아가서 MyObject 객체 인스턴스에 들어가게된다!
	}
	
	public <T> T get(Class class1) throws IllegalAccessException, InstantiationException { 
		T instance = (T) class1.newInstance(); // 그냥 공백 Type Parameter(=T, 메소드 타입은 제네릭으로 지정)로 받아온 class를 인스턴스로 변경해서 작업함(생성자 없이 newInstance 사용)  
		instance = invokeAnnotations(instance);
		return instance;
	}

}
