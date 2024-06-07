package com.labseq.service;

import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.ValueCommands;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LabseqService {
    private final ValueCommands<String, Long> labseqNumbers;
    private static final long KEY_TTL = 60 * 60;

    public LabseqService(RedisDataSource ds) {
        labseqNumbers = ds.value(Long.class);
        labseqNumbers.set("0", 0L);
        labseqNumbers.set("1", 1L);
        labseqNumbers.set("2", 0L);
        labseqNumbers.set("3", 1L);
    }

    public long calculateLabseq(long n) {
        var longStr = String.valueOf(n);
        var value = labseqNumbers.get(longStr);
        if (value != null) {
            return value;
        }

        long temp = calculateLabseq(n - 4) + calculateLabseq(n - 3);
        labseqNumbers.setex(longStr, KEY_TTL, temp);
        return temp;
    }
}
