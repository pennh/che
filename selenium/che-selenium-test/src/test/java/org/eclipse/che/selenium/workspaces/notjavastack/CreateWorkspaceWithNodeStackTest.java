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
package org.eclipse.che.selenium.workspaces.notjavastack;

import static org.eclipse.che.selenium.pageobject.dashboard.NewWorkspace.Stack.NODE;

import com.google.inject.Inject;
import org.eclipse.che.commons.lang.NameGenerator;
import org.eclipse.che.selenium.core.client.TestWorkspaceServiceClient;
import org.eclipse.che.selenium.core.user.DefaultTestUser;
import org.eclipse.che.selenium.core.webdriver.SeleniumWebDriverHelper;
import org.eclipse.che.selenium.core.workspace.TestWorkspace;
import org.eclipse.che.selenium.pageobject.CheTerminal;
import org.eclipse.che.selenium.pageobject.ProjectExplorer;
import org.eclipse.che.selenium.pageobject.dashboard.CreateWorkspaceHelper;
import org.eclipse.che.selenium.pageobject.dashboard.Dashboard;
import org.eclipse.che.selenium.pageobject.dashboard.NewWorkspace;
import org.eclipse.che.selenium.pageobject.dashboard.workspaces.Workspaces;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/** @author Andrey Chizhikov */
public class CreateWorkspaceWithNodeStackTest {
  private final String WORKSPACE = NameGenerator.generate("WsNode", 4);

  @Inject private DefaultTestUser defaultTestUser;
  @Inject private NewWorkspace newWorkspace;
  @Inject private Dashboard dashboard;
  @Inject private ProjectExplorer projectExplorer;
  @Inject private CheTerminal terminal;
  @Inject private SeleniumWebDriverHelper seleniumWebDriverHelper;
  @Inject private TestWorkspaceServiceClient workspaceServiceClient;
  @Inject private Workspaces workspaces;
  @Inject private CreateWorkspaceHelper createWorkspaceHelper;

  // it is used to read workspace logs on test failure
  private TestWorkspace testWorkspace;

  @BeforeClass
  public void setUp() {
    dashboard.open();
  }

  @AfterClass
  public void tearDown() throws Exception {
    workspaceServiceClient.delete(WORKSPACE, defaultTestUser.getName());
  }

  @Test
  public void createWorkspaceWithNodeStackTest() {
    testWorkspace = createWorkspaceHelper.createWorkspaceFromStack(NODE, WORKSPACE, 2.0);

    dashboard.waitNotificationIsClosed();
    seleniumWebDriverHelper.switchToIdeFrameAndWaitAvailability();

    projectExplorer.waitProjectExplorer();
    terminal.waitFirstTerminalTab(60);
  }
}
