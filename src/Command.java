package EIEV3.src;

import java.util.Scanner;
import java.lang.IllegalArgumentException;
import java.util.InputMismatchException;

public class Command {

  public static void Start(DrawFrame f) {
    String acString = "CREATE MOVE EXPAND DELETE\nDELETEALL INTERSEC DISPLAYBOARD EXIT\n";

    Action action = Action.NONE;

    while (action != Action.EXIT) {
      System.out.printf(acString);
      System.out.printf(">> ");
      Scanner scanner = new Scanner(System.in);
      String ac = scanner.nextLine();
      try {
        action = Action.valueOf(ac);
      } catch (IllegalArgumentException exception) {
        System.out.println("Commnad not found");
        continue;
      }

      switch (action) {
        case CREATE:
          int x, y, width, height;
          String bc;
          System.out.println("Please Input x y width height");
          try {
            x = scanner.nextInt();
            y = scanner.nextInt();
            width = scanner.nextInt();
            height = scanner.nextInt();
            System.out.println("Please Input Color RED/BLUE/YELLOW/GRAY");
            bc = scanner.next();
          } catch (InputMismatchException exception) {
            System.out.println("Please try again");
            continue;
          }
          Colors c;
          try {
            c = Colors.valueOf(bc);
          } catch (IllegalArgumentException exception) {
            System.out.println("Please try again");
            continue;
          }
          Rectangle rectangle = new Rectangle(x, y, width, height, new Color(c));
          if (Board.isItOverTheBoard(rectangle)) {
            System.out.println("It's out of range.");
            break;
          } else if (Board.hasSameRectangles(rectangle)) {
            System.out.println("There's the same rectangle.");
            break;
          }
          if (Board.rectangles.size() <= 10) {
            Board.addRectangle(rectangle);
          } else {
            System.out.println("A lot.");
          }
          System.out.println("SUCCSESS");
          break;
        case MOVE:
          if (Board.getRectanglSize() == 0) {
            System.out.println("No Rectangle");
            break;
          }
          Board.showRectangles();
          System.out.println("Please Input Num");
          int n, x0, y0;
          try {

            n = scanner.nextInt();
            System.out.println("Please Input x y");
            x0 = scanner.nextInt();
            y0 = scanner.nextInt();
          } catch (InputMismatchException exception) {
            System.out.println("Please try again");
            continue;
          }
          Rectangle tmpr = Board.getRectangl(n);
          Rectangle rec = new Rectangle(tmpr.x + x0, tmpr.y + y0, tmpr.width, tmpr.height, new Color(tmpr.color.color));
          if (Board.isItOverTheBoard(rec)) {
            System.out.println("It's out of range.");
            break;
          } else if (Board.hasSameRectangles(rec)) {
            System.out.println("There's the same rectangle.");
            break;
          }
          if (Board.rectangles.size() <= 10) {
            Board.deleteRectangle(n);
            Board.addRectangle(rec);
          } else {
            System.out.println("A lot.");
            break;
          }
          System.out.println("SUCCSESS");
          break;
        case EXPAND:
          if (Board.getRectanglSize() == 0) {
            System.out.println("No Rectangle");
            break;
          }
          Board.showRectangles();
          System.out.println("Please Input Num");
          int nu;
          float mx, my;
          try {
            nu = scanner.nextInt();
            System.out.println("Please Input x y");
            mx = scanner.nextFloat();
            my = scanner.nextFloat();
          } catch (InputMismatchException exception) {
            System.out.println("Please try again");
            continue;
          }
          Rectangle tr = Board.getRectangl(nu);
          Rectangle re = new Rectangle(tr.x, tr.y, Math.round(tr.width * mx), Math.round(tr.height * my),
              new Color(tr.color.color));
          if (Board.isItOverTheBoard(re)) {
            System.out.println("It's out of range.");
            break;
          } else if (Board.hasSameRectangles(re)) {
            System.out.println("There's the same rectangle.");
            break;
          }
          if (Board.rectangles.size() <= 10) {
            Board.deleteRectangle(nu);
            Board.addRectangle(re);
          } else {
            System.out.println("A lot.");
          }
          System.out.println("SUCCSESS");
          break;
        case DELETE:
          if (Board.getRectanglSize() == 0) {
            System.out.println("No Rectangle");
            break;
          }
          Board.showRectangles();
          System.out.println("Please Input Num");
          int num;
          try {
            num = scanner.nextInt();
          } catch (InputMismatchException exception) {
            System.out.println("Please try again");
            continue;
          }
          Board.deleteRectangle(num);
          System.out.println("SUCCSESS");
          break;
        case DELETEALL:
          if (Board.getRectanglSize() == 0) {
            System.out.println("No Rectangle");
            break;
          }
          Board.deleteAllRectangle();
          break;
        case INTERSEC:
          if (Board.getRectanglSize() < 2) {
            System.out.println("No Rectangle");
            break;
          }
          Board.showRectangles();
          System.out.println("Please Input Num");
          int rec1, rec2;
          try {
            rec1 = scanner.nextInt();
            rec2 = scanner.nextInt();
          } catch (InputMismatchException exception) {
            System.out.println("Please try again");
            continue;
          }
          Utility.intersec(rec1, rec2);
          break;
        case DISPLAYBOARD:
          if (Board.getRectanglSize() == 0) {
            System.out.println("No Rectangle");
            break;
          }
          Board.showRectangles();
          break;
        case EXIT:
          System.out.println("exit ..");
          scanner.close();
          break;
        default:
          System.out.println("Command not found");
          break;
      }
      f.repaint();
    }
  }

}
