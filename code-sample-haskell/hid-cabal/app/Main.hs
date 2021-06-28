{-# LANGUAGE OverloadedStrings #-}

module Main where

import Data.Char
import Data.List (group, sort)
import qualified Data.Text as T
import qualified Data.Text.IO as TIO
import System.Environment

-- import Lib

doubleSecond :: Num t => [t] -> [t]
doubleSecond xs = zipWith (*) xs $ concat (repeat [1, 2])

main :: IO ()
main = putStrLn "Hello, World!"
  -- [fname] <- getArgs
  -- text <- TIO.readFile fname
  -- let ws = map head $ group $ sort $ map T.toCaseFold $ filter (not . T.null)
  --          $ map (T.dropAround $ not . isLetter) $ T.words text
  -- TIO.putStrLn $ T.unwords ws
  -- print $ length ws
