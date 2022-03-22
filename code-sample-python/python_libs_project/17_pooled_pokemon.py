import time
import urllib.request
import json
from concurrent.futures import ThreadPoolExecutor


def get_pokemon(url: str) -> str:
    print(f"starting get on url: {url}")

    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64)'
        , 'Accept': '*/*'
    }

    req = urllib.request.Request(url, headers=headers)
    with urllib.request.urlopen(req) as response:
        raw_resp = response.read()
        json_resp = json.loads(raw_resp)

        return json_resp['name']


def main():

    # try increasing number of workers and see how the speed changes
    with ThreadPoolExecutor(max_workers=5) as executor:
        urls = [f'https://pokeapi.co/api/v2/pokemon/{i}' for i in range(1, 150)]
        result = executor.map(get_pokemon, urls, timeout=60)

        return list(result)


if __name__ == '__main__':
    start_time = time.time()
    res = main()
    print("****")
    print(res)

    print("--- %s seconds ---" % (time.time() - start_time))
