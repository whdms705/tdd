package com.example.tdd.ch7.stub;

import com.example.tdd.ch7.AutoDebitInfo;
import com.example.tdd.ch7.AutoDebitInfoRepository;

import java.util.HashMap;
import java.util.Map;

// Repositor 대역
// Map으로 저장 조회 대체
public class MemoryAutoDebitInfoRepository implements AutoDebitInfoRepository {
    private Map<String, AutoDebitInfo> infos = new HashMap<>();

    @Override
    public void save(AutoDebitInfo info) {
        infos.put(info.getUserId(), info);
    }

    @Override
    public AutoDebitInfo findOne(String userId) {
        return infos.get(userId);
    }
}
