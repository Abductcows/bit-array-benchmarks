# BitArray benchmarks
Benchmark results for the latest version of the [BitArray](https://github.com/Abductcows/java-bit-array) class. Comparisons so far only include the ArrayList<Boolean> class.

BitArray version: 1.0.1

(Message included with JMH results)  
REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
experiments, perform baseline and negative tests that provide experimental control, make sure
the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
Do not assume the numbers tell you what you want them to tell.

Results:   
Tested with JMH 1.32 (src included corresponds to this results section).  
OS: Windows 10 Home  
JDK: OpenJDK Hotspot 15.0.2  
(If there is anything else that is important let me know)  
  

## NO JVM warmup

Random index `add()` (initial size 10)
<pre>
Benchmark         (ADD_TEST_SIZE)  Mode  Cnt             Score             Error  Units
Add.ArrayListAdd             1000  avgt    5       1753600.000 ±      712866.467  ns/op
Add.ArrayListAdd           100000  avgt    5     182594240.000 ±    11493545.589  ns/op
Add.ArrayListAdd          1500000  avgt    5  122252979540.000 ± 28608386866.697  ns/op
Add.BitArrayAdd              1000  avgt    5       1802480.000 ±      459975.769  ns/op
Add.BitArrayAdd            100000  avgt    5      34709300.000 ±     2200343.035  ns/op
Add.BitArrayAdd           1500000  avgt    5    5299160200.000 ±   171565866.779  ns/op
</pre>
  
Random index `get()`
<pre>
Benchmark         (GET_TEST_SIZE)  Mode  Cnt            Score            Error  Units
Get.ArrayListGet          5000000  avgt   15    160616953.333 ±   14001015.171  ns/op
Get.ArrayListGet         50000000  avgt   15   1905612646.667 ±  100799817.907  ns/op
Get.ArrayListGet        250000000  avgt   15  11170633226.667 ± 1557366661.489  ns/op
Get.BitArrayGet           5000000  avgt   15     59911220.000 ±     634701.094  ns/op
Get.BitArrayGet          50000000  avgt   15    946289186.667 ±  270223355.738  ns/op
Get.BitArrayGet         250000000  avgt   15   8992084866.667 ±  724290733.784  ns/op
</pre>
  
Random index `set()` with negation of previous element
<pre>
Benchmark         (SET_TEST_SIZE)  Mode  Cnt            Score            Error  Units
Set.ArrayListSet          5000000  avgt   15    605210446.667 ±   71854235.559  ns/op
Set.ArrayListSet         50000000  avgt   15   7099144433.333 ±  403505309.060  ns/op
Set.ArrayListSet        250000000  avgt   15  39553312373.333 ± 1830106839.510  ns/op
Set.BitArraySet           5000000  avgt   15     94037333.333 ±    1463357.049  ns/op
Set.BitArraySet          50000000  avgt   15   1063333660.000 ±   40603563.839  ns/op
Set.BitArraySet         250000000  avgt   15   8873798526.667 ±   76510782.555  ns/op
</pre>

Random index `remove()` of all elements
<pre>
Benchmark               (REMOVE_TEST_SIZE)  Mode  Cnt            Score            Error  Units
Remove.ArrayListRemove                1000  avgt    5       481640.000 ±     838427.762  ns/op
Remove.ArrayListRemove              100000  avgt    5    184570700.000 ±    3988406.727  ns/op
Remove.ArrayListRemove             1000000  avgt    5  29080169499.800 ± 6646909241.029  ns/op
Remove.BitArrayRemove                 1000  avgt    5       646840.000 ±     412944.409  ns/op
Remove.BitArrayRemove               100000  avgt    5     35386080.200 ±    2515204.122  ns/op
Remove.BitArrayRemove              1000000  avgt    5   2295145679.800 ±   28725521.024  ns/op
</pre>

## WITH JVM warmup (1 warmup iteration, 1 execution)
  
Random index `add()` (initial size 10)
<pre>
Benchmark         (ADD_TEST_SIZE)  Mode  Cnt             Score             Error  Units
Add.ArrayListAdd             1000  avgt    5        181312.583 ±       55026.533  ns/op
Add.ArrayListAdd           100000  avgt    5     176370700.200 ±     4237978.727  ns/op
Add.ArrayListAdd          1500000  avgt    5  114485534580.200 ± 30819558546.621  ns/op
Add.BitArrayAdd              1000  avgt    5        176779.600 ±      130184.516  ns/op
Add.BitArrayAdd            100000  avgt    5      28058159.800 ±     1138979.871  ns/op
Add.BitArrayAdd           1500000  avgt    5    5534838940.400 ±   144473047.224  ns/op
</pre>
    
Random index `get()`
<pre>
Benchmark         (GET_TEST_SIZE)  Mode  Cnt            Score            Error  Units
Get.ArrayListGet          5000000  avgt   15    154219626.667 ±   10450705.384  ns/op
Get.ArrayListGet         50000000  avgt   15   1964578246.667 ±  248254205.739  ns/op
Get.ArrayListGet        250000000  avgt   15  11659269960.000 ± 3947954199.811  ns/op
Get.BitArrayGet           5000000  avgt   15     57861853.333 ±    2490168.220  ns/op
Get.BitArrayGet          50000000  avgt   15    730634180.000 ±   39620530.197  ns/op
Get.BitArrayGet         250000000  avgt   15   8848587800.000 ±  287880758.626  ns/op
</pre>
  
Random index `set()` with negation of previous element
<pre>
Benchmark         (SET_TEST_SIZE)  Mode  Cnt            Score            Error  Units
Set.ArrayListSet          5000000  avgt   15    600861940.000 ±   57843007.975  ns/op
Set.ArrayListSet         50000000  avgt   15   4794014286.667 ±  265542024.876  ns/op
Set.ArrayListSet        250000000  avgt   15  38092874946.667 ± 1420715928.985  ns/op
Set.BitArraySet           5000000  avgt   15     85382920.000 ±     769557.363  ns/op
Set.BitArraySet          50000000  avgt   15   1003798473.333 ±   28599449.251  ns/op
Set.BitArraySet         250000000  avgt   15   8624668673.333 ±   27363471.190  ns/op
</pre>

Random index `remove()` of all elements
<pre>
Benchmark               (REMOVE_TEST_SIZE)  Mode  Cnt            Score            Error  Units
Remove.ArrayListRemove                1000  avgt    5       162520.000 ±     271912.915  ns/op
Remove.ArrayListRemove              100000  avgt    5    178706260.000 ±   19063020.729  ns/op
Remove.ArrayListRemove             1000000  avgt    5  30393170620.000 ± 8098330872.082  ns/op
Remove.BitArrayRemove                 1000  avgt    5       196420.000 ±     184579.925  ns/op
Remove.BitArrayRemove               100000  avgt    5     26267860.000 ±    1760295.846  ns/op
Remove.BitArrayRemove              1000000  avgt    5   2355821960.000 ±  260728699.192  ns/op
</pre>


