# 10분만에 사용하는 pandas
# 이거랑 reports사용해서 인터넷에서 데이터 불러온 뒤에 어떤 단어가 가장 많이 사용됐는지 분석하는것 등 만들수 있을것같다
# 주로 쓰는 내용을 코드만 써놓은것도 많으므로, 직접 확인해보고 사용할것!

# pandas:데이터 분석, 데이터 처리 등을 쉽게 하기 위해 만들어진 Python Package
import pandas as pd  # pandas를 import해서 pd로 사용

# numpy:Numerical Python의 줄임 말로써 고성능의 수치 계사늘 하기 위해 만들어진 Python Package다
import numpy as np

# matplotlib:플롯(그래프)을 그릴 때 주로 쓰이는 2D, 3D플로팅 패키지다.
import matplotlib.pyplot as plt


# 1. 데이터 오브젝트 생성하기(Series, DataFrame)
# Pandas의 데이터 구조 중 하나인  Series는 다음과 같이 값들의 리스트를 넘겨서 만들 수 있습니다.
# Series는 value와 index의 형태를 지니는 Pandas의 자료구조다(Map, dictionary와 비슷하다 보면 된다)
s = pd.Series([1, 3, 5, np.nan, 6, 8])
print(s)
print("\n")

# DataFreame은 numpy array를 받아 생성 가능합니다.
# 여러개의 Dictionary(Java의 Map)를 처리하는데 있어 Series보다 보기 편한 상태로 제공 합니다.(엑셀의 표 모양과 동일)
dates = pd.date_range('20130101', periods=6)
print(dates)

df = pd.DataFrame(np.random.randn(6, 4), index=dates, columns=list("ABCD"))
print(df)

# 다음과 같이 Series처럼 변환 가능한 오브젝트들을 갖고 있는 dict의 형태를 인자로 넣어주어DataFrame을 만들 수 있습니다.
# 이 때, index는 자동으로 0부터 시작하여 1씩 증가하는 정수 인덱스가 부여되고, dict의 키 값을 컬럼의 이름으로 사용하게 됩니다.(JSON형식)
df2 = pd.DataFrame({
    "A": 1.,
    "B": pd.Timestamp('20130102'),
    "C": pd.Series(1, index=list(range(4)), dtype='float32'),
    'D': np.array([3] * 4, dtype='int32'),
    'E': pd.Categorical(['test', 'train', 'test', 'train']),
    'F': 'foo'
})
print(df2)

# DataFrame의 컬럼들은 각기 특별한 자료형을 갖고 있습니다.
# 이는 DataFrame내에 있는 dtypes라는 속성을 통해 확인 가능합니다.
# 파이썬의 기본적인 소수점은 float64로 잡히고, 기본적인 문자열은 str이 아니라 object라는 자료형으로 나타납니다.
print(df2.dtypes)
print(dir(df2))  # dir関数を使ってどんな属性が入れているのか確認出来ます(위에서 지정한A,B,C,D,E,F도 다 들어가있음)

# 2. 데이터 확인하기
# 첫 5개 행의 데이터를 보여줌
print(df.head())
# 마지막 3개 행의 데이터를 보여줌
print(df.tail(3))

print(df.index)  # ==print(datas)
print(df.columns)
print(df.values)
print(df.describe())  # DataFrame의 간단한 통계 정보를 보여줌
print(df.T)  # DataFrame에서 index와 column을 바꾼 형태의 DataFrame(함수가 아닌 속성)
print(df.sort_index(axis=1, ascending=False))  # 정렬(axis=1 컬럼기준, ascending=False 내림차순)
print(df.sort_values(by='B'))  # 값 기준 정렬

# 3. 데이터 선택하기
print(df['A'])  # column선택 == df.A
print(type(df['A']))  # return되는 값은 Series의 형태임
print(df[0:3])  # 맨 처음 3개의 행을 가져옵니다(범위)
print(df['20130102':'20130104'])  # 행범위의 데이터를 가져옵니다
print(df.loc[dates[0]])  # index 로 선택해서 값 가져오기
print(df.loc[:, ['A', 'B']])  # column A와 column B에 대한 모든 값("":"")(슬라이딩 이라고함)가져오기
print(df.loc['20130102':'20130104', ['A', 'B']])
print(df.loc[dates[0], ['A', 'B']])  # 특정 index값의 컬럼A,B값 가져오기
print(df.loc[dates[0], 'A'])
print(df.at[dates[0], 'A'])  # 上と同じ

