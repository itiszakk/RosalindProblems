from typing import Dict, Tuple

def read_data(filepath: str) -> Dict[str, str]:
    with open(filepath, mode='r') as file:
        fasta_dict = {}
        lines = file.read().strip().split('>')

        for line in lines:
            
            if not line:
                continue

            elements = line.split()
            fasta_dict[elements[0]] = ''.join(elements[1:])
        
        return fasta_dict
        
def write_data(filepath: str, data: Tuple[str, float]) -> None:
    with open(filepath, mode='w') as file:
        file.write(data[0] + '\n' + str(data[1]))

def gc_content(dna: str) -> float:
    count = 0
    
    for symbol in dna:
        
        if symbol == 'G' or symbol == 'C':
            count += 1
    
    return 100 * count / len(dna)

def find_max_gc_content(fasta_dict: Dict[str, str]) -> Tuple[str, float]:
    max_gc_content = ('', 0.0)

    for label in fasta_dict:
        current_gc_content = (label, gc_content(fasta_dict[label]))
        
        if current_gc_content[1] > max_gc_content[1]:
            max_gc_content = current_gc_content
        
    return max_gc_content

if __name__ == '__main__':
    data = read_data('input/rosalind_gc.txt')
    result = find_max_gc_content(data)
    write_data('output/rosalind_gc.txt', result)