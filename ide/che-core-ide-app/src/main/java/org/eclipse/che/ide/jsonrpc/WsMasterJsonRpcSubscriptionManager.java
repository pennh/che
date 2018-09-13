/*
 * Copyright (c) 2012-2018 Red Hat, Inc.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Red Hat, Inc. - initial API and implementation
 */
package org.eclipse.che.ide.jsonrpc;

import static org.eclipse.che.ide.api.jsonrpc.Constants.WS_MASTER_JSON_RPC_ENDPOINT_ID;

import com.google.inject.Singleton;
import javax.inject.Inject;
import org.eclipse.che.api.core.jsonrpc.commons.RequestHandlerConfigurator;
import org.eclipse.che.api.core.jsonrpc.commons.RequestTransmitter;

/** Workspace master client side subscription manager */
@Singleton
public class WsMasterJsonRpcSubscriptionManager extends JsonRpcSubscriptionManager {

  @Inject
  public WsMasterJsonRpcSubscriptionManager(
      RequestTransmitter requestTransmitter,
      RequestHandlerConfigurator requestHandlerConfigurator) {
    super(requestTransmitter, requestHandlerConfigurator, WS_MASTER_JSON_RPC_ENDPOINT_ID);
  }
}
