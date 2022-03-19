from typing import List

def read_data(filepath: str) -> str:
    with open(filepath, mode='r') as file:
        return file.read().rstrip('\n')

def write_data(filepath: str, data: List[int]) -> None:
    with open(filepath, mode='w') as file:
        file.write(' '.join(map(str, data)))
        
def count_nucleotides(dna: str) -> List[int]:
    counters = {}
    for symbol in dna:
        if symbol not in counters.keys():
            counters[symbol] = 0
        counters[symbol] += 1
    print(counters)
    return [item[1] for item in sorted(counters.items())]

if __name__ == '__main__':
    data = read_data('input/rosalind_dna.txt')
    result = count_nucleotides(data)
    write_data('output/rosalind_dna.txt', result)
    