package lambda;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.*;

/*
 * CompletableFuture - 완전한 비동기식 병렬 프로그래밍
 * 람다기반 비동기식 병렬 프로그래밍 기법 : CompletableFuture<T>
 * 자바 비동기식 프로그래밍의 끝판왕
 * 명시적 쓰레드 선언 없이 쓰레드의 사용이 가능함(병렬 프로그래밍)
 * 
 * 이미 병렬 프로그래밍 기능인 쓰레드가 있긴 하지만, 비동기식 멀티스레드 프로그래밍을 자유롭게 하기 위해선 부족한 면이 많아서
 * 비동기 Task간의 연결, 중간에 발생하는 예외처리가 어려웠기에 CompletableFuture이 등장하게 되었다.
 * 
 * CompletableFuture은 새로운 개념은 아니고, 이미 스칼라에서 Futures와 Promise, 하스켈같은 순수 함수형 언어에서 말하는 모나드에서 그 컨셉을 빌려왔다고 보면 된다.
 */
public class ForthPractice {
	public static void main(String args[]) {
		// ex) 비동기식으로 두개의 람다식을 연속적으로 실행하는 예제
		ExecutorService executor = Executors.newSingleThreadExecutor();
		CompletableFuture.runAsync(() -> {
			try {
				System.out.println("메세지 준비중...");
				Thread.sleep(1000);
			} catch(Exception e) {};
			System.out.println("Hello!");
			try {
				System.out.println("메세지 준비중...");
				Thread.sleep(1000);
			} catch(Exception e) {};
		}, executor).thenRun(() -> System.out.println("World"));
		
		/*
		 * 예제에서 확인되듯이, 두개의 비동기 Task가 자연스럽게 연결되었다.(A완료시 B실행)
		 * 1. Executor를 통해 두개의 비동기 Task가 수행될 스레드를 생성하고
		 * 2. CompleteableFurute.runAsync를 통해 다른 스레드에서 비동기식으로 동작할 로직을 선언하고
		 * 3. CompletableFuture.thenRun을 통해 첫번째 task가 완료된 이후에 연속적으로 동작할 로직을 선언했다.
		 */
		
		/*
		 * CompletableFurute의 패턴들
		 * Pattern 1 - 연속적 연결
		 * 하나의 Task완료 혹은 예외 상황을 다른 Task와 연결시키는 것
		 */
		
		// thenApplay(위의 예제외는 다른 쓰레드이므로 각각 실행됨 -> 비동기 실행)
		// then이라는 접두어는 앞의 Task들이 모두 반드시 완료되어야 한다는 것(앞의 Task는 하나 혹은 두개 일수 있음)
		// Apply라는 동사는 뒤에 Function형 람다식이 올 것이란걸 의미)
		CompletableFuture cf = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(1000);
			} catch(Exception ex) {};
			return "result A on thread " + Thread.currentThread().getId();
		}).thenApply(str -> str + "+ tailed").thenAccept(finalResult -> System.out.println(finalResult));
		System.out.println("Task execution requested on on thread " + Thread.currentThread().getId());
		
		// thenRunAsync
		// Run이라는 동사의 의미는 인자를 받지 않고 결과를 리턴하지 않는 Task입력값을 받는다는 의미
		CompletableFuture cff = CompletableFuture.runAsync(() -> {
			try {
				Thread.sleep(1000);
			} catch(Exception ex) {
				System.out.println("Exception"+ex.getMessage());
			};
			System.out.println("runAsync on thread"+Thread.currentThread().getId());
		}, executor).thenRunAsync(() -> System.out.println("thenRunAsync on thread "+Thread.currentThread().getId()));
		
		System.out.println("Task execution requested on thread "+Thread.currentThread().getId());
		
		/*
		 * CompletableFurute의 패턴들
		 * Pattern 2 - Task간의 조합
		 * CompletableFuture의 수행중 완전히 다른 CompletableFuture를 조합하여 실행할 수 있다.
		 * 여기서 조합이란 말은 완료시점을 하나로 맞출 수 있다는 뜻
		 */
		
		// thenComposeAsync
		// 중복적으로 사용되어 여러 Task를 조합할 수 있다.
		// 예시) 웹 서버에서 하나의 request를 받아 4개의 내부 reqeust를 보낸 후 결과를 모두 취합하여 최초의 reqeust에 보내줄 필요가 있을때.
		ExecutorService e = Executors.newCachedThreadPool();
		long startTime = System.currentTimeMillis();
		CompletableFuture cf1 = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(5000);
			} catch(Exception ex) {};
			System.out.println("cf1 supplyAsyc on thread"+Thread.currentThread().getId()+" now="+(System.currentTimeMillis()-startTime));
			return 100;
		});
		CompletableFuture cf2 = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(1000);
			} catch(Exception ex) {};
			System.out.println("cf2 supplyAsyc on thread"+Thread.currentThread().getId()+" now="+(System.currentTimeMillis()-startTime));
			return 200;
		});
		CompletableFuture cf3 = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(3000);
			} catch(Exception ex) {};
			System.out.println("cf3 supplyAsyc on thread"+Thread.currentThread().getId()+" now="+(System.currentTimeMillis()-startTime));
			return 300;
		}, e);
		System.out.println("Task execution reqeusted on thread " + Thread.currentThread().getId());
		cf3.thenComposeAsync((data1) -> cf2).thenComposeAsync((data2) -> cf1).join();
		try {
			System.out.println("final cf1.get = " + cf1.get() + " cf2.get()="+cf2.get() + " cf3.get()="+cf3.get()+ "  now = "+ (System.currentTimeMillis()-startTime));
		} catch (InterruptedException | ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/*
		 * CompletableFurute의 패턴들
		 * Pattern 3 - 두 Task결과를 모두 사용한 Task간 결합
		 * 두 Task결과를 모두 기다렸다가 결과들을 조합하여 그 다음 일을 하는것
		 */
		
		// thenCombineAsync
		// 앞의 Task와 Parameter로 받은 Task의 결과를 입력값으로 받아서 새로운 결과를 리턴한다. get으로서 결과를 얻을 수 있다.
		
		ExecutorService e2 = Executors.newCachedThreadPool(); //새로운 비동기처리가 수행될 스레드 생성
		CompletableFuture CF1 = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(3000);
			} catch(Exception ex) {};
			System.out.println("CF1 supplyAsync on thread " + Thread.currentThread().getId());
			return 500;
		});
		
		CompletableFuture CF2 = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(1000);
			} catch(Exception ex) {};
			System.out.println("CF2 supllyAsync on thread " + Thread.currentThread().getId());
			return 10;
		}, e2).thenCombineAsync(CF1, (Integer x, Integer y) -> x*y); //cf1과 조합(x = cf1, y = cf2, x*y실행해서 결과값을 return함)
		
		try {
			System.out.println("final result = " + CF2.get());
		} catch (InterruptedException | ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// thenAcceptBoth
		// 앞의 Task의 결과, Parameter로 받은 Task의 결과를 받아 return은 하지 않고 결과를 소비하는 패턴이다.
		// 위의 식에서 CF2뒤에 .thenAcceptBothAsync(CF1, (Integer x, Integer y) -> System.out.println("x="+x" y="+y));
		// 이렇게 하면 x, y 값이 나오고 return되는 값이 없으므로 System.out.println("final resutl = " + cf2.get()); 해도 null만 나옴
		
		// runAfterBothAsync 
		// 앞의 Task완료, Parameter로 받은 Task의 완료 이후에 아무런 Parameter를 받지 않고 로직을 수행함. 리턴 안함
		// .runAfterBothAsync(CF1, () -> System.out.println("runAfterBothAsync on thread " + Thread.currentThread().getId())); //이 로직을 수행할뿐
		
		/*
		 * CompletableFurute의 패턴들
		 * Pattern 4 - 먼저 도착한 Task결과를 사용하는 Task결합 방법
		 * 두 Task중 먼저 결과를 내응 쪽의 결과값을 가지고 Task를 실행한다.
		 */
		
		// applyToEither
		// 앞의 Task(CF2)와 Parameter로 받은 Task(CF1)중에 더 빨리 결과를 리턴하는 Task의 결과(가장 빠른거 하나)를 get을 통해 얻을 수 있다.
		// appylToEither를 연속적으로 써서 여러 Task의 연결도 가능함
		// .applyToEither(cf1, Function.<Integer>identity()).applyToEither(cf2, Function.<Integer>identity()); 이런식
		// 예를들어 cf2의 sleep값이 1000이고 cf3의 sleep값이 3000이라면 당연히 cf2의 sleep 값이 더 짧으므로 get으로 cf2의 반환된 값이 나온다
		
		// acceptEitherAsync
		// 앞의 Task와 Parameter로 받은 Task중에 더 빨리 결과를 리턴하는 Task의 값(가장 빠른거 하나)을 가지고 람다식을 통해 처리할 수 있다. 람다식에서 값을 사용하기만 할 뿐 리턴은 안함
		// .acceptEitherAsync(cf1, (x) -> System.out.println("cf2 acceptEitherAsync x="+x).acceptEitherAsync(cf2, (x) -> System.out.println("cf2 acceptEitherAsync x="+x); 이런식
		
		// runAfterEitherAsync
		// 앞의 Task와 Parameter로 받은 Task중에 Task가 끝날 때 마다 람다식을 수행하는 메소드이다.
		// .runAfterEitherAsync(cf1, (x) -> System.out.println("cf1 runAfterEitherAsync ="+Thread.currentThread().getId())
		// .runAfterEitherAsync(cf2, (x) -> System.out.println("cf2 runAfterEitherAsync ="+Thread.currentThread().getId()); 이런식으로 있다면
		// 꼭 지정된 Parameter가 아니라도 특정 처리(Task)가 끝났을때 runAfterEitherAsync가 실행 됨(정확히는 그 안의 람다식이 실행됨). 위의 경우에는 Thread의 id값이 나오게 됨
		
		// 이렇게 Executor와 CompletableFuture를 써서 Java에서도 비동기처리(병렬 프로그램 처리)가 가능하다.
		// 물론 일반적으로 스레드 생성해서 처리하는 병렬 처리방식도 있지만, 람다식을 사용한다면 위와 같은 방식으로 처리하면 된다고 알아두면 될듯
	}
}
