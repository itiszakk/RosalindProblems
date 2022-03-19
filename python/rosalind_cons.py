from typing import Dict, List

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
        
def write_data(filepath: str, profile: Dict[str, List[int]], consensus: str) -> None:
    with open(filepath, mode='w') as file:
        file.write(consensus + '\n')
    
        for symbol in sorted(profile.keys()):
            file.write('{}: {}\n'.format(symbol, ' '.join(map(str, profile[symbol]))))

def get_profile(fasta_dict: Dict[str, str]) -> Dict[str, List[int]]:
    profile = {}

    for label, string in fasta_dict.items():
        for index, symbol in enumerate(string):

            if symbol not in profile.keys():
                profile[symbol] = [0] * len(string)

            profile[symbol][index] += 1

    return profile

def get_consensus(profile: Dict[str, List[int]]) -> str:
    consensus = ""
    
    profile_keys = list(profile.keys())
    consensus_length = len(profile[profile_keys[0]])
    
    for index in range(consensus_length):
        most_common_symbol = ('', 0)
        
        for symbol in profile_keys:
            current_symbol = (symbol, profile[symbol][index])
            
            if current_symbol[1] > most_common_symbol[1]:
                most_common_symbol = current_symbol
        
        consensus += most_common_symbol[0]
    
    return consensus

if __name__ == '__main__':
    data = read_data('input/rosalind_cons.txt')
    profile = get_profile(data)
    consensus = get_consensus(profile)
    write_data('output/rosalind_cons.txt', profile, consensus)     