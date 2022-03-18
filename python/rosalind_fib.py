def count_rabbits_pairs(months: int, litter: int) -> int:
    if months <= 2:
        return 1
    else:
        return count_rabbits_pairs(months - 1, litter) + litter * count_rabbits_pairs(months - 2, litter)

if __name__ == '__main__':
    dataset = [31, 2]
    result = count_rabbits_pairs(*dataset)
    print(result)