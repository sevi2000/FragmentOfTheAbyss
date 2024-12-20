package com.formiko.lwjgl3;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.formiko.fragmentsoftheabyss.Main;

/** Launches the desktop (LWJGL3) application. */
public class Lwjgl3Launcher {
    public static void main(String[] args) {
         // return version
         if (args.length > 0 && args[0].replace("-", "").equalsIgnoreCase("version")) {
            try {
                InputStream is = Lwjgl3Launcher.class.getClassLoader().getResourceAsStream("version.md");
                String version = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)).lines()
                        .collect(Collectors.joining("\n")).strip();
                System.out.println(version);

        if (StartupHelper.startNewJvmIfRequired()) return; // This handles macOS support and helps on Windows.
        createApplication();
    }

    private static Lwjgl3Application createApplication() {
        return new Lwjgl3Application(new Main(), getDefaultConfiguration());
    }

    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("FragmentOfTheAbyss");
        //// Vsync limits the frames per second to what your hardware can display, and
        //// helps eliminate
        //// screen tearing. This setting doesn't always work on Linux, so the line
        //// after is a safeguard.
        configuration.useVsync(true);
        //// Limits FPS to the refresh rate of the currently active monitor, plus 1 to
        //// try to match fractional
        //// refresh rates. The Vsync setting above should limit the actual FPS to match
        //// the monitor.
        configuration.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate + 1);
        //// If you remove the above line and set Vsync to false, you can get unlimited
        //// FPS, which can be
        //// useful for testing performance, but can also be very stressful to some
        //// hardware.
        //// You may also need to configure GPU drivers to fully disable Vsync; this can
        //// cause screen tearing.
        // configuration.setWindowedMode(640, 480);
        // configuration.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
        // configuration.setMaximized(true); // create issues on linux. cf
        //// https://github.com/libgdx/libgdx/issues/7089
        // configuration.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
        // configuration.setWindowedMode(500, 500);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        configuration.setWindowedMode((int) screenSize.getWidth(), (int) screenSize.getHeight());
        //// You can change these files; they are in lwjgl3/src/main/resources/ .
        configuration.setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png");
        return configuration;
    }
}
