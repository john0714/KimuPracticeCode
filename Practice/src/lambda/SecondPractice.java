package lambda;

import java.util.function.*;

// 전 페이지에서 알아본 기본적인 람다 인터페이스들 : Predicate, Function, Consumer, Supplier

// 확장형 람다 인터페이스
public class SecondPractice {
	public static void main(String args[]) {
		/*
		 * java.util.function.BiConsumer
		 * 임의 형태의 입력값<T>와 <U>를 받고, 출력은 하지 않는 형태의 Interface
		 */
		BiConsumer<String, String> BC = (x,y) -> System.out.println(x+","+y);
		BC.accept("first", "second");

		/*
		 * java.util.function.DoubleFunction
		 * java.util.function.Function<T,R>에서 확장한 람다식으로써, 입력과 출력 모두 double형태로 받는 람다식
		 */
		DoubleFunction DF = (a) -> a*0.1;
		System.out.println("DF Test for a ="+ DF.apply(10));
		
		/*
		 * java.util.function.LongToDoubleFunction
		 * java.util.function.Function<T,R>에서 확장한 람다식으로써, 입력은 long으로 받고 출력은 double형태로 리턴하는 람다식
		 */
		LongToDoubleFunction IToDLambda = a -> a; //(입력 -> 출력)
		System.out.println("LongToDoubleFunction test for a ="+ IToDLambda.applyAsDouble(10));
		
		/*
		 * java.util.function.ObjIntConsumer
		 * 첫번째 파라미터는 객체(Object), 두번째 파라미터는 int형을 받는 람다식의 선언
		 */
		ObjIntConsumer OC = (a,b) -> { System.out.println("Object a="+a);
									   System.out.println("Integer b="+b);};
		OC.accept("TestString", 100);
		
		/*
		 * java.util.function.ToDoubleBiFunction<T,U>
		 * 첫번째 파라미터는 <T>형태(임의), 두번째 파라미터는 <U>형태(임의)를 받아, 결과값으로는 double형태의 값을 리턴하는 람다식
		 */
		ToDoubleBiFunction<String, String> tdbLambda = (a,b) -> a.length()+b.length();
		System.out.println("ToDoubleBiFunction test="+ tdbLambda.applyAsDouble("abc", "def"));
		
		/*
		 * java.util.function.BinaryOperator<T>
		 * 같은 <T>형태의 두개의 파라미터를 받아서 람다식의 계산식대로 계산하고, 계산후의 결과값또한<T>형태로 리턴하는 람다식
		 */
		BinaryOperator<String> binOp = (a,b) -> a+","+b;
		System.out.println("BinaryOperator test for a and b = "+binOp.apply("Hello", "World"));
		
		/*
		 * java.util.function.UnaryOperator<T>
		 * java.util.function.Function<T,R>에서 확장한 람다식으로써, <T>형태의 입력값을 받아 <T>형태의 출력값을 리턴한다.
		 */
		UnaryOperator<String> UO = (x) -> x.toUpperCase(); // 소문자를 대문자로 변경하는 toUpperCase()함수
		System.out.println("UnaryOperator test = "+ UO.apply("hello"));
		
		/*
		 * 다른 함수형 언어에서는 이러한 람다식을 처리하기 위한 특별한 인터페이스 형태를 몰라도 되지만,
		 * Java에서는 입력값의 개수, 형태와 출력값의 형태에 따른 명확한 인터페이스의 이름을 알고 있어야 코드상에서의 활용이 가능
		 * → 인터페이스니까 그냥 쓰고싶은 인터페이스 만들어서 Annnotation붙인 뒤에 써도 괜찮지 않나?
		 * 처음에는 어색하더라고 사용하다보면 명확한 입출력에 대한 정의가 매력적으로 느껴질 수도 있다...고함
		 */ 
	}
}
