class Cal(object):
    def __init__(self, v1, v2): #生成者
        if isinstance(v1, int): #if v1인스턴스가 int형 이라면(형태 체크)
            self.v1 = v1
        if isinstance(v2, int): #if v2인스턴스가 int형 이라면(형태 체크)
            self.v2 = v2
        #初期化変数
        #파이선의 메소드들은 첫번째 매개변수(인수)를 지정해줘야함(보통은 self라는 이름으로 지정함)
        #이 첫번째 매개변수는 클래스가 지정한 인스턴스가 된다
        #인스턴스가 만들어질때 생성자가 실행되며 생성자의 첫번째 매개 변수로 생성한 인스턴스가 들어오게 지정되 있음
        #즉, python에서 메소드의 첫번째 인수는 인스턴스란거다.

    def add(self):
        return self.v1+self.v2
    def subtract(self):
        return self.v1-self.v2

    def setV1(self, v):
        if isinstance(v, int):
            self.v1 = v
    def getV1(self):
        return self.v1

    def setV2(self, v):
        if isinstance(v, int):
            self.v2 = v
    def getV2(self):
        return self.v2