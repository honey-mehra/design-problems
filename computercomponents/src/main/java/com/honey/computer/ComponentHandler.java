package com.honey.computer;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class ComponentHandler {

    /**
     * This set contains the already installed components.
     */
    Set<String> installedComponents = new LinkedHashSet<>();

    /**
     * Dependency chart defines the relationship between components.
     * If the components have dependencies or are dependents on other.
     */
    Map<String, Component> dependencyChart = new LinkedHashMap<>();

    /**
     * Print all current installed components in the order of installation.
     */
    public void handleList() {
        installedComponents.stream().forEach(System.out::println);
    }

    /**
     *
     * @param name of the component
     * @param dependentComponents Components to be marked as dependencies for the given component
     */
    public void handleDepend(String name, String[] dependentComponents) {


        if(dependencyChart.get(name) == null) {
            Component component = new Component(name);

            for(String dependency : dependentComponents) {
                if(dependencyChart.get(dependency) == null) {
                    fillDependencies(name, component, dependency);
                }
                else {
                    Component tempComponent = dependencyChart.get(dependency);
                    Set<String> dependencies = tempComponent.getDependencies();
                    if(dependencies.contains(name)) {
                        System.out.println(dependency +" depends on "+name+ ", ignoring command");
                        return;
                    }
                    component.addDependency(dependency);
                    tempComponent.addDependent(name);
                }
            }
            dependencyChart.put(name, component);
        }
        else {
            Component component = dependencyChart.get(name);

            for(String dependency : dependentComponents) {
                if(dependencyChart.get(dependency) == null) {
                    fillDependencies(name, component, dependency);
                }
                else {
                    Component tempComponent = dependencyChart.get(dependency);
                    Set<String> dependencies = tempComponent.getDependencies();
                    if(dependencies.contains(name)) {
                        System.out.println(dependency +" depends on "+name+ ", ignoring command");
                        return;
                    }
                    component.addDependency(dependency);
                    tempComponent.addDependent(name);
                }
            }
        }
    }

    private void fillDependencies(String name, Component component, String dependency) {
        Component tempComponent = new Component(dependency);
        tempComponent.addDependent(name);
        component.addDependency(dependency);
        dependencyChart.put(dependency, tempComponent);
    }

    /**
     *
     * @param name Install the respective component.
     * This method also recursively install the dependencies of the passed component.
     */
    public void handleInstall(String name) {

        Component component = dependencyChart.get(name);
        if(component == null) {
            installedComponents.add(name);
            System.out.println("Installing "+name);
            Component comp = new Component(name, true);
            dependencyChart.put(name, comp);
            return;
        }

        if(!component.isInstalled()) {
            for(String dependency : component.getDependencies()) {
                handleInstall(dependency);
            }
            installedComponents.add(name);
            System.out.println("Installing "+name);
            component.setInstalled(true);
        }
    }

    /**
     *
     * @param name THe component to be removed
     * If the component cannot be removed then this method returns false.
     * Else it removed the components and corresponding dependencies if they are not dependents of other components.
     * @return whether removed or not
     */
    public boolean handleRemove(String name) {

        Component component = dependencyChart.get(name);

        Set<String> dependents = component.getDependents();
        if(dependents.size()==0) {
            if(!component.isInstalled()) {
                return true;
            }
            System.out.println("Removing "+name);
            installedComponents.remove(name);
            component.setInstalled(false);
            for(String depedency : component.getDependencies()) {
                Component dependency = dependencyChart.get(depedency);
                dependency.getDependents().remove(name);
                handleRemove(depedency);
            }
            return true;
        }
        return false;
    }
}
