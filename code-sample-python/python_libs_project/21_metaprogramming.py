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