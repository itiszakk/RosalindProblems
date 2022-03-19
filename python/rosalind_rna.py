def read_data(filepath: str) -> str:
    with open(filepath, mode='r') as file:
        return file.read().rstrip('\n')

def write_data(filepath: str, data: str) -> None:
    with open(filepath, mode='w') as file:
        file.write(data)

def dna_to_rna(dna: str) -> str:
    return dna.replace('T', 'U')

if __name__ == '__main__':
    data = read_data('input/rosalind_rna.txt')
    result = dna_to_rna(data)
    write_data('output/rosalind_rna.txt', result)