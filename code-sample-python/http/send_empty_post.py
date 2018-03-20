import requests

url = 'http://localhost:8000/actuator/refresh'
res = requests.post(url=url,
                    data={})
print(res)

