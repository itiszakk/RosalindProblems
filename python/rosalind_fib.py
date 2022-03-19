from typing import Tuple

def read_data(filepath: str) -> Tuple[int, int]:
    with open(filepath, mode='r') as file:
        line = file.read().rstrip('\n')
        return tuple(map(int, line.split()))

def write_data(filepath: str, data: int) -> None:
    with open(filepath, mode='w') as file:
        file.write(str(data))

def count_rabbits_pairs(months: int, litter: int) -> int:
    if months <= 2:
        return 1
    else:
        return count_rabbits_pairs(months - 1, litter) + litter * count_rabbits_pairs(months - 2, litter)

if __name__ == '__main__':
    data = read_data('input/rosalind_fib.txt')
    result = count_rabbits_pairs(*data)
    write_data('output/rosalind_fib.txt', result)