import math

print(math.ceil(2.9)) #올림
print(math.floor(2.9)) #내림
print(math.sqrt(16)) #나눗셈

# import로 가져오는건 모듈(파이선 파일명)이며 그 안에 정의 되있는 Class들이 객체이다.
# Class는 자바의 Class와 같은 개념이며, Module을 import해주고 사용할때 (루비나 자바에선(방식은 조금 다르지만) new로 인스턴스 변수 생성해주고)
# Python에선 new안쓰고 그냥 인스턴스 변수로 생성해서 Class를 선언한 후에 사용 가능하다
# 일반적인 Module은 항상 준비되어 있는 메소드들의 집합이며(Public) 쓰던 안쓰던 늘 메모리상에 올라가 있다. Module의 메소드들은 import만 하고 그냥 사용 가능하다.
# 하지만 Module은 Class들도 여러개 포함 할 수 있고 import로 가져오는게 module이므로 최상위 객체가 module이라고도 볼 수 있다.