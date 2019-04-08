package com.honey.computer;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;


public class ComponentHandlerTest {

    ComponentHandler handler;

    @Before
    public void setup() {
        handler= new ComponentHandler();
    }

    @Test
    public void testHandleDepend() throws Exception {

        String name = "AA";
        String[] dependentComponents = new String[2];
        dependentComponents[0] = "BB";
        dependentComponents[1] = "CC";

        handler.handleDepend(name, dependentComponents);
        assertEquals(handler.dependencyChart.size(), 3);
    }

    @Test
    public void testHandleInstall() throws Exception {

        String name = "AA";
        String[] dependentComponents = new String[2];
        dependentComponents[0] = "BB";
        dependentComponents[1] = "CC";

        handler.handleDepend(name, dependentComponents);
        handler.handleInstall(name);
        Component component  = handler.dependencyChart.get(name);
        Component dependency = handler.dependencyChart.get(dependentComponents[0]);
        assertEquals(component.isInstalled(), true);
        assertEquals(dependency.isInstalled(), true);
    }

    @Test
    public void testHandleList() throws Exception {

        String[] components = {"AA", "BB"};

        handler.handleInstall(components[0]);
        handler.handleInstall(components[1]);
        handler.handleList();

        Set<String> installedComponents = handler.installedComponents;
        assertEquals(installedComponents.size(), 2);
    }

    @Test
    public void testHandleRemove() throws Exception {

        String[] components = {"AA", "BB"};

        handler.handleInstall(components[0]);
        handler.handleInstall(components[1]);
        Set<String> installedComponents = handler.installedComponents;
        assertEquals(installedComponents.size(), 2);
        handler.handleRemove(components[0]);
        assertEquals(installedComponents.size(), 1);
    }
}
