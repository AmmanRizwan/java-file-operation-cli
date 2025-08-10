public class Main {
    public static void main(String[] args) {
        if (args[0].equals("fo")) {
            switch (args[1]) {
                case "show": Application.ShowFiles(); break;
                case "new": Application.CreateFile(); break;
                case "write": Application.WriteFile(); break;
                case "read": Application.ReadFile(); break;
                case "info": Application.InfoFile(); break;
                case "remove": Application.DeleteFile(); break;
                case "help": Help.getHelp(); break;
                default: System.out.println("'" + args[1] + "' is not a command\n" +
                        "Please check the command into the help\n" +
                        "Type fo help to check all the command");
            }
        } else {
            System.out.println("'" + args[0] + "' is not a command\nAre you need 'fo' for file operation?\nTry fo help to get the commands");
        }

    }
}