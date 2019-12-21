module Files where

import System.IO  

readMyFile :: FilePath -> IO ()
readMyFile file = do  
    handle <- openFile file ReadMode  
    contents <- hGetContents handle  
    putStr contents  
    hClose handle  


readMyFileWithLambda :: FilePath -> IO () 
readMyFileWithLambda file = do     
    withFile file ReadMode (\handle -> do  
        contents <- hGetContents handle     
        putStr contents)  

readMyFileWithReadFile :: FilePath -> IO ()
readMyFileWithReadFile file = do
    contents <- readFile file
    putStr contents


