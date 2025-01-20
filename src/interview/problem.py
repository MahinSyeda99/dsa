def min_replacements(arr):
    n = len(arr)
    replacements = 0
    prefix_sum = [0] * (n + 1)
    min_val = float('inf')
    min_pos = 0

    for i in range(n):
        prefix_sum[i + 1] = prefix_sum[i] + arr[i]
        if prefix_sum[i + 1] < min_val:
            min_val = prefix_sum[i + 1]
            min_pos = i + 1

    if min_val < 0:
        replacements += 1
        prefix_sum[min_pos] = -min_val + 1

    min_val = float('inf')
    for i in range(n):
        if prefix_sum[i + 1] < min_val:
            min_val = prefix_sum[i + 1]

    if min_val < 0:
        replacements += 1

    return replacements