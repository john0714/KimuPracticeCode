package lambda;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/*
 *  람다식은 간단히 말하면 함수를 변수처럼 사용 할 수 있는 개념이다.
 *  JDK8 이전의 자바에는 메소드라는 함수 형태가 존재하지만 객체(혹은 클래스)를 통해서만 접근이 가능하고, 메소드 그 자체를 변수로 사용하지는 못해왔다.
 *  JDK8에서는 함수를 변수처럼 사용 할 수 있기 때문에, 파라미터로 다른 메소드의 인자로 전달 할 수도 있고 리턴값으로 함수를 받을 수도 있다. 
 *  이로써 자바에서도 함수형 프로그래밍 방식으로 코딩을 할 수 있게 되었다.
 */

/*
 * 사실 자바 람다식은 완벽한 함수형 프로그래밍 방식이라고 할 수는 없다. 이유는 자바 람다식은 함수형에 대해서 새로 정의한 것이 아니고,
 * 기존에 존재하는 Interface의 형태를 빌어 람다식을 표현하기 때문이다.
 * 그러므로 함수형 프로그래밍의 장점을 100% 갖지는 못한다.
 */

//익명 클래스를 사용한 예시
public class FirstPractice {
	public static void main(String args[]) {
		
		//기존 익명 클래스 방식
		new Thread(new Runnable(){ // 익명 클래스 선언, 바로 실행하는 Runnable함수
			public void run() {  // 선언된 익명 클래스 안의 메소드
				System.out.println("Annoymous Thread");
			}
		}).start();
		
		//람다 방식
		new Thread(()->System.out.println("Lambda Thread")).start();
		
		/*
		 * () -> System.out.println("Lambda Thread")
		 * 1. ()는 파라미터에 해당하는데, run()이라는 익명 클래스의 메소드가 아무런 Parameter를 취하지 않는다는 의미이다.
		 * 2. ->는 람다식 시작전에 나타나는 람다식 시작 토큰이다. 뒤에는 한줄짜리 람다식 혹은 { }안에 포함단 여러 람다식이 올 수 있다.
		 * 3. System.out.println("Lambda Thread")는 람다식의 내용부분으로 이 안에는 A의 파라미터값이 사용 될 수 있다. 즉, 인수를 사용하는 코드의 내용 부분이다.
		 */
		
		/*
		 * 자바에는 값을 변수로 설정할수 있는 방법이 단 두가지 존재한
		 * 1. 원시형태:primitive -int, long, float, double등
		 * 2. 객체형태:object - calss 혹은 interface
		 * 그럼 람다식은 어떤 형태로 변수로 할당 될 수 있는건가? 답은 객체형태의 interface다.
		 * 이말인 즉슨, 람다식 또한 객체의 형태를 취하고 있다는 말이다.
		 * 예시:위의 Runnable()함수를 Commnad(윈도우 라면 Ctrl)를 누르고 클릭해서 들어가면 내용이 interface형태로 되어있는걸 볼 수 있는데,
		 * 그 위에 @FunctionalInterface(함수형 인터페이스)라는 Annotation이 달려 있는걸 볼 수 있다. 이 Annotation으로 지정된게 람다식으로 사용 가능한 함수라는 뜻(람다 인터페이스 선언)이다.
		 */
		
		//이와 같이 사용 가능하다.
		lambdaTest(() -> System.out.println("input lambda")).run();
		
		// JDK에서 기본적으로 제공하는 여러 람다 인터페이스들(의 일부 예시)
		/*
		 * java.util.function.Predicate
		 * 임의의 타입(T)형태의 객체입력을 받아 그 값이 true인지 false인지를 리턴한다.(인터페이스 내부를 그런식으로 짬)
		 */
		Predicate<String> stringCompare = (String str) -> str.compareTo("abc")==0?true:false;
		System.out.println("Predicate test for abc : " + stringCompare.test("abc"));
		
		/*
		 * java.util.function.Function
		 * 첫번째 임의의 형태의 입력값을 받아 두번째 임의의 형태의 값으로 출력한다.
		 * 출력값의 형태를 자유롭게 지정 할 수 있기 때문에, 상속을 통해 굉장히 다양하게 사용 될 수 있는 람다 인터페이스다.
		 */
		Function<String, Integer> intFunctionLambda = (String str) -> str.compareTo("abc")==0?1:0;
		System.out.println("intFunctionLambda test for abc : " + intFunctionLambda.apply("abc"));
		System.out.println("intFunctionLambda test for abc : " + intFunctionLambda.apply("123"));
		
		Function<String, Float> floatFunctionlambda = (String str) -> str.compareTo("abc") == 0? (float)1.012:(float)0.0123;
		System.out.println("floatFunctionLambda test for abc : " + floatFunctionlambda.apply("abc"));
		
		/*
		 * java.util.Consumer
		 * 임의 형태의 입력값을 받고, 출력은 하지 않는 형태의 인터페이스다.
		 * 대표적으로 사용되는곳은 Collections.sort명령의 인자값이다.(X)
		 * Collections.sort에 사용되는건 java.util.Comparetor 인터페이스다(O)
		 */
		List list = new ArrayList(10);
		list.add(2);
		list.add(1);
		Collections.sort(list, (Integer a, Integer b)->a>b?0:-1); //나중에 넣은 수가 a에 옴. 즉, add 2, add 1 했으면 a = 1, b = 2임
		// Comparater <? super T> c
		// 뒤쪽 Comparater Interface 내용을 바꾸는걸로 정렬 방법을 바꿀수 있다.(람다식 방법을 써서 바꿔봄) 
		// 위의 경우, a>b일 경우 0을 그대로 있게 하며 a>b가 아닐 경우 -1을 리턴하여(a<b라고 return값을 보내서)오름차순으로 정렬 되도록 만든다.  
		System.out.println(list);
		
		/*
		 * java.util.Supllier
		 * 입력값은 따로 없지만, 출력값의 형태를 지정할 수 있다.
		 */
		Supplier<Integer> getSupplierLambda = () -> 123123123;
		System.out.println(getSupplierLambda.get());
		
		/*
		 * java.util.stream.IntStream
		 * 물론 IntStream말고도 여러가지 Stream이 있음
		 * Java8부터 지원하는 stream을 통해 람다식의 형태로 반복문을 만들 수도 있다.(forEach)
		 */
		IntStream.range(0, 10).forEach((int value) -> System.out.println(value));
		
		// 혹은 이런식으로도 구현 가능함(메소드 레퍼런스 사용)
		IntStream.range(1, 100).filter(i -> i <= 50).forEach(System.out::println);
		// 하지만 이런 람다식 형태에서 break;는 사용 불가능하기 때문에 처음 예제와 같은 경우를 하거나 혹은 BreakException을 걸거나 하면됨
		// 위와 같이 filter를 걸더라도 끝까지 도는 작업 자체는 실행하기 때문에 프로그램 성능 저하를 우려하여 그냥 기본 형태의 for구문을 쓰자고 하는 경우도 있다.
	}
	
	//실제로 런타임에 인수로 넣고 return값으로 객체를 받는 형태로 사용할때는 반드시 인터페이스의 형태를 거쳐야한다
	private static Runnable lambdaTest(Runnable runnable) {
		runnable.run();
		return() -> System.out.println("return lambda");
	}
}
