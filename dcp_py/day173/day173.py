#!/usr/bin/env python3
# day173.py
# By Sebastian Raaphorst


def flattener(data):
    """
    Flatten a nested dictionary. Namespace the keys with a period, assuming no periods in the keys.
    :param dictionary: the nested dictionary
    :return: the flattened dictionary with namespaces

    >>> flattener([]) is None
    True
    >>> flattener(3) is None
    True
    >>> flattener({})
    {}
    >>> flattener({'key': 3, 'foo': {'a': 5, 'bar': {'baz': 8}}})
    {'key': 3, 'foo.a': 5, 'foo.bar.baz': 8}
    """
    if not isinstance(data, dict):
        return None

    def aux(dictionary, result=None, base_key=None):
        if result is None:
            result = {}

        actual_key = f"{base_key}." if base_key is not None else ""

        for key, value in dictionary.items():
            if isinstance(value, dict):
                result = aux(value, result, actual_key + key)
            else:
                result[f"{actual_key}{key}"] = value
        return result

    return aux(data)

