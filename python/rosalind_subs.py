from typing import Tuple, List

def read_data(filepath: str) -> Tuple[str, str]:
    with open(filepath, mode='r') as file:
        return tuple(file.read().split())

def write_data(filepath: str, data: List[int]) -> None:
    with open(filepath, mode='w') as file:
        file.write(' '.join(map(str, data)))

def get_substring_positions(dna: str, sub: str) -> List[int]:
    return [i + 1 for i in range(len(dna)) if dna.startswith(sub, i)]

if __name__ == '__main__':
    data = read_data('input/rosalind_subs.txt')
    result = get_substring_positions(*data)
    write_data('output/rosalind_subs.txt', result)