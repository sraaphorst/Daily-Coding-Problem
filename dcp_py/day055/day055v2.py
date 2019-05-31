#!/usr/bin/env python3
# day055v2.py
# By Sebastian Raaphorst, 2019.

# We'll pick IDs iteratively by converting from int to base-62 strings using what we implemented in V1 code of today.
import day055.day055v1 as d55

from hypothesis import strategies as st
from hypothesis import given
from more_strategies import url
from typing import List


class URLStore:
    """
    A storage for shortened URLs six digits in length.
    """
    def __init__(self):
        self.id = 0
        self.url_to_id = {}
        self.id_to_url = {}

    def __generate_id(self) -> int:
        """
        Yield the next empty ID slot for a short URL.
        :return: the next empty ID, or raise an exception if all 62 ** 6 = 56800235584 shortened URLs are in use.
        """
        url = d55.base_10_to_arb(self.id)
        if len(url) > 6:
            raise OverflowError("no more IDs left")
        self.id += 1

        # Tack on any necessary 0s to get to six digits.
        yield (6 - len(url)) * '0' + url

    def encode(self, url: str) -> str:
        """
        Given a URL, encode it and return its ID encoding.
        :param url: the URL to encode
        :return: the shortened ID associated with the URL
        """
        if url is None or not url:
            return None

        if url in self.url_to_id:
            return self.url_to_id[url]
        id = self.__generate_id()
        self.url_to_id[id] = url
        return id

    def decode(self, id) -> str:
        """
        Given an ID (which must be six alphanumeric digits long) that was previously associated with an encoded URL,
        return the original URL.
        :param id: the shortened ID associated with the URL
        :return: the original URL
        """
        if id is None or not id:
            return None

        return self.id_to_url.get(id, None)


@given(st.lists(url, min_size=0, max_size=5000))
def test_store(urls: List[str]):
    store = URLStore()
    for url in urls:
        assert(store.decode(url) is None)
    for url in urls:
        assert(store.encode(url) is not None)
    for url in urls:
        assert(store.decode(store.encode(url)) == url)
