{-# LANGUAGE InstanceSigs #-}

{-# OPTIONS
  -Wall
  -Wno-missing-signatures
  -Wno-type-defaults
#-}


module Main where

import Data.Semigroup

-- Сегодня у нас аппликативные функторы

{-

class Functor f => Applicative f where
    pure  :: a -> f a
    (<*>) :: f (a -> b) -> f a -> f b

-- Т.е. внутри функтора не просто a, а какая-то функция, из а в б. 

У него есть законы:

1. f <*> pure x == fmap ($ x) f
2. pure f <*> x == fmap f x

-}


-- А сейчас напишем DSL для инвалидации...

data ValidationResult a
  = Errors [String]
  | Ok a [String]
  deriving Show

newtype Validation src dest
  = Validation { validate :: src -> ValidationResult dest }

instance Functor (Validation src) where
  fmap :: (a -> b) -> Validation src a -> Validation src b
  fmap f (Validation g) = Validation $ \x -> case g x of
    Errors es     -> Errors es
    Ok v ws       -> Ok (f v) ws

instance Semigroup (Validation a b) where
  (Validation v1) <> (Validation v2) = 
    Validation $ \x -> case (v1 x, v2 x) of
        (Errors e1, Errors e2) -> Errors (e1 ++ e2)
        (Ok _ w1  , Ok x w2)   -> Ok x (w1 ++ w2) 
        (Errors e1, _)         -> Errors e1
        (_        , Errors e2) -> Errors e2

instance Applicative (Validation src) where
  pure x = Validation $ \_ -> Ok x []
  Validation vf <*> Validation vx = Validation $ \x ->
    case (vf x, vx x) of
      (Errors e1, Errors e2) -> Errors (e1 ++ e2)
      (Ok f w1  , Ok v w2)   -> Ok (f v) (w1 ++ w2) 
      (Errors e1, _)         -> Errors e1
      (_        , Errors e2) -> Errors e2
  


ageV :: Validation Int Int
ageV = check "Negative age!" (> 0)
     <> note "Maybe too young!" (> 20)
     <> note "Maybe too old!" (< 80)


{-
ageV = Validation $ \x -> 
  if x < 0 then Errors ["Negative age!"]
  else if x < 18 then Ok x ["Maybe too young"]
  else Ok x [] 
-}

check, note :: String -> (a -> Bool) -> Validation a a
check err test = Validation $ \x ->
  if test x then Ok x []
  else Errors [err]
note warning test = Validation $ \x ->
  Ok x $ if test x 
         then []
         else [warning]



nameV :: Validation String String
nameV = check "Empty name!" (not . null)

data User = User
 { userName :: String
 , userAge :: Int
 }

field :: (a -> b) -> Validation b c -> Validation a c
field f (Validation g) = Validation $ \x -> g (f x) 

userV :: Validation User User
userV = User 
    <$> field userName nameV 
    <*> field userAge ageV

 

main = do
  print a 
  where
    a = validate ageV 3 

