class Course:

    def __init__(self, language, level):
        self.language = language
        self.level = level


if __name__ == '__main__':
    print("Metaprogramming starting")

    n = 10  # Creating an int object
    l = ['a', 'b']  # Creating a list
    course = Course('Python', 'advanced')  # Creating a Course object

    # Printing the types of objects
    print(type(n))
    print(type(l))
    print(type(course))
    print("****")
    print(type(Course))  # printing the type of class. It prints <class 'type'> which is a metaclass

    print("****")

    print("Adding behavior to classes and objects")


    class A:
        pass


    A.x = 10
    A.function = lambda self: f"Number is: '{A.x}'"

    print(A.x)
    obj = A()
    print(obj.function(), obj.x)

    print("****")
    print("Creating class with type(...) call")
    A = type('A', (), {})
    obj = A()
    print(obj)

    print("Class B which inherits from A and also adds and x variable")
    B = type('B', (A,), dict(x=0))
    obj = B()
    print(f'x: "{obj.x}"')
    print(f'class: "{obj.__class__}"')
    print(f'bases: "{obj.__class__.__bases__}"')

    print("Class C which also have both functions and variables")
    C = type('C', (), {'n': 2, 'function': lambda self: self.n * 2, 'mult_by': lambda self, x: self.n * x})
    obj = C()
    print(f'n: "{obj.n}"')
    print(f'no arg func: "{obj.function()}"')
    print(f'1 arg func: "{obj.mult_by(5)}"')


    def f(x, y):
        return x.n * y


    print("class D where the func definition is passed from the outside")
    D = type('D', (), {'n': 2, 'function': f})
    obj = D()
    print(f'n: "{obj.n}"')
    print(f'func call: "{obj.function(5)}"')

    print("****")
    print("Defining your own metaclasses")


    class Meta(type):

        def __new__(cls, name, bases, dct):
            x = super().__new__(cls, name, bases, dct)  # Using __new__ of type
            x.attr = 1  # Making a class variable
            return x  # Returning a class


    class A(metaclass=Meta):
        pass


    print(f'attr of A: "{A.attr}')
    print("****")
