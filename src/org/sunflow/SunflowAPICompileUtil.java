/*
 * Copyright (c) 2013 KITec Inc,.. All rights reserved.
 */
package org.sunflow;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;

import org.codehaus.janino.ClassBodyEvaluator;
import org.codehaus.janino.CompileException;
import org.codehaus.janino.Scanner;
import org.codehaus.janino.Parser.ParseException;
import org.codehaus.janino.Scanner.ScanException;
import org.sunflow.system.Timer;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;

class SunflowAPICompileUtil {
    static SunflowAPI compileSunflowAPIFromFile(String filename) {
        try {
            FileInputStream stream = new FileInputStream(filename);
            SunflowAPI api = (SunflowAPI) ClassBodyEvaluator.createFastClassBodyEvaluator(new Scanner(filename, stream), SunflowAPI.class, ClassLoader.getSystemClassLoader());
            stream.close();
            return api;
        } catch (CompileException e) {
            UI.printError(Module.API, "Could not compile: \"%s\"", filename);
            UI.printError(Module.API, "%s", e.getMessage());
            return null;
        } catch (ParseException e) {
            UI.printError(Module.API, "Could not compile: \"%s\"", filename);
            UI.printError(Module.API, "%s", e.getMessage());
            return null;
        } catch (ScanException e) {
            UI.printError(Module.API, "Could not compile: \"%s\"", filename);
            UI.printError(Module.API, "%s", e.getMessage());
            return null;
        } catch (IOException e) {
            UI.printError(Module.API, "Could not compile: \"%s\"", filename);
            UI.printError(Module.API, "%s", e.getMessage());
            return null;
        }
    }

    static SunflowAPI compileSunflowAPI(String code) {
        try {
            Timer t = new Timer();
            t.start();
            SunflowAPI api = (SunflowAPI) ClassBodyEvaluator.createFastClassBodyEvaluator(new Scanner(null, new StringReader(code)), SunflowAPI.class, (ClassLoader) null);
            t.end();
            UI.printInfo(Module.API, "Compile time: %s", t.toString());
            return api;
        } catch (CompileException e) {
            UI.printError(Module.API, "%s", e.getMessage());
            return null;
        } catch (ParseException e) {
            UI.printError(Module.API, "%s", e.getMessage());
            return null;
        } catch (ScanException e) {
            UI.printError(Module.API, "%s", e.getMessage());
            return null;
        } catch (IOException e) {
            UI.printError(Module.API, "%s", e.getMessage());
            return null;
        }
    }
}