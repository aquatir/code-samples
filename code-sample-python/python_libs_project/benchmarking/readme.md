# Benchmarking

### timeit module
```
python3 -m timeit -s "import tinyfunc; tinyfunc.func()"
```

### cProfile module
```
python3 -m cProfile tinyfunc.py
```

### line_profiler
install
```
pip3 install line_profiler
```

run
```
kernprof -l tinyfunc.py
```

can also run in verbose mode
```
kernprof -l -v biggerfunc.py
```

### memory_profiler
install
```
pip3 install memory_profiler
```

run
```
python3 -m memory_profiler memo_func.py 
```

or
```
mprof run mem_func.py
```

can plot the results with matplotlib
```
pip3 install matplotlib
```
```
mprof plot
```

### profilehooks
install
```
pip3 install profilehooks
```

run either in code (see mem_func_hooks.py) or
```
python3 -m profilehooks mem_func_hooks.py
```