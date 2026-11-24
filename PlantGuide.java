package PlantGuide;
import java.util.Scanner;
import java.util.ArrayList;
public class PlantGuide {
public static void main(String[] args) {
Scanner input = new Scanner(System.in);
Database db = new Database();
User normalUser = new User(1, "Normal User", "user@mail.com", "1234");
Admin admin = new Admin(99, "Admin", "admin@mail.com", "admin");
db.userList.add(normalUser);
db.userList.add(admin);
int nextPlantId = 1; 
db.addPlant(new Plant(
nextPlantId++, 
"Cactus", 
"Cactaceae", 
"Avoid overwatering, use well-draining soil", 
"Patience and Endurance"
));
db.addPlant(new Plant(
nextPlantId++, 
"Aloe Vera", 
"Aloe barbadensis miller", 
"Do not water too much, ensure good water drainage", 
"Healing and Protection"
));
db.addPlant(new Plant(
nextPlantId++, 
"Peace Lily", 
"Spathiphyllum", 
"Ensure the soil is moist, leaves can be cleaned with a damp cloth", 
"Peace and Purity"
));
db.addPlant(new Plant(
nextPlantId++, 
"Gardenia", 
"Gardenia jasminoides", 
"Requires high humidity and acidic soil", 
"Love and Purity"
));
db.addPlant(new Plant(
nextPlantId++, 
"Rose", 
"Rosa", 
"Continuously remove wilted leaves; periodically fertilize the soil; provide good ventilation to avoid diseases", 
"Love, Beauty, and Romance"
));
System.out.println("====================================");
System.out.println(" PLANT CARE SYSTEM ");
System.out.println("====================================");
User loggedUser = null;
boolean isAdmin = false;
while (loggedUser == null) {
System.out.println("*** USER LOGIN ***");
System.out.print("Enter email: ");
String loginEmail = input.nextLine();
System.out.print("Enter password: ");
String loginPassword = input.nextLine();
for (User u : db.userList) {
if (u.login(loginEmail, loginPassword)) { 
loggedUser = u;
break;
}
}
if (loggedUser == null) {
System.out.println("Login failed. Invalid credentials. Try again.");
} else {
isAdmin = loggedUser instanceof Admin;
System.out.println("Login successful as " + (isAdmin ? "**Admin**." : "**User**.") + "\n");

}
}
int choice = -1;
boolean exitProgram = false;
while (!exitProgram) {
System.out.println("\n====================================");
System.out.println(" MAIN MENU ");
System.out.println("====================================");
System.out.println("1. Add new plant (Admin only)");
System.out.println("2. View all plants");
System.out.println("3. Search plant by name");
System.out.println("4. Submit a care tip");
System.out.println("5. Submit a correction");
if (isAdmin) {
System.out.println("6. Approve pending tips");
System.out.println("7. Approve corrections");
System.out.println("8. Edit plant information");
System.out.println("9. Remove a plant");
}
System.out.println("10. Switch User (Logout)"); 
System.out.println("0. Exit Program");
System.out.print("Choose: ");
if (input.hasNextInt()) {
choice = input.nextInt();
input.nextLine();
} 
else {
input.nextLine();
choice = -1;
System.out.println("Invalid input. Please enter a number.");
continue;
}
switch (choice) {
case 1: 
if (!isAdmin) {
System.out.println("Access denied. Only Admins can add new plants.");
break;
}
System.out.print("Enter plant name: ");
String name = input.nextLine();
System.out.print("Enter species: ");
String species = input.nextLine();
System.out.print("Enter care instructions: ");
String care = input.nextLine();
System.out.print("Enter symbolic meaning: ");
String meaning = input.nextLine();
Plant plant = new Plant(
db.plantList.size() + 1, 
name, species, care, meaning
);
if (plant.validateData()) { 
if (((Admin) loggedUser).updateDatabase(plant, db)) { 
System.out.println("Plant added successfully by Admin!");
} else {
System.out.println("Failed to add plant to database.");
}
} else {
System.out.println("Error: Plant data is invalid (Name/Species cannot be null).");
}
break;
case 2:
if (db.plantList.isEmpty()) {
System.out.println("No plants available.");
} else {
System.out.println("\nAll Plants:");
for (Plant p : db.plantList) {
System.out.println("• " + p.getPlantInfo());
}
}
break;
case 3:
System.out.print("Enter plant name to search: ");
String searchTerm = input.nextLine();

ArrayList<Plant> found = loggedUser.searchPlant(searchTerm, db); 
if (found.isEmpty()) {
System.out.println("No plants match your search.");
} else {
System.out.println("Results:");
for (Plant p : found) System.out.println("• " + p.getPlantInfo());
}
break;
case 4: 
System.out.print("Enter plant ID for the tip: ");
int tipPid = input.nextInt();
input.nextLine();
System.out.print("Enter your tip content: ");
String tipText = input.nextLine();
Tip tip = new Tip(
db.tipList.size() + 1, 
loggedUser.userID, 
tipPid, 
tipText
);
db.tipList.add(loggedUser.shareTip(tip)); 
System.out.println("Tip submitted (pending admin approval).");
break;
case 5: 
System.out.print("Enter plant ID to submit correction for: ");
int correctionPid = input.nextInt();
input.nextLine();
System.out.print("Enter correction description: ");
String correctionDesc = input.nextLine();
Correction correction = new Correction(
db.correctionList.size() + 1, 
loggedUser.userID, 
correctionPid, 
correctionDesc
);
if (correction.submitCorrection()) { 
db.correctionList.add(loggedUser.submitCorrection(correction)); 
System.out.println("Correction submitted (pending admin approval).");
} else {
System.out.println("Failed to submit correction.");
}
break;
case 6: 
if (!isAdmin) break;
ArrayList<Tip> pendingTips = new ArrayList<>();
for (Tip t : db.tipList)
if (t.status.equals("pending"))
pendingTips.add(t);
if (pendingTips.isEmpty()) {
System.out.println("No pending tips.");
} else {
System.out.println("\n--- Pending Tips ---");
for (Tip t : pendingTips) {
Plant p = db.getPlantByID(t.plantID);
System.out.println("Tip ID " + t.tipID + " for Plant ID " + t.plantID + (p != null ? " (" + p.name + ")" : "") + 
": " + t.content);
System.out.print("Approve? (yes/no): ");
String ans = input.nextLine();
if (ans.equalsIgnoreCase("yes")) {
((Admin) loggedUser).approveTip(t);
System.out.println("Tip approved.");
} else {
t.editTipStatus("rejected");
System.out.println("Tip rejected.");
}
}
}
break;
case 7: 
if (!isAdmin) break;
if (db.correctionList.isEmpty()) {
System.out.println("No pending corrections.");
break;

} 
System.out.println("\n--- Pending Corrections ---");
Correction c = db.correctionList.get(0); 
Plant plantToCorrect = db.getPlantByID(c.plantID); 
System.out.println("Correction ID " + c.correctionID + " for Plant ID " + c.plantID + (plantToCorrect != null ? " (" +
plantToCorrect.name + ")" : "") + ": " + c.description);
System.out.print("Approve? (yes/no): ");
String ans = input.nextLine();
if (ans.equalsIgnoreCase("yes")) {
((Admin) loggedUser).approveCorrection(c); 
db.correctionList.remove(c); 
System.out.println("Correction approved and processed.");
} else {
db.correctionList.remove(c); 
System.out.println("Correction rejected.");
}
break;
case 8: 
if (!isAdmin) break;
System.out.print("Enter Plant ID to edit: ");
int editPid = input.nextInt();
input.nextLine();
Plant plantToEdit = db.getPlantByID(editPid); 
if (plantToEdit == null) {
System.out.println("Plant not found.");
break;
}
System.out.println("Editing Plant: " + plantToEdit.name + " (" + plantToEdit.species + ")");
System.out.println("Enter new value, or leave empty to keep current.");
System.out.print("New Name (" + plantToEdit.name + "): ");
String newName = input.nextLine();
System.out.print("New Species (" + plantToEdit.species + "): ");
String newSpecies = input.nextLine();
System.out.print("New Care Instructions: ");
String newCare = input.nextLine();
System.out.print("New Symbolic Meaning: ");
String newMeaning = input.nextLine();
Plant updatedPlant = new Plant(
plantToEdit.plantID,
newName.isEmpty() ? plantToEdit.name : newName,
newSpecies.isEmpty() ? plantToEdit.species : newSpecies,
newCare.isEmpty() ? plantToEdit.careInstructions : newCare,
newMeaning.isEmpty() ? plantToEdit.symbolicMeaning : newMeaning
);
plantToEdit.updatePlantInfo(updatedPlant); 
System.out.println("Plant info updated successfully! New info: " + plantToEdit.getPlantInfo());
break;
case 9: 
if (!isAdmin) break;
System.out.print("Enter Plant ID to remove: ");
int removePid = input.nextInt();
input.nextLine();
if (db.removePlant(removePid)) { 
System.out.println("Plant with ID " + removePid + " removed successfully.");
} else {
System.out.println("Failed to remove plant (ID not found).");
}
break;
case 10: 
System.out.println("Logging out...");
loggedUser = null; 
while (loggedUser == null) {
System.out.println("\n====================================");
System.out.println("*** USER LOGIN ***");
System.out.print("Enter email: ");
String newLoginEmail = input.nextLine();
System.out.print("Enter password: ");
String newLoginPassword = input.nextLine();

for (User u : db.userList) {
if (u.login(newLoginEmail, newLoginPassword)) { 
loggedUser = u;
break;
}
}
if (loggedUser == null) {
System.out.println("Login failed. Invalid credentials. Try again.");
} else {
isAdmin = loggedUser instanceof Admin;
System.out.println("Login successful as " + (isAdmin ? "**Admin**." : "**User**.") + "\n");
}
}
break;
case 0:
System.out.println("Goodbye!");
exitProgram = true; 
break;
default:
System.out.println("Invalid choice, try again.");
break;
}
}
}
}
