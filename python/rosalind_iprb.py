from asyncore import read
from typing import Tuple

def read_data(filepath: str) -> Tuple[int, int, int]:
    with open(filepath, mode='r') as file:
        line = file.read().rstrip('\n')
        return tuple(map(int, line.split()))

def write_data(filepath: str, data: float) -> None:
    with open(filepath, mode='w') as file:
        file.write(str(data))

def dominant_allele_probability(homozygous_dominant: int, heterozygous_recessive: int, homozygous_recessive: int) -> float:
    k = homozygous_dominant
    m = heterozygous_recessive
    n = homozygous_recessive

    t = k + m + n
    
    pk = k / t
    pm = m / t
    pn = n / t

    # homozygous recessive & homozygous recessive
    c1 = pn * ((n - 1) / (t - 1))

    # homozygous recessive & heterozygous recessive
    c2 = 2 * pn * (m / (t - 1)) * 0.5

    # heterozygous recessive & heterozygous recessive
    c3 = pm * ((m - 1) / (t - 1)) * 0.25

    return 1 - (c1 + c2 + c3)

if __name__ == '__main__':
    data = read_data('input/rosalind_iprb.txt')
    result = dominant_allele_probability(*data)
    write_data('output/rosalind_iprb.txt', result)