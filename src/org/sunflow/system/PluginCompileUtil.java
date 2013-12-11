/*
 * Copyright (c) 2013 KITec Inc,.. All rights reserved.
 */
package org.sunflow.system;

import org.codehaus.janino.ClassBodyEvaluator;
import org.codehaus.janino.CompileException;
import org.codehaus.janino.Parser.ParseException;
import org.codehaus.janino.Scanner.ScanException;
import org.sunflow.system.UI.Module;

public class PluginCompileUtil {

    @SuppressWarnings("unchecked")
    public static <T> Class<T> registerPlugin(String name, String sourceCode, Class<T> baseClass) {
        try {
            ClassBodyEvaluator cbe = new ClassBodyEvaluator();
            cbe.setClassName(name);
            if (baseClass.isInterface())
                cbe.setImplementedTypes(new Class[] { baseClass });
            else
                cbe.setExtendedType(baseClass);
            cbe.cook(sourceCode);
            return cbe.getClazz();
        } catch (CompileException e) {
            UI.printError(Module.API, "Plugin \"%s\" could not be declared - %s", name, e.getLocalizedMessage());
            return null;
        } catch (ParseException e) {
            UI.printError(Module.API, "Plugin \"%s\" could not be declared - %s", name, e.getLocalizedMessage());
            return null;
        } catch (ScanException e) {
            UI.printError(Module.API, "Plugin \"%s\" could not be declared - %s", name, e.getLocalizedMessage());
            return null;
        }
    }
}