print(df.iloc[3])  # 위치를 이용하여 선택 == df.loc[dates[3]
print(df.iloc[3:5, 0:2])  # index, column위치 선택
print(df.iloc[[1, 2, 4], [0, 2]])  # 배열로 위치 선택해서 보기도 가능
print(df.iloc[1:3, :])  # 참고로 이 범위는 0,1의 자리에서 1,2,3까지이므로 2개(2,3)가 나온다
print(df.iloc[:, 1:3])  # 반대도 됨
print(df.iloc[1, 1])
print(df.iat[1, 1])  # 上と同じ

# Boolean indexing(조건문)
print(df[df.A > 0])  # 조건에 맞는 열이 있는걸 선택하는것도 가능하고
print(df[df > 0])  # 조건에 맞는 값만 나오게 하는것도 가능하다
df2 = df.copy()
df2['E'] = ['one', 'one', 'two', 'three', 'four', 'three']
print(df2)  # 행 하나 추가
print(df2[df2['E'].isin(['two', 'four'])])  # 필터링 조건문 가능(isin)

# Setting(수정 포함)
s1 = pd.Series([1, 2, 3, 4, 5, 6], index=pd.date_range('20130102', periods=6))  # periods를 지정한다는걸 확실히 써주자(오타나서 오류떳었음)
print(s1)
df['F'] = s1  # Setting해서 쓰는도 가능하다
print(df.at[dates[0], 'A'])
df.at[dates[0], 'A'] = 0
print(df.at[dates[0], 'A'])  # 위치 이므로 값 넣어버리는것 가능
df.iat[0, 1] = 0
df.loc[:, 'D'] = np.array([5] * len(df))  # 범위 지정해서 범위값 넣는것도 가능
print(df)
df2 = df.copy()
df2[df2 > 0] = -df2  # 0보다 큰걸 음수로 만들어버림
print(df2)

# 4. Missing Data(결측치, 결측값, 누락된 자료)
print("================ Missing Data ===================")
df1 = df.reindex(index=dates[0:4], columns=list(df.columns) + ['E'])  # index새로 만듬
df1.loc[dates[0]:dates[1], 'E'] = 1  # 새로만든 E인덱스의 0,1위치에 1을 넣음 -> 이때, 결측값이 생길수도 있다.(NaN값)
print(df1)

# 다양한 이유로 인해 생기는 결측값은 분석 오류가 발생되거나 혹은 왜곡될 위험이 있으므로,
# 이에 대해 isnull(), notnull()등의 메소드로 체크를 해줘야 한다.(한마디로 null체크 해줘야 한다는것)

print(df1.dropna(how='any'))  # dropna(any)로 missing data(NaN)전부 제거
print(df1.fillna(value=5))  # 값 채워넣기로 missing data(NaN)에 데이터 입력
print(pd.isna(df1))  # NaN값 체크
print(pd.isnull(df1))  # null값 체크(위와 동일)
print(pd.notnull(df1))  # null이 아닌 값 체크

# 5.Operations -> 일반적으로, missing data(null)을 포함해서 계산함
print(df.mean())  # 평균값 출력
print(df.mean(1))  # 위랑 같은 처리(평균값 출력)
s = pd.Series([1, 3, 5, np.nan, 6, 8], index=dates).shift(2)  # 오른쪽으로 2이동, np.nan은 NaN(null)값 추가
print(s)
print(df)
print(df.sub(s, axis='index'))  # NaN으로 바꾸거나 빼버리는듯?
print(df.apply(np.cumsum))  # 현재 열의 값에 이전 열의 데이터를 더하면서 커짐(apply 지원, 적용)
print(df.apply(lambda x: x.max() - x.min()))  # 해당 행의 최대값 - 최소값
s = pd.Series(np.random.randint(0, 7, size=10))  # 0~7까지 랜덤한 숫자 들어가는 배열 생성(Series)
print(s)
print(s.value_counts())  # Histogramming, 숫자 얼마나 있는지 카운트
s = pd.Series(['A', 'B', 'C', 'Aaba', 'Baca', np.nan, 'CABA', 'dog', 'cat'])
print(s.str.lower())  # String Methods 문자 메소드. 문자 크기 늘리거나 줄이거나 합치거나 뺴거나 등등 하는 함수들이 있음

