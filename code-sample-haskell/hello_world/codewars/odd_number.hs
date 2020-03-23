module OddNumber where

-- | Given a list, find the [Int] that appears an 
--   odd number of times. The tests will always
--   provide such a number, and the list will
--   always contain at least one element.

import Data.Map as M

findOdd :: [Int] -> Int
findOdd numbers = findOddEntry $ asMapList numbers  
    where asMapList :: [Int] -> [(Int, Int)]
          asMapList numbers = M.toList $ Prelude.foldl (\acc b -> M.insertWith (\_ old -> old + 1) b 1 acc) (M.fromList []) numbers           
     
          findOddEntry :: [(Int, Int)] -> Int
          findOddEntry [] = 0
          findOddEntry ((x, y):xs)
              | odd y     = y
              | otherwise = findOddEntry xs
