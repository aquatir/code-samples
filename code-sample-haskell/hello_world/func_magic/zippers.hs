module Zippers where

data Tree a = Empty | Node a (Tree a) (Tree a) deriving (Show)  


-- Lets take this tree and change 'W' into 'R'. How would we do it?
freeTree :: Tree Char  
freeTree =   
    Node 'P'  
        (Node 'O'  
            (Node 'L'  
                (Node 'N' Empty Empty)  
                (Node 'T' Empty Empty)  
            )  
            (Node 'Y'  
                (Node 'S' Empty Empty)  
                (Node 'A' Empty Empty)  
            )  
        )  
        (Node 'L'  
            (Node 'W'  
                (Node 'C' Empty Empty)  
                (Node 'R' Empty Empty)  
            )  
            (Node 'A'  
                (Node 'A' Empty Empty)  
                (Node 'C' Empty Empty)  
            )  
        ) 

-- There is a not exacly clear pattern-matching method
changeToPPatternMatching :: Tree Char -> Tree Char  
changeToPPatternMatching (Node x l (Node y (Node _ m n) r)) = Node x l (Node y (Node 'P' m n) r)  

-- We can also tell where and when we need to turn for a change

data Direction = L | R deriving (Show)  
type Directions = [Direction]  
  
changeToP :: Directions-> Tree Char -> Tree Char  
changeToP (L:ds) (Node x l r) = Node x (changeToP ds l) r  
changeToP (R:ds) (Node x l r) = Node x l (changeToP ds r)  
changeToP [] (Node _ l r) = Node 'P' l r 


-- so now this call we change 'W' to 'P'

tryChangeToP = changeToP [R, L] freeTree



--- But what if we would split a tree in two parts -> the part where are in right now and all the stuff which we left behind when we went left or right?
--- Thats exactly what zippers do!

--- When we go let or right in a tree we always need to remember current node + either left or right part of the tree.
--- A data structure to remeber previous part of data structure is usually called a Crumb 
data Crumb a = LeftCrumb a (Tree a) | RightCrumb a (Tree a) deriving (Show)  

--- And multiple Crumbs are Breadcrumbs
type Breadcrumbs a = [Crumb a]  


--- Now we can define goLeft and goRight functions.
--- As you can see, when go to on	e of the sides of tree we do two things:
--- 1) Replace current tree with it's left or gith part
--- 2) Put the rest of the tree (the other half which we skipped) into breadcrumbs array
goLeft, goRight :: (Tree a, Breadcrumbs a) -> (Tree a, Breadcrumbs a)  

goLeft (Node x l r, bs) = (l, LeftCrumb x r:bs)  
goRight (Node x l r, bs) = (r, RightCrumb x l:bs)  


--- We can even go up with this implementation
--- This 3 functions allow us to navigate tree, while also providing a way to go back up if needed (in other words, we are focusing on some part of the tree at any given moment and we navigate the tree with goLeft, goRight and goUp. Navigation is working correctly because of breakcrumbs we have left)
goUp :: (Tree a, Breadcrumbs a) -> (Tree a, Breadcrumbs a)  
goUp (t, LeftCrumb x r:bs) = (Node x t r, bs)  
goUp (t, RightCrumb x l:bs) = (Node x l t, bs)  

-- Here is a type synonim for zipper
type Zipper a = (Tree a, Breadcrumbs a)
  
--- Lets also create a function which will modify a field in current focus
modify :: (a -> a) -> Zipper a -> Zipper a  
modify f (Node x l r, bs) = (Node (f x) l r, bs)  
modify f (Empty, bs) = (Empty, bs)  


--- And now we can change 'W' to 'P' by nagigating this tree using breadcrumbs
changeToPBreadcrumbs = modify (\_ -> 'P') (goRight (goLeft (freeTree,[])))  

--- We can also make a tiny helping function which will allow us to make navigation more clear
x -: f = f x  

changeToPBreadcrumbsReadable = (freeTree,[]) -: goLeft -: goRight -: modify (\_ -> 'P') 
