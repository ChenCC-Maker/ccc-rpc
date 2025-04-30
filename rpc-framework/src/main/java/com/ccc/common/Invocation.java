package com.ccc.common;

import java.io.Serializable;

public class Invocation implements Serializable {
    private String interFaceName;
    private String methodName;
    private Class[] parameterTypes;
    private Object[] parameterValues;

    public Invocation(String interFaceName, String methodName, Class[] parameterTypes, Object[] parameterValues){
        this.interFaceName = interFaceName;
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.parameterValues = parameterValues;
    }


    public String getInterFaceName() {
        return interFaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public Class[] getParameterTypes() {
        return parameterTypes;
    }

    public Object[] getParameterValues() {
        return parameterValues;
    }

    public void setInterFaceName(String interFaceName) {
        this.interFaceName = interFaceName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setParameterTypes(Class[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public void setParameterValues(Object[] parameterValues) {
        this.parameterValues = parameterValues;
    }
}
