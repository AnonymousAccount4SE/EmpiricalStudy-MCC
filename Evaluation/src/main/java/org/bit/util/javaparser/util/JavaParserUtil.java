package org.bit.util.javaparser.util;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.stmt.BlockStmt;
import org.bit.util.javaparser.dao.Method;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JavaParserUtil {
    public static List<String> parser(File file) throws FileNotFoundException {
        List<String> result = new ArrayList<>();

        CompilationUnit compilationUnit = StaticJavaParser.parse(file);
        List<String> packageNameList = new ArrayList<>();
        compilationUnit.findAll(PackageDeclaration.class).forEach(packageDeclaration -> {
            packageNameList.add(packageDeclaration.getName().asString());
        });
        String packageName = packageNameList.get(0);

        ArrayList<Method> totalMethods = new ArrayList<>();

        compilationUnit.findAll(ClassOrInterfaceDeclaration.class).forEach(classOrInterfaceDeclaration -> {
            if (!classOrInterfaceDeclaration.isInterface()) {
                String className = classOrInterfaceDeclaration.getName().asString();
                ArrayList<Method> classMethods = new ArrayList<>();
                classOrInterfaceDeclaration.findAll(MethodDeclaration.class).forEach(methodDeclaration -> {
                    String methodName = methodDeclaration.getName().asString();
                    String returnType = methodDeclaration.getType().asString();
                    ArrayList<String> parameterTypes = new ArrayList<>();
                    ArrayList<String> methodTokens = new ArrayList<>();
                    NodeList<Parameter> parameters = methodDeclaration.getParameters();
                    for (Parameter parameter : parameters) {
                        parameterTypes.add(parameter.getType().asString());
                    }
                    Optional<BlockStmt> body = methodDeclaration.getBody();
                    body.ifPresent(blockStmt -> blockStmt.findAll(SimpleName.class).forEach(simpleName -> {
                        methodTokens.add(simpleName.asString());
                    }));
                    Method myMethod = new Method(methodName, returnType, parameterTypes, methodTokens);
                    classMethods.add(myMethod);
                });
                for (Method classMethod : classMethods) {
                    classMethod.setClassName(className);
                    classMethod.setPackageName(packageName);
                }
                totalMethods.addAll(classMethods);
            }
        });

        for (Method totalMethod : totalMethods) {
            StringBuffer sb = new StringBuffer();

            sb.append(totalMethod.getPackageName()).append(":");
            sb.append(totalMethod.getClassName()).append(":");
            sb.append(totalMethod.getMethodName()).append(":");

            sb.append(totalMethod.getReturnType());
            if (totalMethod.getParameterTypes().size() > 0) {
                sb.append(" ");
                bufferAddList(sb, totalMethod.getParameterTypes());
            }

            sb.append(":");
            bufferAddList(sb, totalMethod.getMethodTokens());
            sb.append("\n");
            result.add(sb.toString());
        }

        return result;

    }
    public static List<String> parser(String file) throws FileNotFoundException {
        List<String> result = new ArrayList<>();

        CompilationUnit compilationUnit = StaticJavaParser.parse(file);
//        List<String> packageNameList = new ArrayList<>();
//        compilationUnit.findAll(PackageDeclaration.class).forEach(packageDeclaration -> {
//            packageNameList.add(packageDeclaration.getName().asString());
//        });
//        String packageName = packageNameList.get(0);

        ArrayList<Method> totalMethods = new ArrayList<>();

        compilationUnit.findAll(ClassOrInterfaceDeclaration.class).forEach(classOrInterfaceDeclaration -> {
            if (!classOrInterfaceDeclaration.isInterface()) {
                String className = classOrInterfaceDeclaration.getName().asString();
                ArrayList<Method> classMethods = new ArrayList<>();
                classOrInterfaceDeclaration.findAll(MethodDeclaration.class).forEach(methodDeclaration -> {
                    int mark = 0;
                    String methodName = methodDeclaration.getName().asString();
                    String returnType = methodDeclaration.getType().asString();
                    ArrayList<String> parameterTypes = new ArrayList<>();
                    ArrayList<String> methodTokens = new ArrayList<>();
//                    NodeList<AnnotationExpr> annotationExprs = methodDeclaration.getAnnotations();
                    NodeList<Parameter> parameters = methodDeclaration.getParameters();
                    for (Parameter parameter : parameters) {
                        parameterTypes.add(parameter.getType().asString());
                    }
                    Optional<BlockStmt> body = methodDeclaration.getBody();
                    body.ifPresent(blockStmt -> blockStmt.findAll(SimpleName.class).forEach(simpleName -> {
                        methodTokens.add(simpleName.asString());
                    }));
                    Method myMethod = new Method(methodName, returnType, parameterTypes, methodTokens);
//                    for(AnnotationExpr ae:annotationExprs){
//                        System.out.println(ae.getName());
//                        if(ae.getName().toString().equals("Override")){
//                            mark++;
//                        }
//                    }
//                    if(mark == 1)
//                        System.out.println("Override!");
//                    else
                        classMethods.add(myMethod);


                });
                for (Method classMethod : classMethods) {
                    classMethod.setClassName(className);
//                    classMethod.setPackageName(packageName);
                }
                if(classMethods.size()>1)
                    totalMethods.addAll(classMethods.subList(0,1));
                else
                    totalMethods.addAll(classMethods);
            }
        });

        for (Method totalMethod : totalMethods) {
            StringBuffer sb = new StringBuffer();

            sb.append(totalMethod.getPackageName()).append(":");
            sb.append(totalMethod.getClassName()).append(":");
            sb.append(totalMethod.getMethodName()).append(":");

            sb.append(totalMethod.getReturnType());
            if (totalMethod.getParameterTypes().size() > 0) {
                sb.append(" ");
                bufferAddList(sb, totalMethod.getParameterTypes());
            }

            sb.append(":");
            bufferAddList(sb, totalMethod.getMethodTokens());
            sb.append("\n");
            result.add(sb.toString());
        }

        return result;

    }

    static void bufferAddList(StringBuffer sb, List<String> strList) {
        for (String s : strList) {
            sb.append(s).append(" ");
        }
        if (strList.size() > 0)
            sb.deleteCharAt(sb.length() - 1);
    }

}
