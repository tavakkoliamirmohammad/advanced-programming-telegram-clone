package UserViewPages;

import UserManagement.InvalidUserAuthenticationException;
import UserManagement.ProfileImage;
import UserManagement.User;

import java.util.*;

public class UserEntrancePage {

    private static List<String> choices = new ArrayList<>(Arrays.asList("login", "sign up", "exit"));
    private static Scanner scanner = new Scanner(System.in);

    private void showMenu() {
        for (int i = 0; i < choices.size(); ++i) {
            System.out.println((i + 1) + ". " + choices.get(i));
        }
    }

    public void run(){
        System.out.println("Welcome to telegram");
        while (true){
            showMenu();
            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if(choice == 3){
                return;
            }
            if(choice == 1){
                login();
            }
            else if(choice == 2){
                signUp();
            }
        }
    }
    private void login() {
        System.out.println("Enter the username");
        String username = scanner.nextLine();
        System.out.println("Enter the password");
        String password = scanner.nextLine();

        try {
            User user = User.login(username, password);
            UserMainPage userMainPage = new UserMainPage(user);
            userMainPage.run();
            System.out.println("You have logged in successfully");
        } catch (InvalidUserAuthenticationException e) {
            System.out.println(e.getMessage());
        }
    }

    private void signUp(){
        System.out.println("Enter the username");
        String username = scanner.nextLine();
        System.out.println("Enter the password");
        String password = scanner.nextLine();
        System.out.println("Enter your name");
        String name = scanner.nextLine();
        System.out.println("Enter the id");
        String id = scanner.nextLine();
        System.out.println("Enter the profile Image url");
        String profileImageUrl = scanner.nextLine();
        ProfileImage profileImage = new ProfileImage(profileImageUrl);
        profileImage.setSubmitDate(Calendar.getInstance());
        try {
            User.signUp(username, password,name, id, profileImage);
            System.out.println("You have successfully signed up");
        } catch (InvalidUserAuthenticationException e) {
            System.out.println(e.getMessage());
        }
    }


}
