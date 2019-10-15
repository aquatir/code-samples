module Main where 

import Prelude hiding (map, filter, zip, taske, head, zipWith, zipWith3)

filter :: (a -> Bool) -> [a] -> [a]
filter _ [] = []
filter p (x:xs) = 
    if p x 
    then x : filter p xs 
    else filter p xs

zip :: [a] -> [b] -> [(a,b)]
zip _     [] = []
zip []    _  = []
zip (x:xs) (y:ys) = (x,y): zip xs ys


zipWith :: (a -> b -> c) -> [a] -> [b] -> [c]
zipWith _ _ [] = []
zipWith _ [] _ = []
zipWith f (x:xs) (y:ys) = f x y : zipWith f xs ys

zipWith3 :: (a -> b -> c -> d) -> [a] -> [b] -> [c] -> [d]
zipWith3 _ _ _ [] = []
zipWith3 _ _ [] _ = []
zipWith3 _ [] _ _ = []
zipWith3 f (x:xs) (y:ys) (z:zs) = f x y z : zipWith3 f xs ys zs

main = do 
    putStrLn "kek"

-- main = funk 5

