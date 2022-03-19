def read_data(filepath: str) -> str:
    with open(filepath, mode='r') as file:
        return file.read().rstrip('\n')

def write_data(filepath: str, data: str) -> None:
    with open(filepath, mode='w') as file:
        file.write(data)

def get_reverse_complement(dna: str) -> str:
    complements = {'A':'T', 'T':'A', 'C':'G', 'G':'C'}
    return ''.join(complements[symbol] for symbol in reversed(dna))
    
if __name__ == '__main__':
    data = read_data('input/rosalind_revc.txt')
    result = get_reverse_complement(data)
    write_data('output/rosalind_revc.txt', result)