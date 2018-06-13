package lambda;

import java.util.*;
import java.util.stream.*;

/*
 * Java8에 람다가 도입되면서 가장 큰영향을 받은 기존의 API는 Collection이다. 
 * Collection에 추가된 forEach()와 stream()은 람다 방식으로 값의 덩어리들을 처리하기 위해 만들어진 API이다.
 */
public class ThirdPractice {
	public static void main(String args[]) {
		/*
		 * forEach
		 * 기존의 List, Map, Set등에서 forEach API가 람다를 파라미터값으로 받는다. 람다식 안에서는 각 엘리먼트에 대한 개별처리를 정의한다.
		 * 기존 : for(SomeObject obj : someObjects) { }
		 * 람다 : someObject.forEach(obj -> { })  람다식 안의 로직은 자기 자신에게 부여된 obj 엘리먼트에 대해서만 값을 처리할 수 있다.
		 */
		
		//List 예제
		List<String> list1 = new ArrayList();
		list1.add("A");
		list1.add("B");
		list1.forEach((x) -> System.out.println("List item = "+x));
		
		//Map 예제
		Map<String, String> map1 = new HashMap();
		map1.put("key1", "val1");
		map1.put("key2", "val2");
		map1.forEach((x,y) -> System.out.println("Map item x="+x+" y="+y));
		
		//Set 에제
		Set<String> set1 = new HashSet();
		set1.add("set1");
		set1.add("set2");
		set1.forEach(x -> System.out.println("Set item x="+x));
		
		/*
		 * Stream
		 * 컬렉션이나 배열을 사용해서 질의 처리를 하려면 각각의 배열요소에 대해 외부 반복을 해줘야함
		 * 스트림 스타일은 외부 반복을 하지않고, 각 개별 Element에 대한 처리로직을 수행할수 있는 여러 함수들을 제공함(of, filter, reduce, map, colleact등)
		 * 이 스트림 함수들은 행동패턴을 기술하는 람다식을 Parameter값으로 받는다.
		 */
		
		//Stream.of({param}) : 배열을 스트림객체로 변경해준다.
		Integer[] ids = {1,2,3,4};
		Stream intStream = Stream.of(ids);
		intStream.forEach(x -> System.out.print(x));
		
		//map() : 하나의 파라미터 값을 인자로 받아서 다른 임의의 형태로 리턴한다.
		Stream floatStream = Stream.of(ids).map(x -> x*10.0);
		floatStream.forEach(x -> System.out.print(x));
		
		//reduce() : 두개의 파라미터 값을 받아 연산 후 다른 형태로 리턴한다. 여기서 앞의 값은 초기값(0)이자 수식의 결과로 나온 리턴값(a)을 의미한다.
		int ret = Stream.of(ids).reduce(0, 
				(a,b) -> {
					System.out.println("lambda a="+a+" b="+b);
					return a+b;
				});
		System.out.println("result = "+ret);
		
		/* 
		 * collect() : 스트림 형태의 흐름값으로부터 사용자가 직접 값을 이용할수 있는 형태를 가지려면 배열 혹은 컬렉션의 형태를 통해 접근할 수 있다.
		 * 그걸 위해 filter, map, reduce후 최종적인 값을 얻기위해 사용하는 함수가 collect함수이다.
		 * 이런 최종값을 얻는 형태를 terminal이라고 하며 collect가 불리기 전의 map, filter, reduce등은 실제로 진행되지 않고 collect혹은 foreach등의 terminal operation전까지는 수행이 지연된다
		 * List, ArrayList의 형태로 만들 수 있는듯
		 */
		List<Double> list = Stream.of(ids).map(x -> x*10.0).collect(Collectors.toList());
		ArrayList<Double> arrayList = Stream.of(ids).map(x -> x*10.0).collect(Collectors.toCollection(ArrayList::new));
		
		System.out.println(list.get(0) + arrayList.get(2));
		
		/* 
		 * parallel()
		 * 스트림 객체의 커다란 장점은 병렬 프로세싱이 용이하다는 점이다.
		 * 스트림 객체는 parallel()메소드를 통해 병렬처리를 지원하는 parallel객체로 변형될수 있고, 한번 변형이 된 병렬 스트림 객체는 멀티코어 환경에서 동작하게 된다.
		 * 여기서 생성된 병렬 스트림객체는그 이후에  filter, map, reduce, collect 그 어느행위를 하건 동시성 이슈(race condition, shared memory problem)이 없이 안전하게 수행된다.
		 * 이건 함수형 프로그래밍의 inner iteration으로 인한 큰 장점이다.
		 */
		List<Double> l = Stream.of(ids).parallel().map(x -> x*10.0).collect(Collectors.toList());
		System.out.println(l.get(0));
		
		// 람다식 Stream, forEach는 직관적인 프로그래밍, 코딩라인의 절감, 병렬 프로그래밍에 용이 하다는것이 장점이지만
		// 일반적인 for loop보다 10~20%느리고, 특히 primitive형태의 array들(int, double등)이 엄청 느리며 Exception발생시 callstack이 많이 찍혀서 디버깅이 어렵다는 단점이 있다.
		// 따라서 항상 Stream을 사용한 함수형 프로그래밍이 좋다고 할 수는 없으므로. 이런 장,단점을 확인하고 쓸지 말지를 생각해야 할것이다.(코딩 규약에서 쓸지 말지도)
		
		// 여담: 람다식 CompletableFuture기능으로 Java에서 비동기방식 처리 프로그램을 만들수 있긴한데 (one job asnyc)(Flow는 multi job async), 그래도 자바쪽에서 하는말 보면 객체지향중심이라 비동기처리 방식을 그렇게 업데이트는 하지 않을 모양이다.(Java9~10기준)
		// 스레드 구분 방식으로 병렬처리 지원하기도 하니 비동기방식 처리를 통한 병렬처리는 생각하지 않는걸까나?
	}
}