# 6.Merge(병합)
df = pd.DataFrame(np.random.randn(10, 4))  # 0~9열, 0~3행짜리 랜덤값 배열 만듬
print(df)
pieces = [df[:3], df[3:7], df[7:]]  # 0~2열, 3~6열, 7~9열로 나눔
print(pd.concat(pieces))  # concat으로 하나로 합침(Merge)
left = pd.DataFrame({'key': ['foo', 'foo'], 'lval': [1, 2]})
right = pd.DataFrame({'key': ['foo', 'foo'], 'rval': [4, 5]})
print(left)
print(right)
print(pd.merge(left, right, on='key'))  # merge함수 이용해서 병합(key기준)
left = pd.DataFrame({'key': ['foo', 'bar'], 'lval': [1, 2]})  # key변경
right = pd.DataFrame({'key': ['foo', 'bar'], 'rval': [4, 5]})  # key변경
print(left)
print(right)
print(pd.merge(left, right, on='key'))  # merge함수 이용해서 병합(key기준)
df = pd.DataFrame(np.random.randn(8, 4), columns=['A', 'B', 'C', 'D'])  # 8,4 행렬 이차원 배열
s = df.iloc[3]  # 3번 행렬 따로 출력
print(df.append(s, ignore_index=True))  # 배열에 추가(append)

# 7.Grouping(그룹 형식으로 묶는것. group by)
df = pd.DataFrame({'A': ['foo', 'bar', 'foo', 'bar', 'foo', 'bar', 'foo', 'foo'],
                   'B': ['one', 'one', 'two', 'three', 'two', 'two', 'one', 'three'],
                   'C': np.random.randn(8),
                   'D': np.random.randn(8)})
print(df)  # 임의의 데이터 배열 생성
print(df.groupby('A').sum())  # A행 기준. 다 합침
print(df.groupby(['A', 'B']).sum())  # A,B행 기준. 다 합침(DataBase 명령어랑 비슷한것도 몇개 보임)

# 8.Reshaping
tuples = list(zip(*[['bar', 'bar', 'baz', 'baz', 'foo', 'foo', 'qux', 'qux'],
                    ['one', 'two', 'one', 'two', 'one', 'two', 'one', 'two']]))
index = pd.MultiIndex.from_tuples(tuples, names=['first', 'second'])  # tuples에 MultiIndex추가
df = pd.DataFrame(np.random.randn(8, 2), index=index, columns=['A', 'B'])  # index에 임의의 값 배열 추가
df2 = df[:4]  # 처음부터 4번째 까지(0~3)
print(df2)  # 출력해서 봐도 이해하기 좀 어렵다
stacked = df2.stack()  # stack 형식으로 표시해줌(위에서 아래 계층 형식)
print(stacked)  # 이해하기 좋은 출력 형식
print(stacked.unstack())  # stack해제
print(stacked.unstack(1))  # stack해제(다른 형식)
print(stacked.unstack(0))  # stack해제(다른 형식)

df = pd.DataFrame({'A': ['one', 'one', 'two', 'three'] * 3,
                   'B': ['A', 'B', 'C'] * 4,
                   'C': ['foo', 'foo', 'foo', 'bar', 'bar', 'bar'] * 2,
                   'D': np.random.randn(12),
                   'E': np.random.randn(12)  # 랜덤값 12개
                   })
print(df)  # 일반적으로 만든 테이블
print(pd.pivot_table(df, values='D', index=['A', 'B'], columns='C'))  # Pivot Table 메소트 써서 데이터 뽑아서 새 테이블 만든 예

