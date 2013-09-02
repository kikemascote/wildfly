/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat Inc., and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.as.weld;

import org.jboss.as.controller.ReloadRequiredRemoveStepHandler;
import org.jboss.as.controller.ReloadRequiredWriteAttributeHandler;
import org.jboss.as.controller.SimpleAttributeDefinition;
import org.jboss.as.controller.SimpleAttributeDefinitionBuilder;
import org.jboss.as.controller.SimpleResourceDefinition;
import org.jboss.as.controller.registry.ManagementResourceRegistration;
import org.jboss.dmr.ModelNode;
import org.jboss.dmr.ModelType;

/**
 * Resource definition for Weld subsystem
 *
 * @author Jozef Hartinger
 *
 */
public class WeldResourceDefinition extends SimpleResourceDefinition {

    public static final WeldResourceDefinition INSTANCE = new WeldResourceDefinition();

    public static final String REQUIRE_BEAN_DESCRIPTOR_ATTRIBUTE_NAME = "require-bean-descriptor";
    public static final String NON_PORTABLE_MODE_ATTRIBUTE_NAME = "non-portable-mode";

    public static final SimpleAttributeDefinition REQUIRE_BEAN_DESCRIPTOR_ATTRIBUTE =
            new SimpleAttributeDefinitionBuilder(REQUIRE_BEAN_DESCRIPTOR_ATTRIBUTE_NAME, ModelType.BOOLEAN, true)
            .setAllowExpression(true)
            .setDefaultValue(new ModelNode(false))
            .build();

    public static final SimpleAttributeDefinition NON_PORTABLE_MODE_ATTRIBUTE =
            new SimpleAttributeDefinitionBuilder(NON_PORTABLE_MODE_ATTRIBUTE_NAME, ModelType.BOOLEAN, true)
            .setAllowExpression(true)
            .setDefaultValue(new ModelNode(false))
            .build();

    private WeldResourceDefinition() {
        super(
                WeldExtension.PATH_SUBSYSTEM,
                WeldExtension.getResourceDescriptionResolver(),
                WeldSubsystemAdd.INSTANCE,
                ReloadRequiredRemoveStepHandler.INSTANCE);
    }

    @Override
    public void registerAttributes(ManagementResourceRegistration resourceRegistration) {
        super.registerAttributes(resourceRegistration);
        resourceRegistration.registerReadWriteAttribute(REQUIRE_BEAN_DESCRIPTOR_ATTRIBUTE, null, new ReloadRequiredWriteAttributeHandler(REQUIRE_BEAN_DESCRIPTOR_ATTRIBUTE));
        resourceRegistration.registerReadWriteAttribute(NON_PORTABLE_MODE_ATTRIBUTE, null, new ReloadRequiredWriteAttributeHandler(NON_PORTABLE_MODE_ATTRIBUTE));
    }
}
