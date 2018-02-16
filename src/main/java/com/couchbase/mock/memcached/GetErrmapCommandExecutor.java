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
import com.couchbase.mock.memcached.protocol.BinaryGetErrmapCommand;
import com.couchbase.mock.memcached.protocol.BinaryGetErrmapResponse;
import com.couchbase.mock.memcached.protocol.BinaryResponse;
import com.couchbase.mock.memcached.protocol.ErrorCode;
import com.couchbase.mock.util.ReaderUtils;

import java.io.IOException;

/**
 * Created by mnunberg on 12/9/16.
 */
public class GetErrmapCommandExecutor implements CommandExecutor {
    private final static String ERRMAP_V1;
    static {
        try {
            ERRMAP_V1 = ReaderUtils.fromResource("errmap/errmap_v1.json");
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load error map!", e);
        }
    }

    @Override
    public BinaryResponse execute(BinaryCommand cmdBase, MemcachedServer server, MemcachedConnection client) {
        BinaryGetErrmapCommand cmd = (BinaryGetErrmapCommand)cmdBase;
        // Get the version:
        short version = cmd.getVersion();
        if (version < 1) {
            return new BinaryResponse(cmd, ErrorCode.KEY_ENOENT);
        } else {
            return new BinaryGetErrmapResponse(cmd, ERRMAP_V1);
        }
    }
}
