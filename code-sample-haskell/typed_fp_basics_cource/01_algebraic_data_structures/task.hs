-- Ссылка на задание: https://gist.github.com/astynax/1eb88e195c4bab2b8d31d04921b18dd0
-- Нужно сделать только часть 1

-- module ADT_TASK where


-- Откуда-нибудь заимпортить...?
data List a = Nil | Cons a (List a)              deriving (Show)
data ListNonEmpty a = ListNonEmpty a (List a)    deriving (Show)


-- Фишки и колонки 
---  
--- 1. Как задать Pillar - как конкретную имплементацию ListNonEmpty с параметром Chip?
--- 2. Figure (Color) Pillar | Chip -  это получилась пара из цвета и либо столбца либо фишки, так? 
--- 3. А как создать с Either <Pillar, Chip> ? В чем отличие A|B и Either A|B ? Что вообще такое Either?
--- v     

data Color = Red | Blue                            deriving (Show) -- Доступные цвета фишки
data Chip                                           -- Бесцветная фишка

type Pillar = ListNonEmpty Chip                     -- Столбик фишек одного цвета это просто непустой список
data Figure = Figure Color Pillar                   -- Фигура - это непустой список фишек с цветом. 

-- Поле
---
--- 1. Как корректно задать 4х4 массив? Это ведь не список позиций...
--- 2. В чем разница? data Row = Row (Quadruple Field) и type Row = Quadruple Field
--- v


type Quadruple a = (a, a, a, a)
first  (v, _, _, _) = v
second (_, v, _, _) = v
third  (_, _, v, _) = v
fourth (_, _, _, v) = v 

data Place = First | Second | Third | Fourth             -- Где может находится фигура на одной из осей
data Position = Position Place Place                     -- На двух осях
type From = Position
type To = Position

data Field = Field Position (Maybe Figure)               -- Поле - это координаты + наличие или отсутствие фигуры на них

data Row = Row (Quadruple Field)                         -- Ряд - четверка полей. Надо будет самому следить, чтобы позиции в них не повторялись...?
data Board = Board (Quadruple Row)                       -- Доска - это четверка рядов, т.е. четверка четверок полей

--
-- Ходы игроков
--
-- 1. Опять RedTurn | BlueTurn - это не Either?
-- v

data Move = Move From To       -- Движение 1 игрока - это откуда подвинуть и куда подвинуть (куда, возможно, являются фишками противника). Игрок при создании хода двигает только координатами. Мы сами определяем, есть ли что-то в

data TurnType = Movement | Attack    -- Движение - это либо перемещение своих фишек, либо атака фишек противника.
type History = ListNonEmpty (Move) --  История игры - это последовательность ходов. В принципе она не нужно, но она позволит откатываться назад. Сюда можно добавить тип хода. История - не пустой список, так как пустой список - это самое начало игры.

data InProgress = RedTurn | BlueTurn
data Done = RedWon | BlueWon 
data GameState = InProgress | Done   -- Показывает текущее состояние игры
data Game = Game Board GameState History -- Сама игра - это текущее состояние фишек на доске + история всей игры


---
--- Функции
---

-- move :: From -> To -> Game -> Maybe Game
-- 1. Проверить по Game.Board (From), есть ли на поле соответствующая фишка
-- 2. Проверить по From и Game.State, правильный ли игрок пытается сделать ход
-- 3. Проверить по From и To, соседние ли они
-- 4. Проверить по цвету From и To движение это или атака. Если движение -> подвинуть. Если атака -> убрать 1 фишку у врага. Если фишек осталось больше 0 -> убрать одну у атакующего.
-- 5. Обновить в Board поля From и To.
-- 6. Добавить запись в History. Возможно с TurnType
   



-- gameIsOver:: Game -> Maybe Done
-- 1. Проверить GameState. Если InProgress -> Nothing, иначе -> Done


main = print "kek cheburek"

