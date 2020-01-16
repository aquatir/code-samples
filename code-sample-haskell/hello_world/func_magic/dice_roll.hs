module DiceRoll where

import Control.Applicative
import System.Random

--
-- Implement a function rollNDiceIO :: Int -> IO [Int] that, given an integer (a number of die rolls), returns a list of that number of pseudo-random integers between 1 and 6.
--

rollTwoDiceIO :: IO (Int, Int)
rollTwoDiceIO = liftA2 (,) (randomRIO (1,6)) (randomRIO (1,6))


-- sequenceA turns [IO Int] into IO [Int] 
rollNDiceIO :: Int -> IO [Int]
rollNDiceIO dices = sequenceA $ map (\_ -> randomRIO (1,6)) $ takeWhile (< dices) [0..]  
	
