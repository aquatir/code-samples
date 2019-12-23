module InputOutput where

import Control.Monad 
import Data.Char

fun = do
    putStrLn "Hello, what's your name?"  
    name <- getLine  
    return "this"
    return "does"
    return "nothing!"
    putStrLn ("Hey " ++ name ++ ", you rock!")  


main = do   
    line <- getLine  
    if null line  
        then return ()  
        else do
            putStrLn $ reverseWords line  
            main  

buffering = do     
    c <- getChar  
    when (c /= ' ') $ do 
         putChar c  
         buffering  

readThreeTimes = do  
    rs <- sequence [getLine, getLine, getLine]  
    print rs 

colorAssociations = do   
    colors <- forM [1,2,3,4] (\a -> do  
        putStrLn $ "Which color do you associate with the number " ++ show a ++ "?"  
        color <- getLine  
        return color)  
    putStrLn "The colors that you associate with 1, 2, 3 and 4 are: "  
    mapM_ putStrLn colors  

foreverUpper = do
    forever $ do 
        putStrLn $ "write something" 
        userInput <- getLine
        putStrLn $ map toUpper userInput

contentsStuff = do 
    forever $ do 
        stuff <- getContents
        putStrLn $ map toLower stuff

printOnlyShort = do  
        contents <- getContents  
        putStr (shortLinesOnly contents)  
      
shortLinesOnly :: String -> String  
shortLinesOnly input =   
    let allLines = lines input  
        shortLines = filter (\line -> length line < 10) allLines  
        result = unlines shortLines  
    in  result  
        
printOnlyShortInteract = interact shortLinesOnly
printOnlyShortInteractOneLiner = interact $ unlines . filter ((<10) . length) . lines   

reverseWords :: String -> String
reverseWords = unwords . map reverse . words

readAndReverseWithFmap = do line <- fmap reverse getLine  
                            putStrLn $ "You said " ++ line ++ " backwards!"  
                            putStrLn $ "Yes, you really said " ++ line ++ " backwards!" 

