import requests
from bs4 import BeautifulSoup

# Could also use scrapy instead of beautifulsoup4

url = 'https://www.blog.pythonlibrary.org/'


def get_articles():
    """
    Get the articles from the front page of the blog
    """
    req = requests.get(url)
    html = req.text
    soup = BeautifulSoup(html, 'html.parser')
    pages = soup.findAll('h2')

    articles = {i.a['href']: i.text.strip()
                for i in pages if i.a}

    for article in articles:
        s = '{title}: {url}'.format(title=articles[article].encode('utf-8'), url=article)
        print(s)

    return articles


if __name__ == '__main__':
    print("scraping starting")
    articles = get_articles()

    print(articles)
