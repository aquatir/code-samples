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

-- guards!
guardStuff :: Bool -> String
guardStuff a
    | a == True = "True!"
    | otherwise = "False!"


myCompare :: (Ord a) => a -> a -> Ordering  
a `myCompare` b  
    | a > b     = GT  
    | a == b    = EQ  
    | otherwise = LT 

-- where 

bmiTell :: (RealFloat a) => a -> a -> String  
bmiTell weight height  
    | bmi <= skinny = "You're underweight, you emo, you!"  
    | bmi <= normal = "You're supposedly normal. Pffft, I bet you're ugly!"  
    | bmi <= fat    = "You're fat! Lose some weight, fatty!"  
    | otherwise     = "You're a whale, congratulations!"  
    where bmi = weight / height ^ 2  
          (skinny, normal, fat) = (18.5, 25.0, 30.0)

-- case expressions! 
head' :: [a] -> a  
head' xs = case xs of []    -> error "No head for empty lists"
                      (x:_) -> x  


mymax :: (Ord a) => [a] -> a
mymax []     = error "No elements is array!"
mymax (x:[]) = x
mymax (x:xy)   
    | x > tailMax = x 
    | otherwise   = tailMax
    where tailMax = mymax xy


mymax2 :: (Ord a) => [a] -> a
mymax2 []     = error "No elements is array!"
mymax2 (x:[]) = x
mymax2 (x:xy) = max x (mymax2 xy)   


take' :: (Num i, Ord i) => i -> [a] -> [a]  
take' n _  
    | n <= 0   = []
take' _ []     = []  
take' n (x:xs) = x : take' (n-1) xs  
    

divideByTen = (/10)

isUpperAlphanum :: Char -> Bool  
isUpperAlphanum = (`elem` ['A'..'Z'])  

applyTwice :: (a -> a) -> a -> a  
applyTwice f x = f (f x)


zipWith' :: (a -> b -> c) -> [a] -> [b] -> [c]
zipWith' _ _ []          = []
zipWith' _ [] _          = []
zipWith' f (x:xs) (y:ys) = f x y : zipWith' f xs ys

flip' :: (a -> b -> c) -> b -> a -> c
flip' f a b = f b a

thesum = \x y -> x + y

foldl' :: (acc -> a -> acc) -> acc -> [a] -> acc
foldl' _ acc []     = acc
foldl' f acc (x:xs) = foldl' f (f acc x) xs 

foldr' :: (a -> acc -> acc) -> acc -> [a] -> acc
foldr' _ acc []     = acc
foldr' f acc (x:xs) = f x (foldr' f acc xs)

sum' :: (Num a) => [a] -> a  
sum' xs = foldl (+) 0 xs  

-- assume first element is base accumulator for folding
sum'' xs = foldl1 (+) xs


-- with fold...
map' :: (a -> b) -> [a] -> [b]
map' _ [] = []
-- map' f xs = foldl (\acc x -> acc ++ [f x]) [] xs can do it like this... but this one is expensive and the other one is much cheaper
map' f xs = foldr (\x acc -> f x : acc) [] xs


-- TODO: make this with foldr
filter'' :: (a -> Bool) -> [a] -> [a]
filter'' _ [] = []
filter'' p xs = foldl (accumulateBy p) [] xs
    where accumulateBy p acc x 
                   | p x       = acc ++ [x]
                   | otherwise = acc  

filter' :: (a -> Bool) -> [a] -> [a]
filter' _ [] = []
filter' p xs = foldr (\x acc -> if p x then x:acc else acc) [] xs



