#!/usr/bin/env python3
# day055v2.py
# By Sebastian Raaphorst, 2019.

# We'll pick IDs iteratively by converting from int to base-62 strings using what we implemented in V1 code of today.
import day055.day055_v1 as d55

from typing import List
from hypothesis import strategies as st
from hypothesis import given


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

    def decode(self, id: str) -> str:
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


# This is a rough URL regular expression, far from complete but sufficient for our purposes.
# This could use some cleaning up as PyCharm complains that there are invalid sequences.
url_re = r'^(https?://)?(www[.])?[-a-zA-Z0-9@:%._\+~#=]{2,256}\.[a-z]{2,6}(/[-a-zA-Z0-9@:%._\+~#=]{1,256})'\
         r'*(\?[a-zA-Z0-9]+=[a-zA-Z0-9]+(&[a-zA-Z0-9]+=[a-zA-Z0-9]+)*)?$'


@given(st.lists(st.from_regex(url_re, fullmatch=True)))
def test_store(urls: List[str]):
    """
    Test the URL store with a variety of generated URLs.
    :param urls: the generated list of URLs
    """
    store = URLStore()
    encs = {url: store.encode(url) for url in urls}
    for url in urls:
        assert(len(encs[url]) == 6)
        assert(store.encode(url) == encs[url])
        assert(store.decode(encs[url]) == url)
    assert(len(set(encs.values())) == len(encs.values()))


