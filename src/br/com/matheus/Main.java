package br.com.matheus;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        //Set possible commands.
        Set<String> acceptedCommands = new HashSet<>();
        acceptedCommands.add("-ip");
        acceptedCommands.add("-dp");
        acceptedCommands.add("-h");
        acceptedCommands.add("-w");
        acceptedCommands.add("-print");

        HashMap<String, List<String>> parameters = new HashMap<>();
        List<String> options = null;

        for(int k = 0; k < args.length; k++) {
            final String command = args[k];
            if (command.charAt(0) == '-') {
                if (!acceptedCommands.contains(command)) {
                    System.err.printf("Command %s is not valid! \n", command);
                }
                options = new ArrayList<>();
                parameters.put(command, options);
            } else if (options != null) {
                options.add(command);
            }
        }

        String imagePath = null;
        try {
            imagePath = parameters.get("-ip").get(0);
        } catch (Exception e) {
            System.err.print("Command -ip is mandatory \n");
        }

        String destinyPath = parameters.get("-dp") == null ? null : parameters.get("-dp").get(0);

        if (parameters.get("-w") == null && parameters.get("-h") == null) {
            AsciiConverter asciiConverter = new AsciiConverter();
            asciiConverter.generateAscii(imagePath, destinyPath, false);
        } else {
            int width = Integer.parseInt(parameters.get("-w").get(0));
            int height = Integer.parseInt(parameters.get("-h").get(0));
            AsciiConverter asciiConverter = new AsciiConverter(height, width);
            asciiConverter.generateAscii(imagePath, destinyPath, true);
        }

    }
}