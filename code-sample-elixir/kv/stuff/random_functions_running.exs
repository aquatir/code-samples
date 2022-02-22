Code.require_file("lib/random_functions.ex")

IO.inspect RandomFunctions.f_with_default(10) # would multiple by default value == 10
IO.inspect RandomFunctions.f_with_default(10, 3) # would multi by 3

IO.puts ("")

IO.inspect RandomFunctions.f_with_two_default(25)
IO.inspect RandomFunctions.f_with_two_default(25, 30)
IO.inspect RandomFunctions.f_with_two_default(25, 30, 35)

# using guard in anonimous functions
number_compare = fn
  number, other_number when number >= other_number -> number
  _, other_number -> other_number
end

IO.inspect number_compare.(1, 2) # returns 2

IO.puts("")

IO.inspect RandomFunctions.up_to(5)
IO.inspect RandomFunctions.up_to_tail_rec(5)
IO.inspect RandomFunctions.sum_of_list([1,2,3]) #6
IO.inspect RandomFunctions.sum_of_list([6,5,4]) #15

IO.puts("")

# Two types of syntax to access values in maps
item = %{magic: true, price: 150, title: "Edwin's Longsword"}
IO.inspect item[:title] # returns "Edwin's Longsword"
IO.inspect item["owner"] # returns nil
IO.inspect item[:creator][:city] # returns nil
IO.inspect item.title # returns "Edwin's Longsword"
# IO.inspect item.owner # raises a KeyError

IO.puts("")

IO.inspect RandomFunctions.factorial(3) # 2 * 3 = 6
IO.inspect RandomFunctions.factorial(0) # 1
IO.inspect RandomFunctions.factorial(5) # 2 * 3 * 4 * 5 = 120

# IO.inspect RandomFunctions.factorial(-5)  ## will fail guard with
#   ** (FunctionClauseError) no function clause matching in RandomFunctions.factorial/1

IO.puts("")

fact_gen = fn me ->
     fn
       0 -> 1
       x when x > 0 -> x * me.(me).(x - 1)
      end
   end
fact = fact_gen.(fact_gen)

IO.inspect fact.(5)

IO.puts("")
