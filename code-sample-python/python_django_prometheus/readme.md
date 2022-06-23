# Overview

Django + django-rest apps with prometheus and Grafana

## Setup dev

- run `pip install -r requirments.txt`
- go to `docker` folder and run `docker-compose up`
- navigate to Grafana at `http://localhost:3060` and add prometheus with hostname `http://host.docker.internal:9090`

## Setup prod


```
gunicorn python_django_prometheus.wsgi --workers 2 --threads 4  --access-logfile -
```

Does work with preload according to https://github.com/korfuri/django-prometheus/blob/master/documentation/exports.md

### Usage

- FOR DEV setup go to `http://127.0.0.1:8000/metrics` to get raw metrics
- go to `http://127.0.0.1:9090` to get metrics on prometheus
- got o `http://127.0.0.1:3060` to create Grafana dashboards