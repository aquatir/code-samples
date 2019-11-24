module ADT 
( Point(..) -- export all constructors   
, Shape(..) 
, surface   -- exported functions
, nudge  
, baseCircle  
, baseRect  
) where

data Point = Point Float Float deriving (Show) 
data Shape = Circle Point Float | Rectangle Point Point   
    deriving Show

surface :: Shape -> Float  
surface (Circle _ r) = pi * r ^ 2  
surface (Rectangle (Point x1 y1) (Point x2 y2)) = (abs $ x2 - x1) * (abs $ y2 - y1)  

nudge :: Shape -> Float -> Float -> Shape  
nudge (Circle (Point x y) r) a b = Circle (Point (x+a) (y+b)) r  
nudge (Rectangle (Point x1 y1) (Point x2 y2)) a b = Rectangle (Point (x1+a) (y1+b)) (Point (x2+a) (y2+b))  

baseCircle :: Float -> Shape  
baseCircle r = Circle (Point 0 0) r  
      
baseRect :: Float -> Float -> Shape  
baseRect width height = Rectangle (Point 0 0) (Point width height)  


data Person = Person { firstName :: String  
                     , lastName :: String  
                     , age :: Int  
                     , height :: Float  
                     , phoneNumber :: String  
                     , flavor :: String  
                     } deriving (Show, Read) 


instance Eq Person where
    Person fn1 ln1 a1 h1 p1 f1 == Person fn2 ln2 a2 h2 p2 f2 = fn1 == fn2 && ln1 == ln2 && a1 == a2 && h1 == h2 && p1 == p2 && f1 == f2

data Day = Monday | Tuesday | Wednesday | Thursday | Friday | Saturday | Sunday
    deriving (Eq -- can check for equasion
    , Ord -- now order, e.g. Monday < Sunday
    , Show -- can print
    , Read -- can read from text
    , Bounded -- has smallest and larges numbers (minBound and maxBound)
    , Enum)   -- has predessors and sucessors (pred and succ)


errOnToBig :: Int -> Either String Int 
errOnToBig a 
    | a > 50    = Left "Number is too big!"
    | otherwise = Right a


data List a = Empty | Cons a (List a) deriving (Show, Eq)
data Tree a = EmptyTree | Node a (Tree a) (Tree a) deriving (Show, Eq)

singletonTree a = Node a EmptyTree EmptyTree
treeInsert :: (Ord a) => a -> Tree a -> Tree a
treeInsert x EmptyTree = singletonTree x
treeInsert x (Node a left right) 
    | x >= a  = Node a left (treeInsert x right)
    | x < a  = Node a (treeInsert x left) right

treeElem :: (Ord a) => a -> Tree a -> Bool  
treeElem x EmptyTree = False  
treeElem x (Node a left right)  
    | x == a = True  
    | x < a  = treeElem x left  
    | x > a  = treeElem x right

-- j     == *->*
-- a     == *
-- j a   == *
-- a j   == * -> (* -> *)
-- t     == *... because if's a function returning one values, thus:
-- t a j == {* -> (* -> *) this part is from 'a j'} -> *
-- t a j == * -> (* -> *) -> *
class Tofu t where  
    tofu :: j a -> t a j  


-- Frank     == *
-- a         == * -> *
-- b         == *
-- Frank a b == * -> (* -> *) -> * same thing as above
data Frank a b  = Frank {frankField :: b a} deriving (Show)  

instance Tofu Frank where
    tofu x = Frank x -- we cut everything out because 'Frank a b' has exactly the same kinds semantics (if it's the correct word) as 'Tofu'

