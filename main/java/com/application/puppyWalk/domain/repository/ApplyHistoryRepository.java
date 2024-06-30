package com.application.puppyWalk.domain.repository;

import com.application.puppyWalk.domain.ApplyHistory;

public interface ApplyHistoryRepository {

    ApplyHistory saveApplyHistory(ApplyHistory applyHistory);

    boolean existsApplyHistory(long id, long id1);
}