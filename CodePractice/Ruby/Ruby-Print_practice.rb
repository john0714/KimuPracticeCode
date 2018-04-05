print("Hello world!")
puts("Hello world!")

puts(2.2.ceil())
puts(2.7.floor())
puts(2**10)
puts(Math::PI)

puts(10+5)
puts(10-5)
puts(10*5)
puts(10/5)

puts('Hello')
puts("Hello")
puts("Hello 'world'")
puts('Hello "world"')

puts('Hello '+'world')
puts('Hello '*3)
puts('Hello'[0])
puts('Hello'[1])
puts('Hello'[2])

#小文字に作ります。
puts('hello world'.capitalize())
#大文字に作ります。
puts('hello world'.upcase())
#文字のlength
puts('Hello world'.length())
#world文字列をprogrammingに変わります。
puts('Hello world'.sub('world', 'programming'))

puts("egoing's \"tutorial\"")
puts("\\")
#줄바꿈
puts("Hello\nworld")
#tab거리만큼 띄우기
puts("Hello\t\tworld")
#줄공백 넣기
puts("\a")
#Ruby에서 줄바꿈은''면 불가능
puts('Hello\nworld')

#integer
puts(10+5)
#String
puts("10"+"5")