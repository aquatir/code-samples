module Stuff where


printHello = print "Hello, world"
double x = x + x
doubleBothSum x y = double x + double y
doubleNotAll x = if (x > 100) 
                 then x
                 else double x 

someComprehension xs ys = [(x,y) | x <- xs, y <- ys, y /= 5, x /= 3, x > y]


-- All right triangles with perimeter of 24 out of all triangles with length of size between 1 and 10
-- let rightTriangles = [ (a,b,c) | c <- [1..10], a <- [1..c], b <- [1..10], a^2 + b^2 == c^2, a+b+c == 24]

removeNonUppercase :: [Char] -> [Char]  
removeNonUppercase st = [ c | c <- st, c `elem` (' ' : ['A'..'Z'])] 

devilNumber :: (Integral a) => a -> String
devilNumber 666 = "HELL"
devilNumber _   = "Sorry, no!"


factorial :: (Integral a) => a -> a
factorial a = factorial' a 1 

factorial' :: (Integral a) => a -> a -> a
factorial' 0 acc = acc
factorial' a acc = factorial' (a-1) (acc * a)

