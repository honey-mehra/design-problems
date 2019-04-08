package com.honey.computer;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * This class corresponds to the Computer Component that would be added to the system.
 *
 * a. The component name and whether it has been installed or not.
 * b. The component will consists of its dependencies.
 * c. It will also contain a set of string specifying what all other components depend
 *    on this component: they are known as 'dependents' of this component.
 */
public class Component {


    private String name;

    private boolean installed;

    private Set<String> dependencies;

    private Set<String> dependents;

    public Component(String name) {
        this.name = name;
        this.dependencies = new LinkedHashSet<>();
        this.dependents = new LinkedHashSet<>();
    }

    public Component(String name, boolean installed) {
        this.name = name;
        this.installed = installed;
        this.dependencies = new LinkedHashSet<>();
        this.dependents = new LinkedHashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isInstalled() {
        return installed;
    }

    public void setInstalled(boolean installed) {
        this.installed = installed;
    }

    public Set<String> getDependencies() {
        return dependencies;
    }

    public void setDependencies(Set<String> dependencies) {
        this.dependencies = dependencies;
    }

    public Set<String> getDependents() {
        return dependents;
    }

    public void setDependents(Set<String> dependents) {
        this.dependents = dependents;
    }

    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || !(object instanceof Component))
            return false;

        Component component = (Component) object;

        if (this.name != null ? !this.name.equals(component.name) : component.name != null)
            return false;

        return true;
    }

    public int hashCode() {
        return this.name != null ? this.name.hashCode() : 0;
    }

    public String toString() {
        return this.name + " installed=" + installed + " dependencies = " + this.dependencies + " dependents on me: "
                + this.dependents;
    }

    /**
     * This method adds the name of the component passed to the 'dependencies' Set. The dependencies
     * collection consists of what all components 'this' component depends upon.
     *
     * @param name
     */
    public void addDependency(String name) {
        dependencies.add(name);
    }

    /**
     * This method adds the name of the component passed to the 'dependents' Set. The dependents
     * collection consists of what all other components depend on 'this' component.
     *
     * @param name
     */
    public void addDependent(String name) {
        dependents.add(name);
    }
}
