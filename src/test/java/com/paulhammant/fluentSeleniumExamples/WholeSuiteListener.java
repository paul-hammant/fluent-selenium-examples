package com.paulhammant.fluentSeleniumExamples;

import com.codahale.metrics.ConsoleReporter;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;
import org.seleniumhq.selenium.fluent.monitors.CodehaleMetricsMonitor;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

public class WholeSuiteListener extends RunListener {

    @Override
    public void testRunFinished(Result result) throws Exception {
        super.testRunFinished(result);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final ConsoleReporter reporter = ConsoleReporter.forRegistry(codehaleMetricsMonitor.getMetrics())
                .convertRatesTo(TimeUnit.MILLISECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .outputTo(new PrintStream(baos))
                .build();

        reporter.report();

        // ... for assertion

        System.out.println(baos.toString());

    }

    public static CodehaleMetricsMonitor codehaleMetricsMonitor = new CodehaleMetricsMonitor("com.paulhammant.fluentSeleniumExamples.");
}