# 9.Time Series
rng = pd.date_range('1/1/2012', periods=100, freq='S')  # 시간 범위 배열(2012년 1월 1일 0초부터 100초까지)
ts = pd.Series(np.random.randint(0, 500, len(rng)), index=rng)  # rng시간 범위동안 0~500사이의 특정 random값을 출력
print(ts.resample('5Min').sum())  # 랜덤으로 뽑은 값들 전부 합침
rng = pd.date_range('3/6/2012 00:00', periods=5, freq='D')  # 날짜단위
ts = pd.Series(np.random.randn(len(rng)), rng)  # rng범위만큼 임의의 값 넣음
print(ts)

ts_utc = ts.tz_localize('UTC')
print(ts_utc)  # Time zone representation(시간 기준 변경)
ts_utc = ts.tz_localize('US/Eastern')
print(ts_utc)  # Time zone representation(시간 기준 변경)

rng = pd.date_range('1/1/2012', periods=5, freq='M')  # 월단위
ts = pd.Series(np.random.randn(len(rng)), index=rng)
print(ts)
ps = ts.to_period()
print(ps)  # 데이터 표시 형식 변경
print(ps.to_timestamp())  # timestamp함수를 써서 매월의 1일로 변경

prng = pd.period_range('1990Q1', '2000Q4', freq='Q-NOV')  # 시간 기간(10년, 4분기로 나눔(Q))
ts = pd.Series(np.random.randn(len(prng)), prng)
ts.index = (prng.asfreq('M', 'e') + 1).asfreq('H', 's') + 9  # 월+1(분기 월 표시를 위함), 시간+9로 표시
print(ts.head())

# 10.Categoricals(데이터의 이름과 카테고리(type) 지정)
df = pd.DataFrame({"id": [1, 2, 3, 4, 5, 6], "raw_grade": ['a', 'b', 'b', 'a', 'a', 'e']})  # 카테고리 데이터 예시
df["grade"] = df["raw_grade"].astype("category")
print(df["grade"])  # Name, category가 바뀜
df["grade"].cat.categories = ["very good", "good", "very bad"]  # 카테고리 선언(a,b,e)
df["grade"] = df["grade"].cat.set_categories(["very bad", "bad", "medium", "good", "very good"])
print(df["grade"])  # 카테고리 입력 후 출력(5단계)
print(df.sort_values(by="grade"))  # 위에서 지정한 grade순서대로 출력(점수가 낮은것부터, 오름차순)
print(df.groupby("grade").size())  # 갯수 출력

# 11.Plotting(그래프!)
ts = pd.Series(np.random.randn(1000), index=pd.date_range('1/1/2000', periods=1000))  # 랜덤값 1000개, 범위 1000개 만들어서 넣음
ts = ts.cumsum()  # 이전 열의 수와 현재 열의 수를 더해서 계속 값 입력(cumsum())
# ts.plot()  # 그래프 형태로 plt에 입력
# plt.show()  # 그래프를 보여줌

df = pd.DataFrame(np.random.randn(1000, 4), index=ts.index,
                  columns=['A', 'B', 'C', 'D'])
df = df.cumsum()
plt.figure();
df.plot();
plt.legend(loc='best')
# plt.show()  # 그래프를 보여줌

# 12.Getting Data In/Out
df.to_csv('foo.csv')  # Writing to a csv file
print(pd.read_csv('foo.csv'))  # Reading from a csv file
df.to_hdf('foo.h5', 'df')  # Writing to a HDF5 Store(need tables package)
print(pd.read_hdf('foo.h5', 'df'))  # Reading from a HDF5 Store
df.to_excel('foo.xlsx', sheet_name='Sheet1')  # Writing to an excel file(need openpyxl package)
print(pd.read_excel('foo.xlsx', 'Sheet1', index_col=None, na_values=['NA']))  # Reading from xlex(need xlrd Package)

# 13.Gotchas
# 에러 일어났을때 캐치(Gotchas, catch)해서 어떤 에러인지 아는 부분.
# 이 부분은 Doc을 보고 에러와 해결법을 확인하는게 좋다.(검색하거나)
