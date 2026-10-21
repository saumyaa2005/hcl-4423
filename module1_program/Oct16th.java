public class Oct16th {
    // 1. Static variable
    private static Oct16th obj;
    //or
    // static final Oct16th obj= new Oct16th();

    // 2. Private constructor
    private Oct16th() {
    }

    // 3. Static method
    public static Oct16th getInstance() {
        if (obj == null) {
            obj = new Oct16th();
        }
        return obj;
    }

    public static void main(String[] args) {
        Oct16th a = Oct16th.getInstance();
        Oct16th b = Oct16th.getInstance();

        System.out.println(a == b);
    }
}
