#!/usr/bin/env python3
# day055v2.py
# By Sebastian Raaphorst, 2019.

# We'll pick IDs iteratively by converting from int to base-62 strings using what we implemented in V1 code of today.
import day055.day055_v1 as d55


class URLStore:
    """
    A storage for shortened URLs six digits in length.
    """
    def __init__(self):
        self.url_to_id = {}
        self.id_to_url = {}
        self.ids = self.__generate_ids()

    def __generate_ids(self) -> int:
        """
        Yield the next empty ID slot for a short URL.
        :return: the next empty ID, or raise an exception if all 62 ** 6 = 56800235584 shortened URLs are in use.
        """
        for i in range(62 ** 6):
            id = d55.base_10_to_arb(i)
            yield (6  - len(id)) * '0' + id

    def encode(self, url: str) -> str:
        """
        Given a URL, encode it and return its ID encoding.
        :param url: the URL to encode
        :return: the shortened ID associated with the URL
        """
        if url is None or not url:
            return None

        if url in self.url_to_id.keys():
            return self.url_to_id[url]

        next_id = next(self.ids)
        self.url_to_id[url] = next_id
        self.id_to_url[next_id] = url
        return next_id

    def decode(self, id) -> str:
        """
        Given an ID (which must be six alphanumeric digits long) that was previously associated with an encoded URL,
        return the original URL.
        :param id: the shortened ID associated with the URL
        :return: the original URL

        >>> url_store = URLStore()
        >>> urls = ["www.hotmail.com", "www.gmail.com", "http://slashdot.org", "reddit.com", "https://www.youtube.com/watch?v=SNcNfB7ctHw&t=1419s"]
        >>> set([url_store.decode(url) for url in urls]) == {None}
        True
        >>> ids = [url_store.encode(url) for url in urls]
        >>> False in [url_store.decode(url_store.encode(url)) == url for url in urls]
        False
        >>> False in [url_store.encode(url_store.decode(id)) == id for id in ids]
        False
        """
        if id is None or not id:
            return None

        return self.id_to_url.get(id, None)
