# Refactoring Java 8 code with Java 17 new features

URL: https://www.youtube.com/watch?v=wW7uzc61tZ8&list=PLX8CzqL3ArzV4BpOzLanxd4bZr46x5e87&index=15

In this video there is a comparison made between how Eclipse Collections can be used and Java 17 in
terms of performance.

For an accurate comparison jmh is used, so we can have an accurate comparison

1) distinct_letters_EC vs distinct_letters_Java17

Benchmark Mode Cnt Score Error Units Haiku.distinct_letters_EC avgt 5 0.025 ± 0.004 ms/op
Haiku.distinct_letters_Java17 avgt 5 0.022 ± 0.002 ms/op

Readability is in favor of EC. Performance is quite equal.

2) For the top letters there are multiple comparisons

top_letters_EC top_letters_Java17_V1 top_letters_Java17_V2 top_letters_Java17_V3_record

We refactored the code to use records in V3

In terms of performance:
Haiku.top_letters_EC avgt 5 0.019 ± 0.002 ms/op Haiku.top_letters_Java17_V1 avgt 5 0.038 ± 0.001
ms/op Haiku.top_letters_Java17_V2 avgt 5 0.035 ± 0.001 ms/op Haiku.top_letters_Java17_V3_record avgt
5 0.028 ± 0.002 ms/op

The performance of EC is still better than all options in Java 17.

3) For unique and duplicate

Haiku.duplicate_and_unique_EC avgt 5 0.023 ± 0.001 ms/op Haiku.duplicate_and_unique_Java17 avgt 5
0.040 ± 0.003 ms/op Haiku.duplicate_and_unique_Java17_more_efficient_by_partitioning avgt 5 0.040 ±
0.006 ms/op

There should be a larger difference between the two Java versions, but my laptop is quite old. 