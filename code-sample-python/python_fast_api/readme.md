#### Overview

Examples of FastAPI http server lib:

- creating an app
- get/post requests
- passing query, optional query and body parameters
- responding without body
- creating background tasks

##### Starting server

```
uvicorn main:app --reload
```

##### Access docs

Docs:
```
http://localhost:8000/docs
```

Another view on docs with OpenAPI + download option:
```
http://localhost:8000/redoc
```