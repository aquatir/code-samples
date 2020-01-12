module FunctorsAndMonoids where

import Data.Char  
import Data.List 
import Data.Monoid  
import Control.Applicative 
import qualified Data.Foldable as F  
      

fmapIO = do line <- fmap (intersperse '-' . reverse . map toUpper) getLine
            putStrLn line  

-- Works with partial application for '+' function which essentialy has a singnature of 'Num a => a -> a -> a'
magic  = pure (+) <*> Just 3 <*> Just 5

-- With partial application this works!
magic3 = pure (sum3) <*> Just 10 <*> Just 100 <*> Just 1000 

fmapNormal = fmap (+3) [1, 2, 3]
fmapInfix  = (+3) <$> [1, 2, 3]

sum3 :: Int -> Int -> Int -> Int
sum3 a b c = a + b + c

-- the next two functions show where applicative functors works really cool
-- the catch is that both lines looks almost the same, but the second line works with 'Maybe'. Which can in fact be any other 'Functor'
concatNormal =  (++) "johntra" "volta"  
concatInMaybe = (++) <$> Just "johntra" <*> Just "volta"  

-- IO is also an applicative functor
glueTwoLines = (++) <$> getLine <*> getLine

zipListExample = getZipList $ max <$> ZipList [1,2,3,4,5,3] <*> ZipList [5,3,1,2] 

-- Magic start happening here
-- sequenceA :: (Applicative f) => [f a] -> f [a]  
-- sequenceA = foldr (liftA2 (:)) (pure [])  

useSequence = sequenceA [Just 3, Just 2, Just 1] 


-- Ordering is a Monoid!
-- If one string is lesser/greater then the other -> returns LT/GT
-- If strings are equal by length -> compare lexicographicaly... using monoid concan with length compare!
lengthCompare :: String -> String -> Ordering  
lengthCompare x y = (length x `compare` length y) `mappend`  
                    (x `compare` y)  
    where vowels = length . filter (`elem` "aeiou")  

-- Maybe are monoids denifed as 'instance Monoid a => Monoid (Maybe a) where', but there are also 2 useful typeclasses
maybeWithFirst = getFirst $ First (Nothing) `mappend` First (Just 5) `mappend` First (Just 3) -- result is Just 5
maybeWithLast  = getLast  $ Last (Nothing) `mappend` Last (Just 5) `mappend` Last (Just 3)    -- result is Just 3 



data Tree a = Empty | Node a (Tree a) (Tree a) deriving (Show, Read, Eq)  
instance F.Foldable Tree where  
    foldMap f Empty = mempty  
    foldMap f (Node x l r) = F.foldMap f l `mappend`  
                             f x           `mappend`  
                             F.foldMap f r

testTree = Node 5  
            (Node 3  
                (Node 1 Empty Empty)  
                (Node 6 Empty Empty)  
            )  
            (Node 9  
                (Node 8 Empty Empty)  
                (Node 10 Empty Empty)  
            )    

-- this maps values of a tree to Any Monoid and then calculate this monoid's value which considering the implementation of Any should be true if any
-- of values is True
checkAnyIsThree = getAny $ F.foldMap (\x -> Any $ x == 3) testTree

-- First maps each element into an array and then calculate array's monoid which is - concatenate all elements!
turnToArray tree = F.foldMap (\x -> [x]) tree  

