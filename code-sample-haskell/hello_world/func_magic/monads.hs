module DiveIntoMonads where

import Control.Monad.Writer
import Control.Monad.State



monadReturn = return "WHAT" :: Maybe String  
monadOnJust = Just 9 >>= \x -> return (x*10)  
monadOnNothing = Nothing >>= \x -> return $ x ++ " that all!"

fooUgly, fooLessUgly, fooDoNotion :: Maybe String 

fooUgly = Just 3 >>= (\x -> Just "!" >>= (\y -> Just (show x ++ y)))  

fooLessUgly = Just 3   >>= (\x -> 
              Just "!" >>= (\y -> 
              Just (show x ++ y)))

fooDoNotion = do  -- look how this one is similar to 'fooLessUgly'... just less ugly
    x <- Just 3  
    y <- Just "!"  
    Just (show x ++ y)  


justH :: Maybe Char  
justH = do  
    (x:xs) <- Just "hello" -- pattern mathching works with do-notion!  
    return x  


-- This pattern matching will fail, so Monad's function 'fail' will be called. But 'fail' is imlemented as 'fail _ = Nothing', so we gen Nothing from this call
wopwop :: Maybe Char  
wopwop = do  
    (x:xs) <- Just ""  
    return x  

-- Fails in List Monad will be handles as empty list '[]' just as fails in Maybe Monad will be 'Nothing'
failListMonadOne = [] >>= \x -> ["bad","mad","rad"]  
failListMonadTwo = [1,2,3] >>= \x -> []  

listMonadExample = [1,2] >>= \n -> ['a','b'] >>= \ch -> return (n,ch)
-- Guards are used in conjunction with MonadPlus (Monad + Monoid typeclass) in lists to make list compherension works... Oh magic haskell!
guardWorks = guard (5 > 2) >> return "cool" :: [String]  
guardFails = guard (1 > 2) >> return "cool" :: [String]  


-- The Writer monoid!
writerOne = runWriter (return 3 :: Writer String Int)  
writerTwo = runWriter (return 3 :: Writer (Sum Int) Int)  

logNumber :: Int -> Writer [String] Int  
logNumber x = writer (x, ["Got number: " ++ show x])  
  
multWithLog :: Writer [String] Int  
multWithLog = do  
    a <- logNumber 3  
    b <- logNumber 5  
    tell ["Gonna multiply these two!"]
    return (a*b)  


-- gcd with logging
gcd' :: Int -> Int -> Writer [String] Int  
gcd' a b  
    | b == 0 = do  
        tell ["Finished with " ++ show a]  
        return a  
    | otherwise = do  
        tell [show a ++ " mod " ++ show b ++ " = " ++ show (a `mod` b)]  
        gcd' b (a `mod` b)  


gcdPrint a b = mapM_ putStrLn $ snd $ runWriter (gcd' a b)


-- -> '(->) r' Monad (or also called Reader monad)
addStuff :: Int -> Int  
addStuff = do  
    a <- (*2)  
    b <- (+10)  
    return (a+b)   

addMoreStuff :: Int -> Int  
addMoreStuff = do
    a <- (*2)
    b <- (+5)
    c <- (+ (-3))
    return (a + b + c)


-- State Monad

-- Stack example without 'State' monad
type Stack = [Int]  
      
pop :: Stack -> (Int,Stack)  
pop (x:xs) = (x,xs)  
      
push :: Int -> Stack -> ((),Stack)  
push a xs = ((),a:xs)  

stackManip :: Stack -> (Int, Stack)  
stackManip stack = let  
    ((),newStack1) = push 3 stack  
    (a ,newStack2) = pop newStack1  
    in pop newStack2  


-- Stack example WITH state monad

statePop :: State Stack Int
statePop = state $ \(x:xs) -> (x,xs)
  
statePush :: Int -> State Stack ()  
statePush a = state $ \xs -> ((),a:xs)

stackManipState :: State Stack Int  
stackManipState = do  
    statePush 3  
    a <- statePop  
    statePop


-- MonadState in 'Control.Monad.State' allows to get and replace current state
stackyStack :: State Stack ()  
stackyStack = do  
    stackNow <- get  
    if stackNow == [1,2,3]  
        then put [8,3,1]  
        else put [9,2,1]  



-- Make a state example which holds current and previous value of some integer
plus, minus  :: Int -> State Int Int

plus a  = state $ \x -> (x, x+a)
minus a = state $ \x -> (x, x-a)

-- runState (return 0 >>= (\x -> state $ \y -> (x,x+y))) 5
-- WTF...?!?!?!?

testState = do
    plus 50
    a <- minus 25
    plus 100

-- How to write this without do-notion...?
