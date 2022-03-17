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
    dataset = [30, 22, 15]
    result = dominant_allele_probability(*dataset)
    print(result)