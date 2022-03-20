import doctest
import my_docs


#  This will load adn run doc tests from my_docs folder when you run
#  python3 -m unittest discover
#  the name must me 'load_tests' in order for test to be discovered

def load_tests(loader, tests, ignore):
    tests.addTests(doctest.DocTestSuite(my_docs))
    return tests
