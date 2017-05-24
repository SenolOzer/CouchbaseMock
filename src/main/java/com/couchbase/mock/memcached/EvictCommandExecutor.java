/*
 * Copyright 2017 Couchbase, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.couchbase.mock.memcached;

import com.couchbase.mock.memcached.protocol.BinaryCommand;
import com.couchbase.mock.memcached.protocol.BinaryResponse;
import com.couchbase.mock.memcached.protocol.ErrorCode;

public class EvictCommandExecutor implements CommandExecutor {
    @Override
    public void execute(BinaryCommand command, MemcachedServer server, MemcachedConnection client) {
        VBucketStore cache = server.getStorage().getCache(server, command.getVBucketId());

        if (cache.get(command.getKeySpec()) == null) {
            client.sendResponse(new BinaryResponse(command, ErrorCode.KEY_ENOENT));
        } else {
            client.sendResponse(new BinaryResponse(command, ErrorCode.SUCCESS));
        }
    }
}
