package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        MethodSignature methodSignature = new MethodSignature();
        String[] signatureWithoutArgSplit = signatureString.substring(0, signatureString.indexOf('(')).split(" ");
        String[] argSplit = signatureString.substring(signatureString.indexOf('(') + 1, signatureString.indexOf(')')).split(", ");

        String accessModifier;
        String returnType;
        String methodName;

        if (signatureWithoutArgSplit.length == 3) {
            accessModifier = signatureWithoutArgSplit[0];
            returnType = signatureWithoutArgSplit[1];
            methodName = signatureWithoutArgSplit[2];
            methodSignature.setAccessModifier(accessModifier);
        } else {
            returnType = signatureWithoutArgSplit[0];
            methodName = signatureWithoutArgSplit[1];
        }
        methodSignature.setReturnType(returnType);
        methodSignature.setMethodName(methodName);

        List<MethodSignature.Argument> listOfArguments = new ArrayList<>();
        for (String arg : argSplit) {
            if (!arg.equals("")) {
                String argType = arg.split(" ")[0];
                String argName = arg.split(" ")[1];
                listOfArguments.add(new MethodSignature.Argument(argType, argName));
            }
        }
        methodSignature.setArguments(listOfArguments);

        return methodSignature;
    }
}
