module Divisors where

divisors :: (Show a, Integral a) => a -> Either String [a]
divisors a 
    | (length) actualDivisors == 0 = Left $ (show a) ++ " is prime"
    | otherwise                    = Right $ actualDivisors
    where actualDivisors   = filterOnlyDivisors a possibleDivisors  
          possibleDivisors = [2..a `div` 2]

filterOnlyDivisors :: (Integral a) => a -> [a] -> [a]
filterOnlyDivisors a possible = filter (/= 1) $ filter (\x -> a `mod` x == 0) possible


