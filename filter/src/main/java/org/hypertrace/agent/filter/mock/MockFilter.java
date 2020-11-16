/*
 * Copyright The Hypertrace Authors
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

package org.hypertrace.agent.filter.mock;

import io.opentelemetry.trace.Span;
import java.util.Map;
import org.hypertrace.agent.filter.ExecutionBlocked;
import org.hypertrace.agent.filter.ExecutionNotBlocked;
import org.hypertrace.agent.filter.Filter;
import org.hypertrace.agent.filter.FilterResult;

/** Mock filter, blocks execution if an attribute with "mockblock" key is present. */
public class MockFilter implements Filter {

  MockFilter() {}

  @Override
  public FilterResult evaluateRequestHeaders(Span span, Map<String, String> headers) {
    if (headers.containsKey("mockblock")) {
      span.setAttribute("hypertrace.mock.filter.result", "true");
      return ExecutionBlocked.INSTANCE;
    }
    return ExecutionNotBlocked.INSTANCE;
  }

  @Override
  public FilterResult evaluateRequestBody(Span span, String body) {
    return ExecutionNotBlocked.INSTANCE;
  }
}