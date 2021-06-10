/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package gr.geompokon.bitarray.benchmark.vsarraylist;

import gr.geompokon.bitarray.BitArray;
import gr.geompokon.bitarray.benchmark.TestMethods;
import gr.geompokon.bitarray.benchmark.state.ListState;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static gr.geompokon.bitarray.benchmark.state.TestStates.AddTestSizeState;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(5)
@Warmup(iterations = 0, time = 1, timeUnit = TimeUnit.NANOSECONDS)
@Measurement(iterations = 1, time = 1, timeUnit = TimeUnit.NANOSECONDS)
public class Add {

    public static void main(String[] args) throws RunnerException {
        new Runner(
                new OptionsBuilder()
                        .include(".*Add*")
                        .build()
        ).run();
    }

    public static class BitArrayState extends ListState<Boolean> {
        @Override
        public void setUp() {
            obj = new BitArray(10);
        }
    }

    public static class ArrayListState extends ListState<Boolean> {
        @Override
        public void setUp() {
            obj = new ArrayList<>(10);
        }
    }

    // BENCHMARKS

    @Benchmark
    public BitArrayState BitArrayAdd(BitArrayState bitArrayState, AddTestSizeState testSizeState) {
        Random rand = ThreadLocalRandom.current();
        TestMethods.addRandomIndex(bitArrayState.obj, rand, testSizeState, rand::nextBoolean);
        return bitArrayState;
    }

    @Benchmark
    public ArrayListState ArrayListAdd(ArrayListState arrayListState, AddTestSizeState testSizeState) {
        Random rand = ThreadLocalRandom.current();
        TestMethods.addRandomIndex(arrayListState.obj, rand, testSizeState, rand::nextBoolean);
        return arrayListState;
    }

}
