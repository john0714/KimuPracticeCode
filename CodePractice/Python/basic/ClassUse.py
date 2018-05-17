import ClassCal

c1 = ClassCal.Cal(10, 10) #ファイル中のクラス宣言
print(c1.add()) #クラス中の関数
print(c1.subtract())
c2 = ClassCal.Cal(10, 20) #ファイル中のクラス宣言
print(c2.add())
print(c2.subtract())

c1.setV1('one') #String을 넣었으므로 set에 값이 들어가지 않음
#c1.setV2(30) #v2에는 int형을 넣었으므로 값이 들어감
c1.v2 = 30 #Python에선 인스턴스 변수에 직접 접근도 가능함(루비에선 불가능)
#물론 기본 설정일뿐 가능하게도 불가능하게도 바꿀수도 있음
print(c1.add())
print(c1.subtract())

# import로 가져오는건 모듈(파이선 파일명)이며 그 안에 정의 되있는 Class들이 객체이다.
# Class는 자바의 Class와 같은 개념이며, Module을 import해주고 사용할때 (루비나 자바에선(방식은 조금 다르지만) new로 인스턴스 변수 생성해주고)
# Python에선 new안쓰고 그냥 인스턴스 변수로 생성해서 Class를 선언한 후에 사용 가능하다
# 일반적인 Module은 항상 준비되어 있는 메소드들의 집합이며(Public) 쓰던 안쓰던 늘 메모리상에 올라가 있다. Module의 메소드들은 import만 하고 그냥 사용 가능하다.
# 하지만 Module은 Class들도 여러개 포함 할 수 있고 import로 가져오는게 module이므로 최상위 객체가 module이라고도 볼 수 있다.