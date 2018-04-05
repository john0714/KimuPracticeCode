#puts("입력해주세요")

#gets.chomp()で入力を貰います。
#getsで文字を読み込み、chomp()は一番後ろのEnterを無視します。
#in_str = gets.chomp()
#大文字
#puts(in_str.upcase() + " World!")

puts("아이디를 입력 해주세요.\n")
instr = gets.chomp()
real_1 = "11"
real_2 = "ab"
if real_1 == instr
    puts("Hello!, kimu!")
elsif real_2 == instr
    puts("Hello!, k8805")
else
    puts("Who are you?")
end
