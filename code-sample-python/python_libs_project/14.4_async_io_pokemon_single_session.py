import asyncio
import time

import aiohttp
from aiohttp import ClientSession


# Still can't make this example work with a single client session...

async def get_pokemon(url, session):
    print(f"starting get on url: {url}")
    async with session.get(url) as resp:
        pokemon = await resp.json()
        print(f"got response on url: {url}")
        return pokemon['name']


async def main():
    tasks = []

    conn = aiohttp.TCPConnector(limit=100)
    async with ClientSession(connector=conn) as session:
        for i in range(1, 5):
            url = f'https://pokeapi.co/api/v2/pokemon/{i}'
            task = asyncio.create_task(get_pokemon(url.format(i), session))
            tasks.append(task)

        print("waiting on all responses")
        responses = await asyncio.gather(*tasks)
        print("wait finished")
        print(responses)


if __name__ == '__main__':
    start_time = time.time()
    loop = asyncio.get_event_loop()
    loop.run_until_complete(main())

    print("--- %s seconds ---" % (time.time() - start_time))
