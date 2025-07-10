package com.example.test.demo.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class NumberController {

    private static final BigInteger INT_MAX = BigInteger.valueOf(Integer.MAX_VALUE);

    private static final Random random = new Random();

    @GetMapping("/primes")
    public List<Integer> getPrimes() {
        int size = getRandomSize();
        List<Integer> primes = new ArrayList<>();
        int num = 2;
        while (primes.size() < size) {
            if (isPrime(num)) {
                primes.add(num);
            }
            num++;
        }
        return primes;
    }

    @GetMapping("/fibo")
    public List<Long> getFibonacci() {
        int size = getRandomSize();
        List<Long> fibo = new ArrayList<>();
        Long a = 0L, b = 1L;
        while (fibo.size() < size) {
            fibo.add(a);
            Long c = a + b;
            a = b;
            b = c;
        }
        return fibo;
    }

    @GetMapping("/even")
    public List<Integer> getEvens() {
        int size = getRandomSize();
        List<Integer> evens = new ArrayList<>();
        int num = 2;
        while (evens.size() < size) {
            evens.add(num);
            num += 2;
        }
        return evens;
    }

    @GetMapping("/random")
    public List<Integer> getRandomNumbers() {
        int size = getRandomSize();
        Set<Integer> set = new HashSet<>();
        while (set.size() < size) {
            set.add(random.nextInt(1000)); // Random numbers between 0 and 999
        }
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        return list;
    }

     @GetMapping("/analyse/{num}")
    public Response analyse(@PathVariable String num) {
        Response response = new Response();
        response.setNumber(num);

        try {
            BigInteger number = new BigInteger(num);

            // If number > Integer.MAX_VALUE
            if (number.compareTo(INT_MAX) > 0) {
                response.setPrime("Number too large");
                response.setFactorial("Number too large");
                return response;
            }

            int n = number.intValue();

            // Prime check
            response.setPrime(isPrime(n) ? "Yes" : "No");

            // Factorial: Don't compute if n > 20
            if (n > 20) {
                response.setFactorial("Factorial too large");
            } else {
                BigInteger fact = factorial(n);
                response.setFactorial(fact.toString());
            }

        } catch (NumberFormatException e) {
            response.setPrime("Invalid number");
            response.setFactorial("Invalid number");
        }

        return response;
    }

    private boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    private BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    // Response class
    static class Response {
        private String number;
        private String isPrime;
        private String factorial;

        public String getNumber() { return number; }
        public void setNumber(String number) { this.number = number; }

        public String getPrime() { return isPrime; }
        public void setPrime(String prime) { isPrime = prime; }

        public String getFactorial() { return factorial; }
        public void setFactorial(String factorial) { this.factorial = factorial; }
    }

    private int getRandomSize() {
        return 6 + random.nextInt(45); // Random size between 6 and 50
    }
}