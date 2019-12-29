#!/usr/bin/env python3
# By Sebastian Raaphorst, 2019.

from typing import List


def form_array(msg: str, height: int) -> List[List[str]]:
    """
    Form an array of size height x len(msg) containing the message in msg in a zig-zag pattern.
    :param msg: the message to use to populate the array
    :param height: the height of the array
    :return: the array containing the message
    """
    if not msg:
        return [[]]
    if height < 1:
        raise ValueError("height must be positive.")
    length = len(msg)

    # Create the sparse array to store the characters.
    array = [[' ' for _ in range(length)] for _ in range(height)]

    down = True
    posx, posy = 0, 0
    for ch in msg:
        array[posx][posy] = ch
        posy += 1

        # Check if it's time to switch directions.
        if (posx + 1) % height == 0:
            down = False
        if posx == 0:
            down = True

        if down:
            posx += 1
        else:
            posx -= 1
    return array


def print_message(msg: str, height: int):
    """
    Prints msg in a zig-zag horizontal pattern across the screen, with the specified height.
    :param msg: the message to use to populate the array
    :param height: the height of the array
    """
    array = form_array(msg, height)
    rows = [''.join(row) for row in array]
    print('\n'.join(rows))


if __name__ == '__main__':
    print_message('I LOVE PUSSY CATS I LOVE PUSSY CATS', 5)
