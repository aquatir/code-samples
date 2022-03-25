### Overview

Python Django Tips and Tricks from official guide https://docs.djangoproject.com/en/4.0/intro/tutorial01/

- views and routs
- using redirects after form post-data processing
- using django shortcuts such as `render` and `get_object_or_404`
- using generic views

#### Running

```
python3 manage.py runserver
```

Then go to
```
http://localhost:8000
```

#### See how migration is going to look like in SQL

```
python3 manage.py sqlmigrate polls 0001
```

#### Check you project
```
python3 manage.py check
```


#### Open python shell with pre-loaded settings module

```
python manage.py shell
```
