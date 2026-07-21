// A1 — Abstract Factory Pattern (UI Theme Library)


/*

// ---- Abstract Products ----
interface Button { void render(); }
interface TextField { void render(); }
interface Dialog { void render(); }

// ---- Light Family ----
class LightButton implements Button {
    public void render() { System.out.println("Rendering Light Button"); }
}
class LightTextField implements TextField {
    public void render() { System.out.println("Rendering Light TextField"); }
}
class LightDialog implements Dialog {
    public void render() { System.out.println("Rendering Light Dialog"); }
}

// ---- Dark Family ----
class DarkButton implements Button {
    public void render() { System.out.println("Rendering Dark Button"); }
}
class DarkTextField implements TextField {
    public void render() { System.out.println("Rendering Dark TextField"); }
}
class DarkDialog implements Dialog {
    public void render() { System.out.println("Rendering Dark Dialog"); }
}

// ---- Abstract Factory ----
interface UIThemeFactory {
    Button createButton();
    TextField createTextField();
    Dialog createDialog();
}

// ---- Concrete Factories ----
class LightThemeFactory implements UIThemeFactory {
    public Button createButton()       { return new LightButton(); }
    public TextField createTextField() { return new LightTextField(); }
    public Dialog createDialog()       { return new LightDialog(); }
}

class DarkThemeFactory implements UIThemeFactory {
    public Button createButton()       { return new DarkButton(); }
    public TextField createTextField() { return new DarkTextField(); }
    public Dialog createDialog()       { return new DarkDialog(); }
}

// ---- Client ----
class Application {
    private Button button;
    private TextField textField;
    private Dialog dialog;

    // Client selects a theme and receives the matching family
    public void setTheme(UIThemeFactory factory) {
        this.button = factory.createButton();
        this.textField = factory.createTextField();
        this.dialog = factory.createDialog();
        System.out.println("Theme switched.");
    }

    public void renderUI() {
        button.render();
        textField.render();
        dialog.render();
    }
}

// ---- Demonstration ----
public class Main {
    public static void main(String[] args) {
        Application app = new Application();

        app.setTheme(new LightThemeFactory());   // pick Light family
        app.renderUI();                          // Light Button, Light TextField, Light Dialog

        app.setTheme(new DarkThemeFactory());    // switch to Dark, main logic unchanged
        app.renderUI();                          // Dark Button, Dark TextField, Dark Dialog
    }
}

*/