/*
 * Damiene Stewart
 *
 * This program generates 10 - 100 random
 * integers in randomNumbers.txt, 
 * writing their hexadecimal,
 * binary, and decimal representations to
 * hexAndBinary.txt.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

// Function declarations.
int random_number_file_generator();
int hex_and_binary_file_generator();
void convert_decimal_to_hex(int decimalNo, char *hex);
void convert_decimal_to_binary(int decimalNo, char *byte);

// Main.
int main(void) {
    if (!random_number_file_generator()) {
        hex_and_binary_file_generator();
    }
}

int random_number_file_generator() {
    // Seed random.
    srand(time(NULL));
    
    int r1 = rand() % 91 + 10; // Number of numbers.
    
    FILE *myFile = fopen("randomNumbers.txt", "w+"); // Get file pointer.
    
    // Were we able to create the file?
    if (myFile == NULL) {
        return 1;
    }
    
    // Add random numbers to file.
    for (int i = 10; i < r1; i++) {
        fprintf(myFile, "%d\n" , rand() % 256);
    }
    
    // Close file.
    fclose(myFile);
    
    return 0;
}

int hex_and_binary_file_generator() {

    // Variable declarations.
    FILE *myReadFile, *myWriteFile;
    char *myString = malloc(3);
    char *myHexadecimal = malloc(3);
    char *myBinary = malloc(9);
    
    myReadFile = fopen("randomNumbers.txt", "r"); // Open random numbers file.
    myWriteFile = fopen("hexAndBinary.txt", "w+"); // Open hex and binary file.
    
    if (myReadFile == NULL) {
        return 1;
    }
    
    while (!feof(myReadFile) && fgets(myString, 5, myReadFile) != 0) {
        
        int myValue = atoi(myString);
        
        convert_decimal_to_binary(myValue, myBinary);
        convert_decimal_to_hex(myValue, myHexadecimal);
        
        fprintf(myWriteFile,  "0x%s:  %s  (%d)\n", myHexadecimal, myBinary, myValue);
    }
    
    // Clean up.
    
    free(myString);
    free(myHexadecimal);
    free(myBinary);
    
    fclose(myReadFile);
    fclose(myWriteFile);
    return 0;
}

void convert_decimal_to_hex(int decimalNo, char *hex) {
    sprintf(hex, "%x", decimalNo);
}

void convert_decimal_to_binary(int decimalNo, char *byte) {
    int c = 1; // Used to test if bit is set in a particular position.
    
    for (int i = 7; i >= 0; i--) {
        byte[i] = (decimalNo & c) == 0 ? 0 : 1; // If bit is set, assign to array.
        byte[i] += 48; // add ascii offset.
        c <<= 1; // Left shift c to prepare to check next bit position.
    }
    
    byte[8] = '\0';
}