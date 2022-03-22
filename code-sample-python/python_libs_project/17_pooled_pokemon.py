import time
import urllib.request
from concurrent.futures import ThreadPoolExecutor


def get_pokemon(url: str) -> str:
    print(f"starting get on url: {url}")

    # throws 403 for this particular URL now
    req = urllib.request.urlopen(url)

    # parse json here
    # return pokemon['name']
    return "kek"


def main():
    with ThreadPoolExecutor(max_workers=5) as executor:
        urls = [f'https://pokeapi.co/api/v2/pokemon/{i}' for i in range(1, 3)]
        result = executor.map(get_pokemon, urls, timeout=60)

        return list(result)


if __name__ == '__main__':
    start_time = time.time()
    res = main()
    print("****")
    print(res)

    print("--- %s seconds ---" % (time.time() - start_time))
