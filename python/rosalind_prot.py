from utils import rna_codons

def read_data(filepath: str) -> str:
    with open(filepath, mode='r') as file:
        return file.read().rstrip('\n')

def write_data(filepath: str, data: str) -> None:
    with open(filepath, mode='w') as file:
        file.write(data)

def rna_to_protain(rna: str) -> str:
    protain = ''
    for i in range(0, len(rna), 3):
        codon = rna[i:i+3]
        amino_acid = rna_codons.translation[codon]
        
        if amino_acid == 'Stop':
            break

        protain += amino_acid

    return protain

if __name__ == '__main__':
    data = read_data('input/rosalind_prot.txt')
    result = rna_to_protain(data)
    write_data('output/rosalind_prot.txt', result)