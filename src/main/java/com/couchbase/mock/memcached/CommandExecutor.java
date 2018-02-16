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

import java.net.ProtocolException;

/**
 * @author Trond Norbye
 */
interface CommandExecutor {
    /**
     * Execute a single command
     * @param cmd    The incoming command
     * @param server The server handling the command
     * @param client The client requesting the command
     */
    BinaryResponse execute(BinaryCommand cmd, MemcachedServer server, MemcachedConnection client) throws ProtocolException;
}
