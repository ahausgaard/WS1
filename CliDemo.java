// File: CliDemo.java
// Purpose: Simple CLI program for workshop exercises.
// Features:
//  - No arguments: show a default message
//  - --help / -h / -? : show help text
//  - greet <name> : print a greeting
//  - sum <numbers...> : sum integers or decimals
//  - echo <text...> : print the provided text

public class CliDemo {

    public static void main(String[] args) {
        if (args.length == 0) {
            printDefaultMessage();
            return;
        }

        String first = args[0].toLowerCase();

        // Help flags accepted as the first argument
        if (first.equals("--help") || first.equals("-h") || first.equals("-?")) {
            printHelp();
            return;
        }

        if (first.equals("--version")) {
            System.out.println("CliDemo 1.0");
            return;
        }

        switch (first) {
            case "greet":
                doGreet(args);
                break;
            case "sum":
                doSum(args);
                break;
            case "echo":
                doEcho(args);
                break;
            default:
                System.out.println("Unknown command: " + args[0]);
                System.out.println("Tip: Run with --help to see available commands.");
                // System.exit(2); // (optional) return non-zero exit code for scripting
        }
    }

    private static void printDefaultMessage() {
        System.out.println("Hello from the CLI!");
        System.out.println("You ran the program without any arguments.");
        System.out.println("Try for example:");
        System.out.println("  java CliDemo --help");
        System.out.println("  java CliDemo greet Alice");
        System.out.println("  java CliDemo sum 3 4.5 10");
        System.out.println("  java CliDemo echo Hello there");
        System.out.println();
        System.out.println("Working directory: " + System.getProperty("user.dir"));
        System.out.println("Java version: " + System.getProperty("java.version"));
        System.out.println("Operating system: " + System.getProperty("os.name"));
    }

    private static void printHelp() {
        System.out.println("CliDemo - a simple command-line utility");
        System.out.println();
        System.out.println("Usage:");
        System.out.println("  java CliDemo [command] [arguments]");
        System.out.println();
        System.out.println("Commands:");
        System.out.println("  greet <name>         Prints a greeting to <name>");
        System.out.println("  sum <numbers...>     Sums numbers (integers/decimals)");
        System.out.println("  echo <text...>       Prints the given text as-is");
        System.out.println();
        System.out.println("Flags:");
        System.out.println("  --help, -h, -?       Show this help");
        System.out.println("  --version            Show version");
        System.out.println();
        System.out.println("Examples:");
        System.out.println("  java CliDemo");
        System.out.println("  java CliDemo --help");
        System.out.println("  java CliDemo greet Alice");
        System.out.println("  java CliDemo sum 3 4.5 10");
        System.out.println("  java CliDemo echo \"Hello world\"");
    }

    private static void doGreet(String[] args) {
        if (args.length < 2) {
            System.out.println("Error: 'greet' requires a name.");
            System.out.println("Usage: java CliDemo greet <name>");
            return;
        }
        String name = args[1];
        System.out.println("Hello, " + name + "!");
    }

    private static void doSum(String[] args) {
        if (args.length < 2) {
            System.out.println("Error: 'sum' requires at least one number.");
            System.out.println("Usage: java CliDemo sum <numbers...>");
            return;
        }
        double total = 0.0;
        for (int i = 1; i < args.length; i++) {
            try {
                // Accept both dot and comma as decimal separators
                total += Double.parseDouble(args[i].replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Error: '" + args[i] + "' is not a valid number.");
                System.out.println("Usage: java CliDemo sum <numbers...>");
                return;
            }
        }
        // Print as integer if no fractional part
        if (Math.abs(total - Math.round(total)) < 1e-9) {
            System.out.println((long) Math.round(total));
        } else {
            System.out.println(total);
        }
    }

    private static void doEcho(String[] args) {
        if (args.length < 2) {
            System.out.println("Error: 'echo' requires some text.");
            System.out.println("Usage: java CliDemo echo <text...>");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            if (i > 1) sb.append(" ");
            sb.append(args[i]);
        }
        System.out.println(sb.toString());
    }
}