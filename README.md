# Phony

A simple console-based phone book application that allows users to manage contacts with encryption for enhanced privacy.

## Getting Started

### Prerequisites

Make sure you have Java installed on your system.

### Running the Application

1. Compile the `Main.java` and `PhoneBook.java` files.
2. Run the `Main` class to start the phone book application.

## Features

- **Add New Contact:** Add a new contact with a phone number and name.

- **Find Contact:**
    - Search contacts by name.
    - Search contacts by phone number (international format with '+').

- **View All Contacts:**
    - Display all contacts stored in the phone book.

- **Encryption:**
    - Contacts are stored in an encrypted file (`contacts.enc`) for enhanced privacy.

## Usage

1. Upon starting the application, the main menu will be displayed.
2. Follow the on-screen instructions to perform actions such as adding new contacts, finding contacts, viewing all contacts, or exiting the application.
3. For the first file creation, you will be prompted to enter a secret key. This key is used for encrypting and decrypting contacts.

## Encryption Details

The application uses the AES encryption algorithm to encrypt and decrypt contact information. The secret key, provided by the user, determines the encryption key.

## File Storage

Contact information is stored in the file `contacts.enc`. The file is encrypted and decrypted using the user-provided secret key.

**Note:** Ensure that the application is not run on a system where Java Runtime Environment (JRE) supports class file versions up to 55.0. If encountered, update the JRE to a version that supports class file versions 64.0.



## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

## Note

This project is a study project and is not likely to be updated regularly.
