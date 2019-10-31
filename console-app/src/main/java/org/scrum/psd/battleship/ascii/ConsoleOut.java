package org.scrum.psd.battleship.ascii;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

public class ConsoleOut {
    private static ColoredPrinter console = new ColoredPrinter.Builder(1, false).build();

    public static void meriam() {
        console.print("\033[2J\033[;H");
        console.println("                  __");
        console.println("                 /  \\");
        console.println("           .-.  |    |");
        console.println("   *    _.-'  \\  \\__/");
        console.println("    \\.-'       \\");
        console.println("   /          _/");
        console.println("  |      _  /\" \"");
        console.println("  |     /_\'");
        console.println("   \\    \\_/");
        console.println("    \" \"\" \"\" \"\" \"");
    }

    public static void kapal() {
        console.setForegroundColor(Ansi.FColor.MAGENTA);
        console.println("                                     |__");
        console.println("                                     |\\/");
        console.println("                                     ---");
        console.println("                                     / | [");
        console.println("                              !      | |||");
        console.println("                            _/|     _/|-++'");
        console.println("                        +  +--|    |--|--|_ |-");
        console.println("                     { /|__|  |/\\__|  |--- |||__/");
        console.println("                    +---------------___[}-_===_.'____                 /\\");
        console.println("                ____`-' ||___-{]_| _[}-  |     |_[___\\==--            \\/   _");
        console.println(" __..._____--==/___]_|__|_____________________________[___\\==--____,------' .7");
        console.println("|                        Welcome to Battleship                         BB-61/");
        console.println(" \\_________________________________________________________________________|");
        console.println("");
        console.clear();
    }

    public static void boom(String target) {
        if (target.equalsIgnoreCase("me")) {
            console.setForegroundColor(Ansi.FColor.YELLOW);
        } else {
            console.setForegroundColor(Ansi.FColor.RED);
        }

        console.println("                \\         .  ./");
        console.println("              \\      .:\" \";'.:..\" \"   /");
        console.println("                  (M^^.^~~:.'\" \").");
        console.println("            -   (/  .    . . \\ \\)  -");
        console.println("               ((| :. ~ ^  :. .|))");
        console.println("            -   (\\- |  \\ /  |  /)  -");
        console.println("                 -\\  \\     /  /-");
        console.println("                   \\  \\   /  /");
    }

    public static void beep() {
        console.print("\007");
    }
}
