package ca.yorku.eecs.mack.demo_quotation_benchmark;

import android.util.Log;
import androidx.benchmark.junit4.BenchmarkRule;
import androidx.benchmark.BenchmarkState;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ca.yorku.eecs.mack.demo_quotation_25741.DemoQuotationActivity;

/**
 * <p>
 * <p>What has been added</p>
 * <blockquote>
 * <p>
 * added and tried the microbenchmark package to benchmark the two most resource intensive test cases
 * </p>
 * </blockquote>
 * </p>
 */
@RunWith(AndroidJUnit4.class)
public class DemoQuotationActivityTestBenchmark {
    @Rule
    public BenchmarkRule mBenchmarkRule = new BenchmarkRule();

    /*
     * default generated code from creating the benchmark module
     */
    @Test
    public void log() {
        final BenchmarkState state = mBenchmarkRule.getState();
        while (state.keepRunning()) {
            Log.d("LogBenchmark", "the cost of writing this log method will be measured");

        }
    }

}