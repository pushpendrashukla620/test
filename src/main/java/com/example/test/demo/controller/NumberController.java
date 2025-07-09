package com.example.test.demo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class NumberController {

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

    private int getRandomSize() {
        return 6 + random.nextInt(45); // Random size between 6 and 50
    }

    private boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }
}