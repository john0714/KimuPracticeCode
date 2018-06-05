package annotationPractice;

public class AnnotationDemo {
	public static void main(String[] args) throws IllegalAccessException, InstantiationException{
		MyContextContainer demo = new MyContextContainer();
		MyObject obj = demo.get(MyObject.class);  //MyObject.class를 get에 파라메터로 넣음(어노테이션이 들어있는 클래스)
		
		System.out.println(obj.getName()); // "My name is JDM."
		System.out.println(obj.getDefaultValue()); // "This is StringInjector."
		System.out.println(obj.getInvalidType());  // "null"
	}
}
