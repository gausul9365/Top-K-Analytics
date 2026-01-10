package com.finding.top_k.util;

import com.finding.top_k.dto.TopKResponse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class TopKCalculator {

    public static List<TopKResponse> findTopK(
            List<TopKResponse> data,
            int k
    ) {

        // Min-heap based on count
        PriorityQueue<TopKResponse> minHeap =
                new PriorityQueue<>(Comparator.comparingLong(TopKResponse::getCount));

        for (TopKResponse item : data) {
            minHeap.offer(item);

            // keep heap size <= k
            if (minHeap.size() > k) {
                minHeap.poll(); // remove weakest
            }
        }

        // heap contains top K, but unordered
        return new ArrayList<>(minHeap);
    }
}
