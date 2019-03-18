package page;

public class BasePage {

    private static String resetPasswordLink;

    public static String getResetPasswordLink() {
        return BasePage.resetPasswordLink;
    }

    public static void setResetPasswordLink(String resetPasswordLink) {
        BasePage.resetPasswordLink = resetPasswordLink;
    }
}
