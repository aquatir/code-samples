import sqlite3
from contextlib import contextmanager, suppress, redirect_stdout, ExitStack

DB_NAME = ":memory"


# Either create a separate class with __enter__ and __exit__ methods
class DataConn:
    """"""

    def __init__(self, db_name):
        """Constructor"""
        self.db_name = db_name

    def __enter__(self):
        """
        Open the database connection
        """
        self.conn = sqlite3.connect(self.db_name)
        return self.conn

    def __exit__(self, exc_type, exc_val, exc_tb):
        """
        Close the connection
        """
        self.conn.close()
        if exc_val:
            raise


# OR use @contextmanager annotation
@contextmanager
def open_db_con(db_name):
    try:
        db_con = sqlite3.connect(db_name)
        yield db_con
    except OSError:
        print("We had an error!")
    finally:
        print('Closing db conn')
        db_con.close()


# essentially the same as above but creates a special 'closing' class instead of generic try/finally statement
@contextmanager
def closing(db_name):
    try:
        con = sqlite3.connect(db_name)
        yield con
    finally:
        con.close()


if __name__ == '__main__':
    print("context management running")

    # create a special wrapper around db connection
    with (DataConn(DB_NAME)) as con:
        con.execute("drop table if exists test")
        con.execute("create table test(id int, data text)")
        con.execute("insert into test values (0, 'hello')")
        con.execute("insert into test values (1, 'world')")
        con.commit()

    # open and close db connection with specific 'closing' function
    with(closing(DB_NAME)) as con:
        con.execute("insert into test values (3, 'again')")
        con.commit()

    # open and close db connection with generic function
    with (open_db_con(DB_NAME)) as con:
        cur = con.cursor()
        # for row in cur.execute("SELECT * FROM TEST"):
        #    print(row)

        cur.execute("SELECT * FROM TEST")
        data = cur.fetchall()

    print(data)

    # supress exceptions
    with suppress(FileNotFoundError):
        with open('fauxfile.txt') as fobj:
            for line in fobj:
                print(line)

    # redirect stdout. The same would work for stdin
    path = 'text.txt'
    with open(path, 'w') as fobj:
        with redirect_stdout(fobj):
            help(redirect_stdout)

    # create a stack which allows nesting different contexts inside it and close them in the correct order
    filenames = ["file.txt", "something.txt", "kekw.txt"]
    with ExitStack() as stack:
        stack.enter_context(suppress(FileNotFoundError))
        file_objects = [stack.enter_context(open(filename)) for filename in filenames]