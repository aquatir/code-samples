module Main where

import Data.List
import Data.Function (on)

-- import Data.List (nub, sort) import only some 
-- import Data.List hiding (nub)  import without something 

import qualified Data.Map as Map -- we can refer to Data.Map functions with M... like so: M.filter
import qualified Data.Set as Set 
-- import qualified Data.Map -- this will allow us to refer functions as Data.Map.filter if we need a function from this module

numUniques :: (Eq a) => [a] -> Int  
numUniques = length . nub


-- simple substring search. This is the same is isInfixOf
search :: (Eq a) => [a] -> [a] -> Bool  
search needle haystack =   
    let nlen = length needle  
    in  foldl (\acc x -> if take nlen x == needle then True else acc) False (tails haystack) 

main = do 
    print $ break (==4) [1,2,3,4,5,6,7] 
    print $ let (fw, rest) = span (/=' ') "This is a sentence" in "First word:" ++ fw ++ ", the rest:" ++ rest
    print $ group [1,1,1,1,2,2,2,2,3,3,2,2,2,5,6,7]  
    print $ map (\ l@(x:xs) -> (x,length l)) . group . sort $ [1,5,6,3,2,1,2,5,3,3,2,6,2,5,4,1]   
    print $ let w = "w00t" in zip (inits w) (tails w)
    print $ "cat" `isInfixOf` "im a cat burglar" 
    print $ "hey" `isPrefixOf` "hey there!"  
    print $ "there!" `isSuffixOf` "oh hey there!"
    print $ 4 `elemIndex` [1,2,3,4,5,6]       
    print $ ' ' `elemIndices` "Where are the spaces?" 
    print $ findIndices (`elem` ['A'..'Z']) "Where Are The Caps?"
    print $ lines "first line\nsecond line\nthird line"
    print $ unlines ["first line", "second line", "third line"]  
    print $ words "hey these are the words in this sentence"
    print $ unwords ["hey","there","mate"] 
    print $ [1..10] \\ [2,5,9]  -- remove two, five and nine
    print $ [1..7] `union` [5..10] 
    print $ [1..7] `intersect` [5..10] 
    print $ groupBy ((==) `on` (> 0)) [-4.3, -2.4, -1.2, 0.4, 2.3, 5.9, 10.5, 29.1, 5.3, -2.4, -14.5, 2.9, 2.3]
    print $ Map.fromList [(1,2),(3,4),(3,2),(5,5)]
    print $ Set.fromList "privet"
    
