module InputOutput where

fun = do
    putStrLn "Hello, what's your name?"  
    name <- getLine  
    putStrLn ("Hey " ++ name ++ ", you rock!")  


main = do   
    line <- getLine  
    if null line  
        then return ()  
        else do
            putStrLn $ reverseWords line  
            main  

reverseWords :: String -> String
reverseWords = unwords . map reverse . words
