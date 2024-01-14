import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
public class PhoneBook {

    private Map<String, String> contacts;
    private static final String ENCRYPTION_ALGORITHM = "AES";
    private boolean firstLoad = true;


    public PhoneBook() {
        contacts = new HashMap<>();
        loadContacts();
    }

    public void addContact(String phone, String name) {
        if (isValidPhone(phone) && isValidName(name)) {
            if (contacts.containsKey(phone)) {
                contacts.put(phone, name);
            } else {
                contacts.put(phone, name);
                System.out.println("New contact added.");
                saveContacts(); // Save contacts after adding a new contact
            }
        } else {
            System.out.println("Name or phone number is not valid..");
        }
    }

    public String getContactByPhone(String phone) {
        String name = contacts.get(phone);
        return (name != null) ? name + " - " + phone : "";
    }

    public Set<String> getContactByName(String name) {
        Set<String> matchingPhones = new TreeSet<>();
        for (Map.Entry<String, String> entry : contacts.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(name)) {
                matchingPhones.add(entry.getValue() + " - " + entry.getKey());
            }
        }
        System.out.println("Contacts by name: " + matchingPhones);
        return matchingPhones;
    }

    public Set<String> getAllContacts() {
        Set<String> allContacts = new TreeSet<>();
        Map<String, StringBuilder> contactMap = new HashMap<>();

        for (Map.Entry<String, String> entry : contacts.entrySet()) {
            String name = entry.getValue();
            String phone = entry.getKey();

            if (contactMap.containsKey(name)) {
                contactMap.get(name).append(", ").append(phone);
            } else {
                contactMap.put(name, new StringBuilder(name + " - " + phone));
            }
        }

        for (StringBuilder contactInfo : contactMap.values()) {
            allContacts.add(contactInfo.toString());
        }

        System.out.println("All contacts: " + allContacts);
        return allContacts;
    }

    private boolean isValidPhone(String phone) {
        String phoneRegex = "^\\+?\\d{1,15}$";
        return phone.matches(phoneRegex);
    }

    private boolean isValidName(String name) {
        String nameRegex = "^[\\p{L}]+([',. -][\\p{L} ])?[\\p{L}]*$";
        return name.matches(nameRegex);
    }


    public void saveContacts() {
        try {
            Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
            SecretKey secretKey = getSecretKey();
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            try (ObjectOutputStream outputStream = new ObjectOutputStream(
                    new CipherOutputStream(new FileOutputStream("contacts.enc"), cipher))) {
                outputStream.writeObject(contacts);
            }

        } catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    private void loadContacts() {
        if (new File("contacts.enc").exists()) {
            try {
                Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);

                SecretKey secretKey;
                if (firstLoad) {
                    secretKey = getSecretKey();
                    firstLoad = false;
                } else {
                    secretKey = new SecretKeySpec("dummykey".getBytes(StandardCharsets.UTF_8), ENCRYPTION_ALGORITHM);
                }

                cipher.init(Cipher.DECRYPT_MODE, secretKey);

                try (ObjectInputStream inputStream = new ObjectInputStream(
                        new CipherInputStream(new FileInputStream("contacts.enc"), cipher))) {
                    contacts = (Map<String, String>) inputStream.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            } catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
                e.printStackTrace();
            }
        }
    }

    private SecretKey getSecretKey() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the secret key:");
        String userKey = scanner.nextLine();

        // Determine the closest valid key length (128, 192, or 256 bits)
        int keyLength = (userKey.length() <= 16) ? 128 : (userKey.length() <= 24) ? 192 : 256;

        // Adjust the key length
        byte[] keyBytes = Arrays.copyOf(userKey.getBytes(StandardCharsets.UTF_8), keyLength / 8);

        return new SecretKeySpec(keyBytes, ENCRYPTION_ALGORITHM);
    }


}
