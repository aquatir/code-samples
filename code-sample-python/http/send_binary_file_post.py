import requests

url = 'http://localhost:8050/model/excel'
headers = {'Content-Type': 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
           'Content-Disposition': 'attachment;filename=ANALYTICS_STATS.xlsx'}
data = open('/tmp/ANALYTICS_STATS.xlsx', 'rb').read()
res = requests.post(url=url,
                    data=data,
                    headers=headers)
print(res)
