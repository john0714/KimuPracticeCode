from pandas import Series, DataFrame
import pandas as pd
# 이렇게 선언해두면 그냥 Series로도 사용 가능하고 pd.Series로도 사용가능

import numpy as np

obj = Series([4,7,-5,3])
print(obj)
print(obj.values)
print(obj.index)

obj2 = Series([4,7,-5,3], index = ['d','b','a','c'])
print(obj2)
print(obj2.index)
print(obj2['a'])
print(obj2[obj2>0])
print(obj2 * 2)

print(np.exp(obj2))  # 지수상수의 승수 값 구함 -> 지수 구함 (e^x, e=2.718281)
# d      54.598150
# b    1096.633158
# a       0.006738
# c      20.085537
# dtype: float64

sdata = {'Ohio':35000, 'Texas':71000, 'Oregon':16000, 'Utah':5000}
obj3 = Series(sdata)
print(obj3)
# Ohio      35000
# Oregon    16000
# Texas     71000
# Utah       5000
# dtype: int64

states = ['California', 'Ohio', 'Oregon', 'Texas']
obj4 = Series(sdata, index = states)
print(obj4)
# California        NaN
# Ohio          35000.0
# Oregon        16000.0
# Texas         71000.0
# dtype: float64

print(pd.isnull(obj4))
# California     True
# Ohio          False
# Oregon        False
# Texas         False
# dtype: bool

print(obj3 + obj4)
# California         NaN
# Ohio           70000.0
# Oregon         32000.0
# Texas         142000.0
# Utah               NaN

obj4.name = 'population'
obj4.index.name = 'state'
print(obj4)
# state
# California        NaN
# Ohio          35000.0
# Oregon        16000.0
# Texas         71000.0
# Name: population, dtype: float64

data = {'state': ['Ohio', 'Ohio', 'Ohio', 'Nevada', 'Nevada'],
        'year': [2000, 2001, 2002, 2001, 2002],
        'pop': [1.5, 1.7, 3.6, 2.4, 2.9]}
frame = DataFrame(data)
print(frame)
#    pop   state  year
# 0  1.5    Ohio  2000
# 1  1.7    Ohio  2001
# 2  3.6    Ohio  2002
# 3  2.4  Nevada  2001
# 4  2.9  Nevada  2002

print(DataFrame(data, columns=['year', 'state', 'pop']))
#    year   state  pop
# 0  2000    Ohio  1.5
# 1  2001    Ohio  1.7
# 2  2002    Ohio  3.6
# 3  2001  Nevada  2.4
# 4  2002  Nevada  2.9

frame2 = DataFrame(data, columns = ['year', 'state', 'pop', 'debt'],
                   index = ['one', 'two', 'three', 'four', 'five'])
print(frame2)
#        year   state  pop debt
# one    2000    Ohio  1.5  NaN
# two    2001    Ohio  1.7  NaN
# three  2002    Ohio  3.6  NaN
# four   2001  Nevada  2.4  NaN
# five   2002  Nevada  2.9  NaN

print(frame2.year)
# one      2000
# two      2001
# three    2002
# four     2001
# five     2002

print(frame2.ix['three']) # 特定インデックス(ixで選択)
# year     2002
# state    Ohio
# pop       3.6
# debt      NaN

frame2.debt = 16.5
print(frame2)
#        year   state  pop  debt
# one    2000    Ohio  1.5  16.5
# two    2001    Ohio  1.7  16.5
# three  2002    Ohio  3.6  16.5
# four   2001  Nevada  2.4  16.5
# five   2002  Nevada  2.9  16.5

frame2.debt = np.arange(5)
print(frame2)
#        year   state  pop  debt
# one    2000    Ohio  1.5     0
# two    2001    Ohio  1.7     1
# three  2002    Ohio  3.6     2
# four   2001  Nevada  2.4     3
# five   2002  Nevada  2.9     4

val = Series([-1.2, -1.5, -1.7], index=['two', 'four', 'five'])
frame2.debt = val
print(frame2)
#        year   state  pop  debt
# one    2000    Ohio  1.5   NaN
# two    2001    Ohio  1.7  -1.2
# three  2002    Ohio  3.6   NaN
# four   2001  Nevada  2.4  -1.5
# five   2002  Nevada  2.9  -1.7

frame2['eastern'] = frame2.state == 'Ohio'
print(frame2)
#        year   state  pop  debt  eastern
# one    2000    Ohio  1.5   NaN     True
# two    2001    Ohio  1.7  -1.2     True
# three  2002    Ohio  3.6   NaN     True
# four   2001  Nevada  2.4  -1.5    False
# five   2002  Nevada  2.9  -1.7    False

del frame2['eastern']  # delete
print(frame2)
#        year   state  pop  debt
# one    2000    Ohio  1.5   NaN
# two    2001    Ohio  1.7  -1.2
# three  2002    Ohio  3.6   NaN
# four   2001  Nevada  2.4  -1.5
# five   2002  Nevada  2.9  -1.7

pop = {'Nevada':{2001:2.4, 2002:2.9},
       'Ohio':{2000:1.5, 2001:1.7, 2002:3.6}}
frame3 = DataFrame(pop)
print(frame3)
#       Nevada  Ohio
# 2000     NaN   1.5
# 2001     2.4   1.7
# 2002     2.9   3.6

print(frame3.T)
#         2000  2001  2002
# Nevada   NaN   2.4   2.9
# Ohio     1.5   1.7   3.6

print(DataFrame(pop, index=[2001, 2002, 2003]))  # 特定な範囲だけ
#       Nevada  Ohio
# 2001     2.4   1.7
# 2002     2.9   3.6
# 2003     NaN   NaN

frame3.index.name = 'year'
frame3.columns.name = 'state'
print(frame3)  # DataFrameのindexとcolumnsに名前の付けます。 形を覚えてください。
# state  Nevada  Ohio
# year
# 2000      NaN   1.5
# 2001      2.4   1.7
# 2002      2.9   3.6

obj = Series(range(3), index=['a', 'b', 'c'])
index = obj.index
print(index)
# Index(['a', 'b', 'c'], dtype='object')

index = pd.Index(np.arange(3))
obj2 = Series([1.5,-2.5,0], index = index)
print(obj2.index is index)  # index가 같은지 확인함(당연히 위에서 만든 index를 obj2의 index부분에 넣었으니 obj2.index랑 index는 같다고 나옴)
# True

print(obj2)
# 0    1.5
# 1   -2.5
# 2    0.0
# dtype: float64