import asyncio
import time

from aiohttp import ClientSession


# create 150 requests simultaneously, gather their result and finish

async def get_pokemon(url):
    print(f"starting get on url: {url}")
    async with ClientSession() as session:
        async with session.get(url) as resp:
            pokemon = await resp.json()
            print(f"got response on url: {url}")
            return pokemon['name']


async def main():
    tasks = []

    for i in range(1, 150):
        url = f'https://pokeapi.co/api/v2/pokemon/{i}'
        task = asyncio.create_task(get_pokemon(url.format(i)))
        tasks.append(task)

    responses = await asyncio.gather(*tasks)
    print(responses)


if __name__ == '__main__':
    start_time = time.time()
    loop = asyncio.get_event_loop()
    loop.run_until_complete(main())

    print("--- %s seconds ---" % (time.time() - start_time))
