package org.bit.util.javaparser.dao;

import java.util.ArrayList;

public class Method {
    private String packageName;
    private String className;
    private String methodName;
    private String returnType;
    private ArrayList<String> parameterTypes;
    private ArrayList<String> methodTokens;

    public Method(String methodName, String returnType, ArrayList<String> parameterTypes, ArrayList<String> methodTokens) {
        this.methodName = methodName;
        this.returnType = returnType;
        this.parameterTypes = parameterTypes;
        this.methodTokens = methodTokens;
    }

    public Method(String packageName, String className, String methodName, String returnType, ArrayList<String> parameterTypes, ArrayList<String> methodTokens) {
        this.packageName = packageName;
        this.className = className;
        this.methodName = methodName;
        this.returnType = returnType;
        this.parameterTypes = parameterTypes;
        this.methodTokens = methodTokens;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public ArrayList<String> getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(ArrayList<String> parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public ArrayList<String> getMethodTokens() {
        return methodTokens;
    }

    public void setMethodTokens(ArrayList<String> methodTokens) {
        this.methodTokens = methodTokens;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "dao.Method{" +
                "packageName='" + packageName + '\'' +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", returnType='" + returnType + '\'' +
                ", parameterTypes=" + parameterTypes +
                ", methodTokens=" + methodTokens +
                '}';
    }
}
