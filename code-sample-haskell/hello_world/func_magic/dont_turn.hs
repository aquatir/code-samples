module DontTurn where 

-- Don't go in circle!
-- Can not make 4 turnLefts or turnRights one after another
-- Example of recursion with Monad in turnMore function (turn to one 

type Turns = Int
data Direction = L | R 

turnLeft, turnRight :: Turns -> Maybe Turns
turnLeft turns    
    | turns - 1 == -4 = Nothing
    | otherwise       = Just $ turns - 1
 
turnRight turns 
    | turns + 1 == 4 = Nothing
    | otherwise      = Just $ turns + 1

turn :: Direction -> Turns -> Maybe Turns
turn L turns = turnLeft turns
turn R turns = turnRight turns

-- make a turn to required direction specified number of times
turnMore :: Direction -> Int -> Turns -> Maybe Turns
turnMore dir n turns 
    | n < 0     = undefined -- this should probably be something else... Returning Nothing here is wrong, because calling this function with negative value is againts it's contract and out Nothing means 'we turned to one size too much'  
    | n == 0    = Just turns 
    | otherwise = turnF turns >>= turnMore dir (n-1)
    where turnF = turnFunction dir

turnFunction :: Direction -> (Turns -> Maybe Turns) 
turnFunction R = turnRight
turnFunction L = turnLeft

testTurns = return 0 >>= turnLeft >>= turn L >>= turnMore L 1 >>= turnMore R 6

