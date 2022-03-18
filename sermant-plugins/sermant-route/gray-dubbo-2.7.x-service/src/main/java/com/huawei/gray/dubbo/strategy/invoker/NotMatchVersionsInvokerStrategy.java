/*
 * Copyright (C) 2021-2022 Huawei Technologies Co., Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.huawei.gray.dubbo.strategy.invoker;

import com.huawei.gray.dubbo.strategy.InvokerStrategy;
import com.huawei.gray.dubbo.strategy.VersionStrategy;

import java.util.Set;

/**
 * 匹配不在notMatchVersions中的invoker
 *
 * @author provenceee
 * @since 2021-12-08
 */
public class NotMatchVersionsInvokerStrategy implements InvokerStrategy {
    /**
     * 匹配不在notMatchVersions中的invoker
     *
     * @param invoker Invoker
     * @param version 目标版本
     * @param notMatchVersions 没有匹配上的版本
     * @param versionStrategy 版本策略
     * @return 是否匹配
     */
    @Override
    public boolean isMatch(Object invoker, String version, Set<String> notMatchVersions,
        VersionStrategy versionStrategy) {
        // 由于notMatchVersions里面的版本号已经匹配过了且没有匹配上，所以要剔除掉，不能参与负载均衡，否则会导致流量比例不正确（会偏高）
        return !notMatchVersions.contains(versionStrategy.getVersion(invoker));
    }
}