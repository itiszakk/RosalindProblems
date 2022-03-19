from typing import Tuple

def read_data(filepath: str) -> Tuple[str, str]:
    with open(filepath, mode='r') as file:
        return tuple(file.read().split())

def write_data(filepath: str, data: int) -> None:
    with open(filepath, mode='w') as file:
        file.write(str(data))

def count_mutations(first_dna: str, second_dna: str) -> int:
    mutations = 0
    for index, symbol in enumerate(first_dna):
        if symbol != second_dna[index]:
            mutations += 1
    return mutations
    
if __name__ == '__main__':
    data = read_data('input/rosalind_hamm.txt')
    result = count_mutations(*data)
    write_data('output/rosalind_hamm.txt', result)