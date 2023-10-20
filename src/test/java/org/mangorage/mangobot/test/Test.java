/*
 * Copyright (c) 2023. MangoRage
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.mangorage.mangobot.test;


import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class Test {
    public static String getCallingClass() {
        var l = Thread.currentThread().getStackTrace();
        return l[l.length - 1].getClassName();
    }

    public static void println(String content) {
        System.out.println("[%s] [%s:27] [%s/INFO]: %s".formatted(OffsetDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), getCallingClass(), Thread.currentThread().getName(), content));
    }

    public static void test() {
        var l = Thread.currentThread().getStackTrace();

        System.out.println(l[l.length - 1].getClassName());
    }

    public static class Testing {
        public static void main(String[] args) {
            test();
        }
    }


    public static void main(String[] args) {
        var fileName = "test.txt";
        var ext = ".txt";
        var fileNameNoExt = fileName.substring(0, fileName.length() - ext.length());
        System.out.println(fileNameNoExt);

        int pointsPerDay = 200;
        int pointsPerDollar = 100 / 5;
        int days = 30;

        int potentialEarnedPoints = pointsPerDay * days;
        int earnedPoints = 529;

        int potentialTotalIncome = potentialEarnedPoints / pointsPerDollar;
        int totalIncome = earnedPoints / pointsPerDollar;

        System.out.println("Estimated Income: %s".formatted(potentialTotalIncome));
        System.out.println("Earned Income: %s".formatted(totalIncome));


        LocalDateTime a = LocalDateTime.now();

        System.out.println(a);
    }

}
