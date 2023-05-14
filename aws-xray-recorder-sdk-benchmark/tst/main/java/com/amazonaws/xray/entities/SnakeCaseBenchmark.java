/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.amazonaws.xray.entities;

import com.amazonaws.xray.utils.StringTransform;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@Measurement(iterations = 5, time = 1)
@Warmup(iterations = 10, time = 1)
@Fork(1)
@BenchmarkMode({Mode.Throughput, Mode.SampleTime})
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Threads(16)
public class SnakeCaseBenchmark {

    public static final PropertyNamingStrategies.NamingBase SNAKE_CASE_NAMING_STRATEGY = (PropertyNamingStrategies.NamingBase) PropertyNamingStrategies.SNAKE_CASE;

    @Benchmark
    public String ownSnakeCase() {
        return StringTransform.toSnakeCase("thisIsASampleStringForTestingYourToSnakeCaseFunctionWithDifferentWordsAndItIsExactlyOneHundredCharacters");
    }

    @Benchmark
    public String jacksonSnakeCase() {
        return SNAKE_CASE_NAMING_STRATEGY.translate("thisIsASampleStringForTestingYourToSnakeCaseFunctionWithDifferentWordsAndItIsExactlyOneHundredCharacters");
    }


    // Convenience main entry-point
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .addProfiler("gc")
                .include(".*" + SnakeCaseBenchmark.class.getSimpleName() + ".*")
                .build();

        new Runner(opt).run();
    }
}
